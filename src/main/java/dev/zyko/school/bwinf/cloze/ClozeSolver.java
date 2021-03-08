package dev.zyko.school.bwinf.cloze;

import java.util.*;

public class ClozeSolver {

    private final Cloze cloze;

    public ClozeSolver(Cloze cloze) {
        this.cloze = cloze;
    }

    public String solve() {
        StringBuilder resultBuilder = new StringBuilder();
        List<Match> matchList = new ArrayList<>();
        for(Word w : this.cloze.getWordList()) {
            for(Gap g : this.cloze.getGapList()) {
                if(w.match(g)) {
                    if(this.isGapUsedInMatchList(matchList, g)) {
                        Objects.requireNonNull(this.getMatchEntryFromGap(matchList, g)).getPossibleWords().add(w);
                    } else {
                        List<Word> wordList = new ArrayList<>();
                        wordList.add(w);
                        Match m = new Match(g, wordList);
                        matchList.add(m);
                    }
                }
            }
        }

        /**
         * Sort the list by the number of possible words for a gap in ascending order.
         * This is needed, because if there is a gap that only matches with one word, that word can't be used in any other gap that in return must accept two or more words.
         */
        matchList.sort(Comparator.comparingInt(o -> o.getPossibleWords().size()));
        List<Word> usedWords = new ArrayList<>();
        for (Match m : matchList) {
            for (Word w : m.getPossibleWords()) {
                if (usedWords.contains(w)) continue;
                usedWords.add(w);
                m.setAcceptedSolution(w);
                break;
            }
        }

        /**
         * This is complicated:
         * If a word has not been used, then there has to be a gap that it theoretically fits in, but another word has taken it's spot.
         * In return we have to re-match every word for the also unused gap and once we find a word that fits into the unused gap and the unused word fits into the gap previously blocked by the aforementioned word,
         * we can safely swap these two to resolve the problem.
         */
        if(usedWords.size() != this.cloze.getWordList().size()) {
            for(Word w : this.cloze.getWordList()) {
                if(!usedWords.contains(w)) {
                    for(Gap g : this.cloze.getGapList()) {
                        if(w.match(g) && this.getMatchEntryFromGap(matchList, g) != null) {
                            Match m = this.getMatchEntryFromGap(matchList, g);
                            if(m.hasAcceptedSolution()) {
                                Word accepted = m.getAcceptedSolution();
                                for(Gap g2 : this.cloze.getGapList()) {
                                    if(accepted.match(g2) && this.getMatchEntryFromGap(matchList, g2) != null && !this.getMatchEntryFromGap(matchList, g2).hasAcceptedSolution()) {
                                        this.getMatchEntryFromGap(matchList, g2).setAcceptedSolution(accepted);
                                        m.setAcceptedSolution(w);
                                        usedWords.add(w);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        /**
         * Sort the words by their original order again.
         */
        matchList.sort(Comparator.comparingInt(m -> m.getGap().getIndex()));
        for(Match m : matchList) {
            if(m.hasAcceptedSolution()) {
                resultBuilder.append(m.getGap().getPrefix()).append(new String(m.getAcceptedSolution().getWord())).append(m.getGap().getSuffix()).append(" ");
            } else {
                resultBuilder.append("<no match found / ").append(new String(m.getGap().getRawData())).append("> ");
            }
        }
        return resultBuilder.toString().trim();
    }

    private Match getMatchEntryFromGap(List<Match> matchList, Gap g) {
        for(Match m : matchList) {
            if(m.getGap() == g) {
                return m;
            }
        }
        return null;
    }

    private boolean isGapUsedInMatchList(List<Match> matchList, Gap g) {
        for(Match m : matchList) {
            if(m.getGap() == g) {
                return true;
            }
        }
        return false;
    }

}

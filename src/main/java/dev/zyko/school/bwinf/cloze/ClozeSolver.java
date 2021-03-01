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
        matchList.sort(Comparator.comparingInt(m -> m.getPossibleWords().size()));
        List<Word> usedWords = new ArrayList<>();
        for (Match m : matchList) {
            for (Word w : m.getPossibleWords()) {
                if (usedWords.contains(w)) continue;
                usedWords.add(w);
                m.setAcceptedSolution(w);
            }
        }
        matchList.sort(Comparator.comparingInt(m -> m.getGap().getIndex()));
        for(Match m : matchList) {
            if(m.hasAcceptedSolution()) {
                resultBuilder.append(new String(m.getAcceptedSolution().getWord())).append(" ");
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

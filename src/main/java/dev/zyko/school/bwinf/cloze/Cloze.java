package dev.zyko.school.bwinf.cloze;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cloze {

    private final List<Gap> gapList;
    private final List<Word> wordList;

    public Cloze(String rawText, String words) {
        this.gapList = new ArrayList<>();
        this.wordList = new ArrayList<>();
        String[] splitText = rawText.split(" ");
        for(int i = 0; i < splitText.length; i++) {
            String gap = splitText[i];
            StringBuilder prefix = new StringBuilder(), suffix = new StringBuilder();
            String replacedGap = gap.replaceAll("[^a-zA-Z0-9_]", "");

            /**
             * Retrieve a possible prefix/suffix for each gap (to re-insert punctuation later on).
             */
            int maxBorder = 1;
            for(int j = 0; j < maxBorder; j++) {
                char c = gap.charAt(j);
                if(!this.checkWhetherCharacterIsLiteral(c)) {
                    prefix.append(c);
                    maxBorder++;
                } else break;
            }
            maxBorder = 1;
            for(int j = gap.length() - 1; j > gap.length() - 1 - maxBorder; j--) {
                char c = gap.charAt(j);
                if(!this.checkWhetherCharacterIsLiteral(c)) {
                    suffix.append(c);
                    maxBorder++;
                } else break;
            }

            this.gapList.add(new Gap(replacedGap, i, prefix.toString(), suffix.toString()));
            this.gapList.sort(Comparator.comparingInt(Gap::getIndex));

            /**
             * If we know the previous word ended with a '.', ':', '!', '?', we also know that the current word has to start with a capital letter. We set the flag accordingly.
             */
            for(int j = 1; j < this.gapList.size(); j++) {
                Gap prevGap = this.gapList.get(j - 1);
                Gap currentGap = this.gapList.get(j);
                if(prevGap.getSuffix().endsWith(":") || prevGap.getSuffix().endsWith(".") || prevGap.getSuffix().endsWith("!") || prevGap.getSuffix().endsWith("?")) {
                    currentGap.setInformationFlag(GapInformation.FIRST_LETTER_CAPITAL);
                }
            }
        }
        String[] splitWords = words.split(" ");
        for(String word : splitWords) {
            this.wordList.add(new Word(word));
        }
    }

    /**
     * Checks whether a character is included in A-Z, a-z, 0-9. Everything else is a special character, possibly punctuation.
     * @param c the character to check
     * @return true if the character is included in the above list of chars, otherwise false.
     */
    private boolean checkWhetherCharacterIsLiteral(char c) {
        String cString = String.valueOf(c);
        cString = cString.replaceAll("[^a-zA-Z0-9_]", "");
        return cString.length() != 0;
    }

    public List<Gap> getGapList() {
        return gapList;
    }

    public List<Word> getWordList() {
        return wordList;
    }

}

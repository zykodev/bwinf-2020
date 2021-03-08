package dev.zyko.school.bwinf.cloze;

import java.util.Arrays;

public class Word {

    private final char[] word;

    public Word(String word) {
        this.word = word.toCharArray();
    }

    public boolean match(Gap gap) {
        if(this.word.length == gap.getRawData().length) {
            if(gap.getAdditionalInformationFlags().size() > 0) {
                for(GapInformation gapInformation : gap.getAdditionalInformationFlags()) {
                    if(GapInformation.doesStringFit(gapInformation, String.valueOf(this.word))) {
                    } else return false;
                }
            }
            if(gap.getKnownCharacterMap().isEmpty()) {
                return true;
            }
            for(Integer i : gap.getKnownCharacterMap().keySet()) {
                if(word[i] != gap.getKnownCharacterMap().get(i)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public char[] getWord() {
        return word;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word=" + Arrays.toString(word) +
                '}';
    }

}

package dev.zyko.school.bwinf.cloze;

import java.util.ArrayList;
import java.util.List;

public class Cloze {

    /**
     * TODO: Fix "raetsel1.txt" error including multiple sentences in one text.
     * TODO: Re-inserting any punctuation before return.
     */

    private final List<Gap> gapList;
    private final List<Word> wordList;

    public Cloze(String rawText, String words) {
        this.gapList = new ArrayList<>();
        this.wordList = new ArrayList<>();
        String[] splitText = rawText.split(" ");
        for(int i = 0; i < splitText.length; i++) {
            String gap = splitText[i];
            gap = gap.replaceAll("[^a-zA-Z0-9_]", "");
            this.gapList.add(new Gap(gap, i));
        }
        String[] splitWords = words.split(" ");
        for(String word : splitWords) {
            this.wordList.add(new Word(word));
        }
    }

    public List<Gap> getGapList() {
        return gapList;
    }

    public List<Word> getWordList() {
        return wordList;
    }

}

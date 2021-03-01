package dev.zyko.school.bwinf.cloze;

import java.util.Arrays;
import java.util.List;

public class Match {

    private final Gap gap;
    private final List<Word> possibleWords;
    private Word acceptedSolution;

    public Match(Gap gap, List<Word> possibleWords) {
        this.gap = gap;
        this.possibleWords = possibleWords;
    }

    public void setAcceptedSolution(Word acceptedSolution) {
        this.acceptedSolution = acceptedSolution;
    }

    public boolean hasAcceptedSolution() {
        return this.acceptedSolution != null;
    }

    public Word getAcceptedSolution() {
        return acceptedSolution;
    }

    public Gap getGap() {
        return gap;
    }

    public List<Word> getPossibleWords() {
        return possibleWords;
    }

    @Override
    public String toString() {
        return "Match{" +
                "gap=" + gap +
                ", possibleWords=" + Arrays.toString(this.possibleWords.toArray(new Word[0])) +
                '}';
    }
}

package dev.zyko.school.bwinf.cloze;

import java.util.Arrays;
import java.util.HashMap;

public class Gap {

    private final char[] rawData;
    private final int index;
    private final HashMap<Integer, Character> knownCharacterMap;

    public Gap(String raw, int index) {
        this.rawData = raw.toCharArray();
        this.knownCharacterMap = this.fetchKnownChars();
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public HashMap<Integer, Character> getKnownCharacterMap() {
        return knownCharacterMap;
    }

    public char[] getRawData() {
        return rawData;
    }

    private HashMap<Integer, Character> fetchKnownChars() {
        HashMap<Integer, Character> indexCharMap = new HashMap<>();
        for (int i = 0; i < this.rawData.length; i++) {
            if(rawData[i] != '_') {
                indexCharMap.put(i, rawData[i]);
            }
        }
        return indexCharMap;
    }

    @Override
    public String toString() {
        return "Gap{" +
                "rawData=" + Arrays.toString(rawData) +
                '}';
    }

}

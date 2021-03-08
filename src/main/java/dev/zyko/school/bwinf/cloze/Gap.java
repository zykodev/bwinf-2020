package dev.zyko.school.bwinf.cloze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Gap {

    private final char[] rawData;
    private final int index;
    private final String prefix, suffix;
    private final HashMap<Integer, Character> knownCharacterMap;
    private final List<GapInformation> additionalInformationFlags;

    public Gap(String raw, int index, String prefix, String suffix) {
        this.additionalInformationFlags = new ArrayList<>();
        this.rawData = raw.toCharArray();
        this.knownCharacterMap = this.fetchKnownChars();
        this.index = index;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public int getIndex() {
        return index;
    }

    public void setInformationFlag(GapInformation gapInformation) {
        if(!this.additionalInformationFlags.contains(gapInformation))
            this.additionalInformationFlags.add(gapInformation);
    }

    public void unsetInformationFlag(GapInformation gapInformation) {
        this.additionalInformationFlags.remove(gapInformation);
    }

    public List<GapInformation> getAdditionalInformationFlags() {
        return additionalInformationFlags;
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

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    @Override
    public String toString() {
        return "Gap{" +
                "rawData=" + Arrays.toString(rawData) +
                '}';
    }

}

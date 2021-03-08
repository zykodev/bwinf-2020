package dev.zyko.school.bwinf.cloze;

public enum GapInformation {

    FIRST_LETTER_CAPITAL;

    /**
     * Using enum to provide the ability to quickly add more information flags if need be.
     * (Although a simple bit mask would have done the job perfectly...)
     */

    public static boolean doesStringFit(GapInformation gapInformation, String c) {
        if (gapInformation == GapInformation.FIRST_LETTER_CAPITAL) {
            String cString = c.substring(0, 1);
            return cString.toUpperCase().equals(cString);
        }
        return true;
    }

}

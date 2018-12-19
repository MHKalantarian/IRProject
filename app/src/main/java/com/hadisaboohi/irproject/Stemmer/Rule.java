package com.hadisaboohi.irproject.Stemmer;

import java.util.regex.Pattern;

/**
 * @author htaghizadeh
 */
class Rule {

    private Pattern body;
    private String substitution;
    private String poS;
    private byte minLength;
    private boolean state;

    public Rule(String sBody, String sSubstitution, String sPoS, byte iMinLength, boolean bState) {
        this.setBody(sBody);
        this.setSubstitution(sSubstitution);
        this.setPoS(sPoS);
        this.setMinLength(iMinLength);
        this.setState(bState);
    }

    public static Rule createFromText(String line) {
        String[] arr = line.split(",");
        return new Rule(arr[0], arr[1], arr[2], Byte.parseByte(arr[3]), Boolean.parseBoolean(arr[4]));
    }

    public String toString() {
        return "Body: '" + getBody().toString() + "', sub: '" + getSubstitution() + "', PoS: '" + getPoS() + "'|";
    }

    public final Pattern getBody() {
        return body;
    }

    public final void setBody(String value) {
        body = Pattern.compile(value);
    }

    public final String getSubstitution() {
        return substitution;
    }

    public final void setSubstitution(String value) {
        substitution = value;
    }

    public final String getPoS() {
        return poS;
    }

    public final void setPoS(String value) {
        poS = value;
    }

    public final byte getMinLength() {
        return minLength;
    }

    public final void setMinLength(byte value) {
        minLength = value;
    }

    public final boolean getState() {
        return state;
    }

    public final void setState(boolean value) {
        state = value;
    }
}

package com.hadisaboohi.irproject.Stemmer;

/**
 * @author htaghizadeh
 */
class VerbStem {

    private String present;
    private String past;

    public VerbStem(String sPast, String sPresent) {
        this.setPresent(sPresent);
        this.setPast(sPast);
    }

    public final String getPresent() {
        return present;
    }

    public final void setPresent(String value) {
        present = value;
    }

    public final String getPast() {
        return past;
    }

    public final void setPast(String value) {
        past = value;
    }
}

package com.duladissa.googlesignin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duladissa on 23/3/17.
 */

public class Result {
    @SerializedName("introShowSpeech")
    private String mIntroShowSpeech;
    @SerializedName("introSpeakOut")
    private String mIntroSpeakOut;

    public String getIntroShowSpeech() {
        return mIntroShowSpeech;
    }

    public void setIntroShowSpeech(String mIntroShowSpeech) {
        this.mIntroShowSpeech = mIntroShowSpeech;
    }

    public String getIntroSpeakOut() {
        return mIntroSpeakOut;
    }

    public void setIntroSpeakOut(String mIntroSpeakOut) {
        this.mIntroSpeakOut = mIntroSpeakOut;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("IntroShowSpeech - "+mIntroShowSpeech+",\n")
                .append("mIntroSpeakOut - "+mIntroSpeakOut)
                .toString();
    }
}

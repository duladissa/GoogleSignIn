package com.duladissa.googlesignin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duladissa on 23/3/17.
 */

public class ClientAction {

    @SerializedName("oauthExtractCodePattern")
    private String mOauthExtractCodePattern;
    @SerializedName("oauthProvider")
    private String mOauthProvider;
    @SerializedName("oauthRedirectUrl")
    private String mOauthRedirectUrl;
    @SerializedName("oauthUrl")
    private String mOauthUrl;
    @SerializedName("postTokenUrl")
    private String mPostTokenUrl;
    @SerializedName("type")
    private Long mType;

    public String getOauthExtractCodePattern() {
        return mOauthExtractCodePattern;
    }

    public void setOauthExtractCodePattern(String oauthExtractCodePattern) {
        mOauthExtractCodePattern = oauthExtractCodePattern;
    }

    public String getOauthProvider() {
        return mOauthProvider;
    }

    public void setOauthProvider(String oauthProvider) {
        mOauthProvider = oauthProvider;
    }

    public String getOauthRedirectUrl() {
        return mOauthRedirectUrl;
    }

    public void setOauthRedirectUrl(String oauthRedirectUrl) {
        mOauthRedirectUrl = oauthRedirectUrl;
    }

    public String getOauthUrl() {
        return mOauthUrl;
    }

    public void setOauthUrl(String oauthUrl) {
        mOauthUrl = oauthUrl;
    }

    public String getPostTokenUrl() {
        return mPostTokenUrl;
    }

    public void setPostTokenUrl(String postTokenUrl) {
        mPostTokenUrl = postTokenUrl;
    }

    public Long getType() {
        return mType;
    }

    public void setType(Long type) {
        mType = type;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("OAuthUrl - "+mOauthUrl+",\n")
                .append("OauthRedirectUrl - "+mOauthRedirectUrl+",\n")
                .append("OauthProvider - "+mOauthProvider+",\n")
                .append("Type - "+mType)
                .toString();
    }
}

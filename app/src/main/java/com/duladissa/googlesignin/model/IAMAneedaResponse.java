package com.duladissa.googlesignin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by duladissa on 22/3/17.
 */

public class IAMAneedaResponse {
    @SerializedName("clientAction")
    private ClientAction mClientAction;

    @SerializedName("result")
    private Result mResult;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ClientAction -"+((mClientAction != null) ? mClientAction.toString() : "")+",\n");
        builder.append("Results -"+((mResult != null) ? mResult.toString() : ""));
        return builder.toString();
    }
}

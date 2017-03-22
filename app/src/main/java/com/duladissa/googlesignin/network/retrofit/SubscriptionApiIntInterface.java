package com.duladissa.googlesignin.network.retrofit;

import com.duladissa.googlesignin.model.IAMAneedaResponse;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;

/**
 * Created by duladissa on 22/3/17.
 */

public interface SubscriptionApiIntInterface {
    String API_BASE_URL = "https://iamplus-skills-subscriptn-dev.herokuapp.com/";

    //01) Add Account - POST model/add_service.json content in body
    @POST("account")
    Call<IAMAneedaResponse> addRemoveAccout(@Body JsonObject jsonObject);

    //02) Send GET request for redirect url
    @GET("oauth/callback/google")
    Call<IAMAneedaResponse> getGoogleCallback(@QueryMap HashMap<String, String> queryParams);
}

package com.duladissa.googlesignin.network.retrofit;

import android.content.Context;

import com.duladissa.googlesignin.R;
import com.duladissa.googlesignin.model.IAMAneedaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by duladissa on 22/3/17.
 */

public class Controller implements Callback<IAMAneedaResponse> {

    private static Controller mInstance;

    private SubscriptionApiIntInterface mSubscriptionApiIntInterface;
    private Context mContext;

    public static Controller getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Controller(context);
        }
        return mInstance;
    }

    private Controller(Context context) {
        mContext = context;
        init();
    }

    public void init() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SubscriptionApiIntInterface.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mSubscriptionApiIntInterface = retrofit.create(SubscriptionApiIntInterface.class);
    }

    public void addService() {
        String json = loadJSONFromAsset(R.raw.add_service);
        Call<IAMAneedaResponse> call = mSubscriptionApiIntInterface.addRemoveAccout(new Gson().fromJson(json, JsonObject.class));
        call.enqueue(this);
    }

    public void removeService() {
        String json = loadJSONFromAsset(R.raw.remove_service);
        Call<IAMAneedaResponse> call = mSubscriptionApiIntInterface.addRemoveAccout(new Gson().fromJson(json, JsonObject.class));
        call.enqueue(this);
    }

    public void getGoogleCallback(String authCode, String error) {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("state","UNIQUE ID RECEIVED IN ADD SERVICE RESPONSE"); //Mandatory
        if(authCode != null) {
            queryMap.put("code", "AUTH CODE RECEIVED IN SIGN IN [authCode] :"+authCode); //Optional
        }
        if(error != null) {
            queryMap.put("error", "ERROR or USER DENY ACCESS [error] :"+error); //Optional
        }

        Call<IAMAneedaResponse> call = mSubscriptionApiIntInterface.getGoogleCallback(queryMap);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<IAMAneedaResponse> call, Response<IAMAneedaResponse> response) {
        if(response.isSuccessful()) {
            System.out.println("onResponse Success -> "+response.body().toString());
        } else {
            System.out.println("onResponse Failed -> "+response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<IAMAneedaResponse> call, Throwable t) {
        t.printStackTrace();
    }

    private String loadJSONFromAsset(int resourceId) {
        InputStream inputStream = mContext.getResources().openRawResource(resourceId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int ctr;
        try {
            ctr = inputStream.read();
            while (ctr != -1) {
                byteArrayOutputStream.write(ctr);
                ctr = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }
}

package com.duladissa.googlesignin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.duladissa.googlesignin.network.retrofit.Controller;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

public class SigninActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private static final String TAG = SigninActivity.class.getSimpleName();

    private static final int REQUEST_CODE_GET_AUTH_CODE = 50001;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initUi();
        initOAuth();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        findViewById(R.id.add_service_button).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.remove_service_button).setOnClickListener(this);
    }

    private void initOAuth() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(
                        /**
                         * https://www.googleapis.com/auth/userinfo.email
                         * https://www.googleapis.com/auth/userinfo.profile
                         * https://mail.google.com
                         * https://www.googleapis.com/auth/calendar
                         * https://www.googleapis.com/auth/carddav
                         */
                        new Scope(Scopes.EMAIL),
                        new Scope(Scopes.PROFILE),
                        new Scope("https://mail.google.com"),
                        new Scope("https://www.googleapis.com/auth/calendar"),
                        new Scope("https://www.googleapis.com/auth/carddav")
                )
                .requestServerAuthCode(Constants.SERVER_CLIENT_ID)
                .requestEmail()
                .build();

        // Build GoogleAPIClient with the Google Sign-In API and the above options.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_GET_AUTH_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "onActivityResult:GET_AUTH_CODE:success:" + result.getStatus().isSuccess());

            if (result.isSuccess()) {
                // [START get_auth_code]
                GoogleSignInAccount acct = result.getSignInAccount();
                String authCode = acct.getServerAuthCode();

                // TODO: send code to server and exchange for access/refresh/ID tokens.
                Controller.getInstance(this).getGoogleCallback(authCode, null);
            } else {
                Controller.getInstance(this).getGoogleCallback(null, "Error");
            }
        }
    }

    private void getAuthCode() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, REQUEST_CODE_GET_AUTH_CODE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_service_button:
                Controller.getInstance(this).addService();
                break;
            case R.id.sign_in_button:
                getAuthCode();
                break;
            case R.id.remove_service_button:
                Controller.getInstance(this).removeService();
                break;
        }
    }
}

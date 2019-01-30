package com.percyku.work.mygoogleaccount;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    GoogleSignInOptions gso;

    GoogleSignInClient mGoogleSignInClient;

//    GoogleApiClient mGoogleApiClient;

    private SignInButton signInButton;

    private Button signOutButtone;

    private TextView informationTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

    }

    private void initial() {


        informationTextView=(TextView)this.findViewById(R.id.information_txt);

        signInButton=(SignInButton)this.findViewById(R.id.sign_in_btn);

        signOutButtone=(Button)this.findViewById(R.id.sign_out_btn);


        signInButton.setOnClickListener(this);

        signOutButtone.setOnClickListener(this);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient=  GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }


    private void onSignIn() {


        startActivityForResult(mGoogleSignInClient.getSignInIntent(),1);


    }

    private void onSignOut(){


        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...

                        informationTextView.setText("Not Sign in");
                    }
                });
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode ==1){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignResult(task);

        }
    }



    private void handleSignResult(Task<GoogleSignInAccount> task) {

        try{

            GoogleSignInAccount account = task.getResult(ApiException.class);
            updateUI(account);



        } catch (ApiException e) {
            e.printStackTrace();
            Log.e("Error",e.toString());
            updateUI(null);
        }


    }

        private void updateUI(GoogleSignInAccount account) {

        if(account!=null){
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            informationTextView.setText(personName+"\n"+personGivenName
                    +"\n"+personFamilyName+"\n"+personEmail+"\n"+personId+"\n"+personPhoto);

        }else{

            informationTextView.setText("Not Sign in");

        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.sign_in_btn:

                onSignIn();


                break;
            case  R.id.sign_out_btn:

                onSignOut();

                break;
        }

    }
}

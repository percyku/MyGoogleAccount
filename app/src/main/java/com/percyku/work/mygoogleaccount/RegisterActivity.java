package com.percyku.work.mygoogleaccount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText mAccount,mPwd,mGPwd,mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initial();

    }

    private void initial() {

        mAccount=(EditText) this.findViewById(R.id.idEdt);
        mPwd=(EditText) this.findViewById(R.id.passwordsEdt);
        mGPwd=(EditText) this.findViewById(R.id.passwordsAgainEdt);
        mName=(EditText) this.findViewById(R.id.idEdt);

    }
}

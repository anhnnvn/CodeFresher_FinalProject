package com.example.myapplication3.signinscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication3.homescreen.HomeActivity;
import com.example.myapplication3.R;

public class SignInActivity extends AppCompatActivity {

    private static Boolean statusLogIn = true;
    private static String TAG = "TAG";

    public EditText userName;
    public EditText passWord;
    public TextView describeUser;
    public TextView describePass;
    public ImageView warningUser;
    public ImageView warningPass;
    public TextView loginButton;
    public TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView(R.layout.activity_sign_in);
        initView();
        setAction();
    }

    private void initView() {
        userName = findViewById(R.id.username);
        signUp = findViewById(R.id.sign_up);
        passWord = findViewById(R.id.password);
        loginButton = findViewById(R.id.button_login);


//        warningUser = findViewById(R.id.image_warning_username);
//        warningPass = findViewById(R.id.image_warning_password);

    }

    private void setAction() {
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLogIn()) {
                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                    intent.putExtra("userName", userName.getText());
                    startActivity(intent);
                }
            }
        });

        this.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment signUpFragment = SignUpFragment.newInstance();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, signUpFragment, TAG)
                        .commit();
            }
        });


        userName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }
        });
        passWord.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }
        });
    }

    private boolean checkLogIn() {
        if (this.userName.getText().toString().isEmpty() || this.passWord.getText().toString().isEmpty()) {
            if (this.userName.getText().toString().isEmpty()) {
//                this.warningUser.setVisibility(View.VISIBLE);
//                this.describeUser.setVisibility(View.VISIBLE);
            }
            if (this.passWord.getText().toString().isEmpty()) {
//                this.warningPass.setVisibility(View.VISIBLE);
//                this.describePass.setVisibility(View.VISIBLE);
            }
            return true;
        }
        return true;
    }
}
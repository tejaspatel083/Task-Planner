package com.example.taskplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {

    private Button loginbtn,createAccountbtn;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        loginbtn = findViewById(R.id.MainLoginBtn);
        createAccountbtn = findViewById(R.id.MainCreateBtn);
        forgotPassword = findViewById(R.id.MainForgotPassword);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginPage.this,HomePage.class);
                startActivity(intent);
            }
        });

        createAccountbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginPage.this,CreateAccountPage.class);
                startActivity(intent);

            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginPage.this,ForgotPasswordPage.class);
                startActivity(intent);
            }
        });
    }
}

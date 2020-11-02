package com.example.taskplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity {

    private Button loginbtn,createAccountbtn;
    private TextView forgotPassword;
    private EditText pwd,emailId;
    private TextView v1,iv1;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        loginbtn = findViewById(R.id.MainLoginBtn);
        createAccountbtn = findViewById(R.id.MainCreateBtn);
        forgotPassword = findViewById(R.id.MainForgotPassword);
        pwd = findViewById(R.id.MainPassword);
        emailId = findViewById(R.id.MainEmail);
        v1 = findViewById(R.id.visible);
        iv1 = findViewById(R.id.notvisible);


       firebaseAuth = FirebaseAuth.getInstance();

        pwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v1.setVisibility(View.VISIBLE);
                return false;
            }
        });

        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                v1.setVisibility(View.INVISIBLE);
                iv1.setVisibility(View.VISIBLE);
            }
        });
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                iv1.setVisibility(View.INVISIBLE);
                v1.setVisibility(View.VISIBLE);
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = emailId.getText().toString().trim();
                String password = pwd.getText().toString().trim();


                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {

                            checkEmailVerification();

                        }
                        else
                        {

                        }

                    }
                });


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
    private void checkEmailVerification() {


        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag)
        {

            Toast.makeText(this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginPage.this,HomePage.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Please verify your Email", Toast.LENGTH_SHORT).show();
        }

    }
}

package com.example.taskplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskplanner.api_interfaces.JsonPlaceHolderApi;
import com.example.taskplanner.models.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

                if (emailId.getText().toString().trim().length() == 0)
                {
                    emailId.setError("Email Id Required");
                    //Toast.makeText(MainActivity.this,"Enter Email",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(LoginPage.this,"Enter Email",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if (pwd.getText().toString().trim().length() == 0)
                {
                    emailId.setError(null);
                    pwd.setError("Password Required");
                    //Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_LONG).show();
                    Toast toast = Toast.makeText(LoginPage.this,"Enter Password",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else
                {
                    emailId.setError(null);
                    pwd.setError(null);

                    //login();

                    Toast.makeText(LoginPage.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPage.this,HomePage.class);
                    startActivity(intent);
                    /*
                    String email = emailId.getText().toString().trim();
                    String password = pwd.getText().toString().trim();



                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {

                                //checkEmailVerification();

                            }
                            else
                            {

                                Toast toast = Toast.makeText(LoginPage.this,"Enter Valid Email and Password",Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                                toast.show();
                            }

                        }
                    });

                     */

                }




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

    public void login()
    {
        UserInfo userInfo = new UserInfo();

        userInfo.setEmail(emailId.getText().toString().trim());
        userInfo.setPassword(pwd.getText().toString().trim());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<UserInfo> call = jsonPlaceHolderApi.login(userInfo);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {

                if (response.isSuccessful())
                {
                    Toast.makeText(LoginPage.this, "Login SuccessFull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPage.this,HomePage.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginPage.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

                Toast.makeText(LoginPage.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}

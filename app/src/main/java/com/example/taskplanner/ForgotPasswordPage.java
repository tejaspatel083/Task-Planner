package com.example.taskplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordPage extends AppCompatActivity {

    private EditText forgotpwd;
    private Button submitbtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);

        forgotpwd = findViewById(R.id.ForgotEmail);
        submitbtn = findViewById(R.id.ForgotSubmitBtn);

        firebaseAuth = FirebaseAuth.getInstance();


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = forgotpwd.getText().toString().trim();


                if(email.length() == 0)
                {
                    Toast toast = Toast.makeText(ForgotPasswordPage.this,"Please Enter Your Email",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();
                }
                else
                {


                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {

                                Toast toast = Toast.makeText(ForgotPasswordPage.this,"Password Reset Email sent",Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
                                toast.show();

                                Intent i = new Intent(ForgotPasswordPage.this,LoginPage.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast toast = Toast.makeText(ForgotPasswordPage.this,"Enter Registered Email", Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL,0,0);
                                toast.show();

                            }
                        }
                    });

                }

            }
        });
    }
}

package com.example.baithick_thipchay_bino_quoc.Activity.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baithick_thipchay_bino_quoc.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    TextView alreadyHaveAccount;
    EditText inputEmail,inputPassword,inputConformPassword;
    Button btnRegister;
    String emailpattern ="[a-zA-z1-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        alreadyHaveAccount  =findViewById(R.id.alreadyHaveAccount) ;

        inputEmail  =findViewById(R.id.inputEmail) ;
        inputPassword  =findViewById(R.id.inputPassword) ;
        inputConformPassword  =findViewById(R.id.inputConformPassword) ;
        btnRegister=findViewById(R.id.btnRegister);
        progressDialog=new ProgressDialog(this);
        mAuth =FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,StartActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforAuth();
            }
        });
    }

    private void perforAuth() {
        String email =inputEmail.getText().toString();
        String password =inputPassword.getText().toString();
        String conformPassword =inputConformPassword.getText().toString();


        if (!email.matches(emailpattern)){
            inputEmail.setError("Enter connext enail");
        }else if (password.isEmpty()||password.length()<6){
            inputPassword.setError("Enter propre password");
        }else if (!password.equals(conformPassword)){
            inputPassword.setError("Password not match");
        }
        else
        {
            progressDialog.setMessage("Please wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this,"Registartion Successful",Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
    Intent intent=new Intent(RegisterActivity.this,introActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    }
}
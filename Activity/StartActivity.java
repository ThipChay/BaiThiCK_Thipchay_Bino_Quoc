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

public class StartActivity extends AppCompatActivity {

    TextView createnewAccount;
    EditText inputEmail,inputPassword;
    Button btnLogin;
    String emailpattern ="[a-zA-z1-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        createnewAccount  =findViewById(R.id.createNewAccount) ;

        inputEmail  =findViewById(R.id.inputEmail) ;
        inputPassword  =findViewById(R.id.inputPassword) ;
        btnLogin =findViewById(R.id.btnLogin);
        progressDialog=new ProgressDialog(this);
        mAuth =FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        createnewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,RegisterActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfroLogin();
            }
        });

    }
    private void perfroLogin() {
        String email =inputEmail.getText().toString();
        String password =inputPassword.getText().toString();



        if (!email.matches(emailpattern)){
            inputEmail.setError("Enter connext enail");
        }else if (password.isEmpty()||password.length()<6){
            inputPassword.setError("Enter propre password");
        }else
        {
            progressDialog.setMessage("Please wait while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(StartActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(StartActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(StartActivity.this,introActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}

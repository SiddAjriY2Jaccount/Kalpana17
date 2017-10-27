package com.example.unmesh.kalpana;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText uname;
    EditText passwd;
    Button loginbtn;
    TextView Signupt;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        //checking if he is already signed in
        if(mAuth.getCurrentUser() != null){
            //Start the profileactivity after login
            finish();
           startActivity(new Intent(getApplicationContext(),Profileactivity.class));
        }

        //get the ui elements
        uname = (EditText)findViewById(R.id.etName);
        passwd = (EditText)findViewById(R.id.etpswd);
        loginbtn = (Button)findViewById(R.id.Loginbtn);
        Signupt = (TextView)findViewById(R.id.Signuptxt);

        loginbtn.setOnClickListener(this);
        Signupt.setOnClickListener(this);



    }

    private void userLogin(){
        String username = uname.getText().toString().trim();
        String password = passwd.getText().toString().trim();

        //to check if the emails are empty
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please Enter Email-ID",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //Here is the profileactivity once he logs into his account
                            finish();
                            startActivity(new Intent(getApplicationContext(),Profileactivity.class));

                        }
                        else
                            Toast.makeText(getApplicationContext(), "Account does not exist",Toast.LENGTH_LONG).show();

                    }
                });
    }


    @Override
    public void onClick(View v) {
        if(v == loginbtn){
            userLogin();
        }
        if(v == Signupt){
            finish();
            startActivity(new Intent(this, Signup.class));
        }
    }
}

package com.example.unmesh.kalpana;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity implements View.OnClickListener {


    //Creating the private variables
    private Button Signupbtn;
    private EditText usname;
    private EditText passwod;
    private EditText repasswod;
    private EditText email;
    private TextView login;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        //Variable take are wired to the components on create
        Signupbtn = (Button)findViewById(R.id.Signupbtn);
        usname = (EditText)findViewById(R.id.usernm);
        email = (EditText)findViewById(R.id.emailid);
        passwod = (EditText)findViewById(R.id.passwd);
        repasswod = (EditText)findViewById(R.id.repasswd);
        login = (TextView)findViewById(R.id.Logintt);

        Signupbtn.setOnClickListener(this);
        login.setOnClickListener(this);

    }


    private void registerUser(){
        String username = usname.getText().toString().trim();
        String emaild = email.getText().toString().trim();
        String p = passwod.getText().toString().trim();
        String rp = repasswod.getText().toString().trim();

        //to check if the emails are empty
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please Enter Username",Toast.LENGTH_LONG).show();
            //prevents the further execution of this function
            return;
        }
        if (TextUtils.isEmpty(emaild)){
            Toast.makeText(this,"Please Enter Email-ID",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(p)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(rp)){
            Toast.makeText(this,"Please Re-Enter Password",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.getTrimmedLength(p)<6){
            Toast.makeText(this,"Password too short",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.equals(p,rp)){


            mAuth.createUserWithEmailAndPassword(emaild, p)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                //Toast.makeText(this,"Please Enter Username",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Profileactivity.class));
                            }

                        }
                    });
        }
        else {
            Toast.makeText(this,"Entered passwords dont match",Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public void onClick(View v) {

        if(v == login){
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

        if (v == Signupbtn){


            registerUser();


        }

    }
}

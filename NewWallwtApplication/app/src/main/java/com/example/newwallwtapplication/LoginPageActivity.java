package com.example.newwallwtapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPageActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginPageButton;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginUsername = (EditText) findViewById(R.id.loginUsername);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginPageButton = (Button) findViewById(R.id.loginPageButton);
        DB = new DatabaseHelper(this);

        loginPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = loginUsername.getText().toString();
                String pass = loginPassword.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LoginPageActivity.this, "Enter all the fields!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checuserpass = DB.checkUsernamePassword(user,pass);
                    if(checuserpass == true){
                        Toast.makeText(LoginPageActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        changeActivity();
                    }
                    else{
                        Toast.makeText(LoginPageActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void changeActivity(){
        Intent intent = new Intent(LoginPageActivity.this, ProfilePageActivity.class);
        startActivity(intent);
    }
}
package com.example.newwallwtapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPageActivity extends AppCompatActivity {

    EditText fullName, password;
    Button systemRegisterButton;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        fullName = (EditText) findViewById(R.id.fullName);
        password = (EditText) findViewById(R.id.password);
        systemRegisterButton = (Button) findViewById(R.id.systemRegisterButton);
        DB = new DatabaseHelper(this);

        systemRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = fullName.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("") || pass.equals(""))
                    Toast.makeText(RegisterPageActivity.this, "Enter all the fields!!!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = DB.checkUserName(user);
                    if(checkUser == false){
                        Boolean insert = DB.addUser(user, pass);
                        if(insert == true){
                            Toast.makeText(RegisterPageActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            changeActivity();
                        }
                        else{
                            Toast.makeText(RegisterPageActivity.this, "Registration failed!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterPageActivity.this, "User already exists!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void changeActivity(){
        Intent intent = new Intent(RegisterPageActivity.this,LoginPageActivity.class);
        startActivity(intent);

    }
}
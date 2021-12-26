package com.example.income_expense_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText fullName,userName, password;
    Button systemRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        fullName = (EditText) findViewById(R.id.fullName);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        systemRegisterButton = (Button) findViewById(R.id.systemRegisterButton);

        systemRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });
    }



    public void changeActivity(){
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);

    }

}

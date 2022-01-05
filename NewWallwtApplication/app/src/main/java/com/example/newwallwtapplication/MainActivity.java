package com.example.newwallwtapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button registerButton, loginButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton=(Button) findViewById(R.id.registerButton);
        loginButton = (Button) findViewById(R.id.loginButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });

        //giris butonuna welcome page sayfasına gidiş özelliği
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity2();
            }
        });
    }

    public void changeActivity(){
        Intent intent = new Intent(MainActivity.this,RegisterPageActivity.class);
        startActivity(intent);

    }

    public void changeActivity2(){
        Intent intent3 = new Intent(MainActivity.this, LoginPageActivity.class);
        startActivity(intent3);
    }
}
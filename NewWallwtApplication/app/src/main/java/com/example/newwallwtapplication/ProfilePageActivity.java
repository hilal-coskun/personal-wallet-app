package com.example.newwallwtapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfilePageActivity extends AppCompatActivity {

    Button incomeList, expenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        incomeList = (Button) findViewById(R.id.incomeList);
        expenseList = (Button) findViewById(R.id.expenseList);


        incomeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivity2();
            }
        });

        expenseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivity3();
            }
        });

    }

    public void ChangeActivity2(){
        Intent intent2 = new Intent(ProfilePageActivity.this, IncomeListPage.class);
        startActivity(intent2);
    }

    public void ChangeActivity3(){
        Intent intent3 = new Intent(ProfilePageActivity.this, ExpenseListPage.class);
        startActivity(intent3);
    }

}
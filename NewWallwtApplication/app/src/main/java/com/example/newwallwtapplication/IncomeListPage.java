package com.example.newwallwtapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class IncomeListPage extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addIncome_button;

    DatabaseHelper db ;
    ArrayList<String> income_id, income_date, income_subject, income_amount;
    IncomeAdaptor incAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_list_page);

        recyclerView = findViewById(R.id.recyclerView);
        addIncome_button = findViewById(R.id.addIncome_button);

        addIncome_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IncomeListPage.this, AddIncomePageActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(IncomeListPage.this);
        income_id = new ArrayList<>();
        income_date = new ArrayList<>();
        income_subject = new ArrayList<>();
        income_amount = new ArrayList<>();

        allData();

        incAdp = new IncomeAdaptor(IncomeListPage.this, this, income_id, income_date, income_subject, income_amount);
        recyclerView.setAdapter(incAdp);
        recyclerView.setLayoutManager(new LinearLayoutManager(IncomeListPage.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void allData(){
        Cursor cursor = db.readData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"Data is null", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()) {
                income_id.add(cursor.getString(0));
                income_date.add(cursor.getString(1));
                income_subject.add(cursor.getString(2));
                income_amount.add(cursor.getString(3));
            }
        }
    }
}
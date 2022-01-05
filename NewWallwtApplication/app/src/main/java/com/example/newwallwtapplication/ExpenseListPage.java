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

public class ExpenseListPage extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addExpense_button;
    DatabaseHelper db ;

    ArrayList<String> expense_id, expense_date, expense_subject, expense_amount;
    ExpenseAdaptor expAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list_page);

        addExpense_button = findViewById(R.id.addExpense_button);
        recyclerView = findViewById(R.id.recyclerView);


        addExpense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpenseListPage.this, AddExpensePageActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(ExpenseListPage.this);
        expense_id = new ArrayList<>();
        expense_date = new ArrayList<>();
        expense_subject = new ArrayList<>();
        expense_amount = new ArrayList<>();

        allExpenseData();

        expAdp = new ExpenseAdaptor(ExpenseListPage.this, this, expense_id, expense_date, expense_subject, expense_amount);
        recyclerView.setAdapter(expAdp);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExpenseListPage.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void allExpenseData(){
        Cursor cursor = db.readExpenseData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"Data is null", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()) {
                expense_id.add(cursor.getString(0));
                expense_date.add(cursor.getString(1));
                expense_subject.add(cursor.getString(2));
                expense_amount.add(cursor.getString(3));
            }
        }
    }
}
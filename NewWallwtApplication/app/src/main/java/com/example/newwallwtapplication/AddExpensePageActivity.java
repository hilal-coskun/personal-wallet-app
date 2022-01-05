package com.example.newwallwtapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddExpensePageActivity extends AppCompatActivity {

    EditText date_input_expense, subject_input_expense, amount_input_expense;
    Button add_expense_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense_page);

        date_input_expense = (EditText) findViewById(R.id.date_input_expense);
        subject_input_expense = (EditText) findViewById(R.id.subject_input_expense);
        amount_input_expense = (EditText) findViewById(R.id.amount_input_expense);
        add_expense_button = (Button) findViewById(R.id.add_expense_button);

        add_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper EDB = new DatabaseHelper(AddExpensePageActivity.this);
                EDB.addExpense(date_input_expense.getText().toString().trim(),
                        subject_input_expense.getText().toString().trim(),
                        Integer.valueOf(amount_input_expense.getText().toString().trim()));
            }
        });
    }
}
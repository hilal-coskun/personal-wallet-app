package com.example.newwallwtapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddIncomePageActivity extends AppCompatActivity {

    EditText date_input, subject_input, amount_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income_page);

        date_input = (EditText) findViewById(R.id.date_input);
        subject_input = (EditText) findViewById(R.id.subject_input);
        amount_input = (EditText) findViewById(R.id.amount_input);
        add_button = (Button) findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper DB = new DatabaseHelper(AddIncomePageActivity.this);
                DB.addIncome(date_input.getText().toString().trim(),
                        subject_input.getText().toString().trim(),
                        Integer.valueOf(amount_input.getText().toString().trim()));
            }
        });
    }
}
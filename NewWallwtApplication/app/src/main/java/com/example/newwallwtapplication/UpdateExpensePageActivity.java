package com.example.newwallwtapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateExpensePageActivity extends AppCompatActivity {

    String id, date, subject, amount;
    EditText date_input, subject_input, amount_input;
    Button update_button, delete_expense_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_expense_page);

        date_input = (EditText) findViewById(R.id.date_input_edit);
        subject_input = (EditText) findViewById(R.id.subject_input_edit);
        amount_input = (EditText) findViewById(R.id.amount_input_edit);
        update_button = (Button) findViewById(R.id.update_button);
        delete_expense_button = (Button) findViewById(R.id.delete_expense_button);

        getData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(UpdateExpensePageActivity.this);
                date = date_input.getText().toString().trim();
                subject = subject_input.getText().toString().trim();
                amount = amount_input.getText().toString().trim();
                db.updateExpense(id, date, subject, amount);
            }
        });

        delete_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
    }

    void getData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("date") && getIntent().hasExtra("subject") && getIntent().hasExtra("amount")){
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            subject = getIntent().getStringExtra("subject");
            amount = getIntent().getStringExtra("amount");

            date_input.setText(date);
            subject_input.setText(subject);
            amount_input.setText(amount);

        }else{
            Toast.makeText(this, "Data is null", Toast.LENGTH_SHORT).show();
        }
    }

    void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + subject+ " ?");
        builder.setMessage("Are you sure ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper db = new DatabaseHelper(UpdateExpensePageActivity.this);
                db.deleteExpense(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
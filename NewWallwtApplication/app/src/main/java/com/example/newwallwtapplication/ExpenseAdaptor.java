package com.example.newwallwtapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdaptor extends RecyclerView.Adapter<ExpenseAdaptor.MyViewHolder> {

    private Context ctx;
    private ArrayList expense_id, expense_date, expense_subject, expense_amount;
    Activity activity;

    ExpenseAdaptor(Activity activity, Context _context, ArrayList expense_id, ArrayList expense_date, ArrayList expense_subject, ArrayList expense_amount){
        this.activity = activity;
        this.ctx = _context;
        this.expense_id = expense_id;
        this.expense_date = expense_date;
        this.expense_subject = expense_subject;
        this.expense_amount = expense_amount;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.item_expense, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id_txt_expense.setText(String.valueOf(expense_id.get(position)));
        holder.date_txt_expense.setText(String.valueOf(expense_date.get(position)));
        holder.subject_txt_expense.setText(String.valueOf(expense_subject.get(position)));
        holder.amount_txt_expense.setText(String.valueOf(expense_amount.get(position)));
        holder.mainL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, UpdateExpensePageActivity.class);
                intent.putExtra("id", String.valueOf(expense_id.get(position)));
                intent.putExtra("date", String.valueOf(expense_date.get(position)));
                intent.putExtra("subject", String.valueOf(expense_subject.get(position)));
                intent.putExtra("amount", String.valueOf(expense_amount.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return expense_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt_expense, date_txt_expense, subject_txt_expense, amount_txt_expense;
        LinearLayout mainL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt_expense = itemView.findViewById(R.id.id_txt_expense);
            date_txt_expense = itemView.findViewById(R.id.date_txt_expense);
            subject_txt_expense = itemView.findViewById(R.id.subject_txt_expense);
            amount_txt_expense = itemView.findViewById(R.id.amount_txt_expense);
            mainL = itemView.findViewById(R.id.mainL);
        }
    }
}

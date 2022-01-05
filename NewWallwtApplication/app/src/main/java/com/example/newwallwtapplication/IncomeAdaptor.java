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

public class IncomeAdaptor extends RecyclerView.Adapter<IncomeAdaptor.MyViewHolder> {

    private Context ctx;
    private ArrayList income_id, income_date, income_subject, income_amount;
    Activity activity;

    IncomeAdaptor(Activity activity, Context _context, ArrayList income_id, ArrayList income_date, ArrayList income_subject, ArrayList income_amount){
        this.activity = activity;
        this.ctx = _context;
        this.income_id = income_id;
        this.income_date = income_date;
        this.income_subject = income_subject;
        this.income_amount = income_amount;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.item_income, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id_txt_income.setText(String.valueOf(income_id.get(position)));
        holder.date_txt_income.setText(String.valueOf(income_date.get(position)));
        holder.subject_txt_income.setText(String.valueOf(income_subject.get(position)));
        holder.amount_txt_income.setText(String.valueOf(income_amount.get(position)));
        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, UpdateIncomePageActivity.class);
                intent.putExtra("id", String.valueOf(income_id.get(position)));
                intent.putExtra("date", String.valueOf(income_date.get(position)));
                intent.putExtra("subject", String.valueOf(income_subject.get(position)));
                intent.putExtra("amount", String.valueOf(income_amount.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return income_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt_income, date_txt_income, subject_txt_income, amount_txt_income;
        LinearLayout main;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt_income = itemView.findViewById(R.id.id_txt_income);
            date_txt_income = itemView.findViewById(R.id.date_txt_income);
            subject_txt_income = itemView.findViewById(R.id.subject_txt_income);
            amount_txt_income = itemView.findViewById(R.id.amount_txt_income);
            main = itemView.findViewById(R.id.main);
        }
    }
}

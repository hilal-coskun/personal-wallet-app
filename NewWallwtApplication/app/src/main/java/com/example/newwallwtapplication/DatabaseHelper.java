package com.example.newwallwtapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context ctxt ;

    public static final String DATABASE_NAME = "MobileApplication.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_INSOME_NAME = "insomeApp";
    public static final String COLUMN_INSOME_ID = "_id";
    public static final String COLUMN_INSOME_DATE = "income_date";
    public static final String COLUMN_INSOME_SUBJECT = "income_subject";
    public static final String COLUMN_INSOME_AMOUNT = "income_amount";

    public static final String TABLE_EXPENSE_NAME = "expenseApp";
    public static final String COLUMN_EXPENSE_ID = "_id";
    public static final String COLUMN_EXPENSE_DATE = "expense_date";
    public static final String COLUMN_EXPENSE_SUBJECT = "expense_subject";
    public static final String COLUMN_EXPENSE_AMOUNT = "expense_amount";


    public static final String TABLE_USER_NAME = "user";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_PASSWORD = "password";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctxt = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE " + TABLE_INSOME_NAME +
                " (" + COLUMN_INSOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INSOME_DATE + " TEXT, " +
                COLUMN_INSOME_SUBJECT + " TEXT, " +
                COLUMN_INSOME_AMOUNT + " INTEGER);";

        String query2 ="CREATE TABLE " + TABLE_EXPENSE_NAME +
                " (" + COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EXPENSE_DATE + " TEXT, " +
                COLUMN_EXPENSE_SUBJECT + " TEXT, " +
                COLUMN_EXPENSE_AMOUNT + " INTEGER);";

        String query3 ="CREATE TABLE " + TABLE_USER_NAME +
                " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT);";


        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSOME_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_NAME);
        onCreate(db);
    }

    public Boolean addUser(String user_name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvalues = new ContentValues();

        cvalues.put(COLUMN_USER_NAME, user_name);
        cvalues.put(COLUMN_USER_PASSWORD, password);
        long res = db.insert(TABLE_USER_NAME, null, cvalues);

        if(res == -1){
            Toast.makeText(ctxt, "User could not be registered", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(ctxt, "user successfully registered", Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    public Boolean checkUserName(String user_name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from "+ TABLE_USER_NAME + " where " + COLUMN_USER_NAME +" =?", new String[] {user_name});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String user_name, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from "+ TABLE_USER_NAME + " where "+COLUMN_USER_NAME + "= ? and " + COLUMN_USER_PASSWORD + " = ?",new String[] { user_name, password} );
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }




    void addIncome(String date, String subject, int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvalues = new ContentValues();

        cvalues.put(COLUMN_INSOME_DATE, date);
        cvalues.put(COLUMN_INSOME_SUBJECT, subject);
        cvalues.put(COLUMN_INSOME_AMOUNT, amount);
        long res = db.insert(TABLE_INSOME_NAME, null, cvalues);

        if(res == -1){
            Toast.makeText(ctxt, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ctxt, "Success", Toast.LENGTH_SHORT).show();
        }

    }

    void addExpense(String date, String subject, int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvalue = new ContentValues();

        cvalue.put(COLUMN_EXPENSE_DATE, date);
        cvalue.put(COLUMN_EXPENSE_SUBJECT, subject);
        cvalue.put(COLUMN_EXPENSE_AMOUNT, amount);
        long res = db.insert(TABLE_EXPENSE_NAME, null, cvalue);

        if(res == -1){
            Toast.makeText(ctxt, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ctxt, "Success", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_INSOME_NAME;
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor =  null;
        if(DB != null){
            cursor = DB.rawQuery(query, null);
        }

        return  cursor;
    }

    Cursor readExpenseData(){
        String query = "SELECT * FROM " + TABLE_EXPENSE_NAME;
        SQLiteDatabase DB = this.getReadableDatabase();

        Cursor cursor =  null;
        if(DB != null){
            cursor = DB.rawQuery(query, null);
        }

        return  cursor;
    }



    void update(String row_id, String date, String subject, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvalue = new ContentValues();

        cvalue.put(COLUMN_INSOME_DATE, date);
        cvalue.put(COLUMN_INSOME_SUBJECT, subject);
        cvalue.put(COLUMN_INSOME_AMOUNT, amount);

        long result = db.update(TABLE_INSOME_NAME, cvalue, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(ctxt, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(ctxt, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    void updateExpense(String row_id, String date, String subject, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvalue = new ContentValues();

        cvalue.put(COLUMN_EXPENSE_DATE, date);
        cvalue.put(COLUMN_EXPENSE_SUBJECT, subject);
        cvalue.put(COLUMN_EXPENSE_AMOUNT, amount);

        long result = db.update(TABLE_EXPENSE_NAME, cvalue, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(ctxt, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(ctxt, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    void delete(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_INSOME_NAME, "_id=?", new String[]{row_id});

        if(res == -1){
            Toast.makeText(ctxt, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ctxt, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteExpense(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_EXPENSE_NAME, "_id=?", new String[]{row_id});

        if(res == -1){
            Toast.makeText(ctxt, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(ctxt, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}

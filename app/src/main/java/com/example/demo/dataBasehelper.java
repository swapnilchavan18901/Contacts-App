package com.example.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dataBasehelper<DatabaseOpenHelper> extends SQLiteOpenHelper {


    public static final String COLUMN_ID = "ID";
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_PHONE = "CUSTOMER_PHONE";



    public dataBasehelper(@Nullable Context context) {
        super(context, "Customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating table with columns
        String createTableStatement= "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + COLUMN_CUSTOMER_NAME + "  TEXT , " + COLUMN_CUSTOMER_PHONE + " TEXT)";
                db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean ADDONE(customerModel customerModel){
                //adding the data into database
        SQLiteDatabase db=this. getWritableDatabase();
        ContentValues cv=new ContentValues();

        //cloms in database
        cv.put(COLUMN_CUSTOMER_NAME,customerModel.getName());
        cv.put(COLUMN_CUSTOMER_PHONE,customerModel.getPhone());


        long insert=db.insert(CUSTOMER_TABLE,null,cv);

        if (insert==1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean deleteOne(customerModel customerModel){
        //to delete the selected database

SQLiteDatabase db= this.getWritableDatabase();
String querystring="DElETE FROM "+CUSTOMER_TABLE+" Where "+COLUMN_ID+"="+customerModel.getId();
        Cursor cursor = db.rawQuery(querystring, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else {
            return false;

        }
    }

    public List<customerModel>getEveryone(){

        //taking data out from database
        //and displaying

        List<customerModel> returnlist=new ArrayList<>();
        String queryString="SELECT * From  "+CUSTOMER_TABLE;
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            //loop through the cursor and create new customer objects and put them into return list
            do {
                int CustomerId = cursor.getInt(0);      //taking data out into colms
                String CustomerName = cursor.getString(1);
                String CustomerPhone = cursor.getString(2);
                customerModel newcustomer = new customerModel(CustomerId, CustomerName,  CustomerPhone);
                returnlist.add(newcustomer);
            }while (cursor.moveToNext());
        }
        else {
            //failure do not add anything to this list
        }
        cursor.close();
        db.close();             //make sure u close the data base
        return returnlist;
    }
}

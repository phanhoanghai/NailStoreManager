package app1.phh.com.nailstoremanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import app1.phh.com.nailstoremanager.Common.Constants;

/**
 * Created by HaiPhan on 9/7/2015.
 */
public class SqlHelper extends SQLiteOpenHelper {


    private HashMap hp;

    public SqlHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.createDb(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    private void createDb(SQLiteDatabase db) {
        db.execSQL("create table contacts " + "(id integer primary key, name text,phone text,email text, street text,place text)");
        db.execSQL("create table jobs" + "id integer primary key, title text, time text");
    }

    //================ All query for jobs ==========================================================
    public boolean insertJobs(String title, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("time", time);

        db.insert("jobs", null, contentValues);
        return true;
    }

    public Cursor getDataJobs(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from jobs  where id=" + id + "", null);
        return res;
    }

    public ArrayList<String> getAllJobs() {
        ArrayList<String> array_list = new ArrayList<String>();


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from jobs", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex(Constants.JOBS_TABLE_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    //=============================== All query for Cotact =============================
    public boolean insertContact(String name, String phone, String email, String street, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        return true;
    }

    public Cursor getDataContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts where id=" + id + "", null);
        return res;
    }


    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Constants.CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact(Integer id, String name, String phone, String email, String street, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts", "id = ? ", new String[]{Integer.toString(id)});
    }

    public ArrayList<String> getAllContacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex(Constants.CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

//    public void resetContactBd() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        db.execSQL("DROP TABLE "+ Constants.CONTACTS_TABLE_NAME);
//    }
}

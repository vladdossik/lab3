package com.example.lab3;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "people_table";
   // private static final String COL1 = "ID";
    private  static final String COL2 = "name";
    private  static final String COL3= "time";
    private  static final String COL4= "surname";
    private  static final String COL5="patronymic";
    public long id=1;

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 27);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT," +
                COL3 + " TEXT," +
                COL4 + " TEXT," +
                COL5 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, time);
        long result = db.insert(TABLE_NAME, null, contentValues);
        //if date as inserted incorrectly it will return -1
        id=result;
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean replace(String surname, String name, String patronymic){
         SQLiteDatabase db = this.getWritableDatabase();
           ContentValues cv = new ContentValues();
           cv.put(COL2, name);
           cv.put(COL4,surname);
           cv.put(COL5,patronymic);;
           db.update(TABLE_NAME, cv, "ID =  " + id, null);
           return  true;

    }

    public void DeleteandAdd(int i,String surname,String name,String patronymic,String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(i==0){
            db.delete(TABLE_NAME, null, null);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3,time);
        contentValues.put(COL4,surname);
        contentValues.put(COL5,patronymic);
        long result = db.insert(TABLE_NAME, null, contentValues);
       id=result;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}

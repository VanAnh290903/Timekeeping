package com.vanh.timekeeping.ulitilies;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "staff.db";
    private static final int DATABASE_VERSON= Constants.VERSION_ROOM;

    private static final String TABLE_STAFF = "Staff";
    private static final String COLUMN_ID = "idStaff";
    private static final String COLUMN_NAME = "nameStaff";

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStaff= "CREATE TABLE " + TABLE_STAFF + "("
                +COLUMN_ID + "INTEGER PRIMARY KEY, "
                + COLUMN_NAME + "TEXT)";
        sqLiteDatabase.execSQL(createTableStaff);
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    @SuppressLint("Range")
    public String getStaffNameById(int staffId) {
        String staffName = null;
        SQLiteDatabase db =this.getReadableDatabase();

        // truy vấn
        String query = "SELECT " + COLUMN_NAME + "FROM " + TABLE_STAFF +
                " WHERE " + COLUMN_ID + " = ? ";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(staffId)});
        if (cursor.moveToFirst()) {
            staffName = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        }
        cursor.close();
        db.close();
        return staffName;
    }

}
package com.example.dbhelper13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database information
    private static final String DATABASE_NAME = "Student.db";
    private static final int DATABASE_VERSION = 2;

    // Table information
    private static final String TABLE_NAME = "student_table";

    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "EMAIL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // =========================
    // CREATE TABLE
    // =========================
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COL_1 + " INTEGER PRIMARY KEY, " +
                        COL_2 + " TEXT NOT NULL, " +
                        COL_3 + " TEXT NOT NULL)"
        );
    }

    // =========================
    // UPGRADE DATABASE
    // =========================
    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion
    ) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    // =========================
    // INSERT DATA
    // =========================
    public boolean insertData(String name, String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        /*
         * Find the smallest available ID.
         *
         * Example:
         * Existing IDs = 1, 2, 4
         * New ID = 3
         *
         * Existing IDs = 2, 3
         * New ID = 1
         */
        int newId = getSmallestAvailableId(db);

        ContentValues contentValues = new ContentValues();

        // Explicitly insert the ID
        contentValues.put(COL_1, newId);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);

        long result = db.insert(
                TABLE_NAME,
                null,
                contentValues
        );

        return result != -1;
    }

    // =========================
    // FIND SMALLEST AVAILABLE ID
    // =========================
    private int getSmallestAvailableId(SQLiteDatabase db) {

        int id = 1;

        Cursor cursor = db.rawQuery(
                "SELECT " + COL_1 +
                        " FROM " + TABLE_NAME +
                        " ORDER BY " + COL_1 + " ASC",
                null
        );

        try {

            while (cursor.moveToNext()) {

                int currentId = cursor.getInt(0);

                if (currentId == id) {

                    // ID already exists
                    id++;

                } else if (currentId > id) {

                    // Found a missing ID
                    break;
                }
            }

        } finally {
            cursor.close();
        }

        return id;
    }

    // =========================
    // GET ALL DATA
    // =========================
    public Cursor getAllData() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_NAME +
                        " ORDER BY " + COL_1 + " ASC",
                null
        );
    }

    // =========================
    // UPDATE DATA
    // =========================
    public boolean updateData(
            String id,
            String name,
            String email
    ) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);

        int result = db.update(
                TABLE_NAME,
                contentValues,
                COL_1 + " = ?",
                new String[]{id}
        );

        return result > 0;
    }

    // =========================
    // DELETE DATA
    // =========================
    public int deleteData(String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(
                TABLE_NAME,
                COL_1 + " = ?",
                new String[]{id}
        );
    }
}

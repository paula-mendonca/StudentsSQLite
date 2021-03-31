package com.paulamendonca.studentssqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class StudentSQLiteDatabase {
    Context ctx;
    public static final String DATABASE_NAME = "students.db";
    public static Integer DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public StudentSQLiteDatabase (Context ctx) {
        this.ctx = ctx;
        db = new StudentSQLiteDatabaseHelper().getWritableDatabase();
    }

    public static class StudentTable implements BaseColumns{
        public static final String TABLE_NAME = "student";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_COURSE = "course";

        public static String getSQL () {
            String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID                  + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME          + " TEXT, " +
                    COLUMN_COURSE        + " TEXT, " +
                    COLUMN_EMAIL         + " TEXT)";
            return sql;
        }
    }

    private class StudentSQLiteDatabaseHelper extends SQLiteOpenHelper {

        public StudentSQLiteDatabaseHelper () { super (ctx, DATABASE_NAME, null, DATABASE_VERSION); }

        @Override
        public void onCreate (SQLiteDatabase db) { db.execSQL(StudentTable.getSQL()); }

        @Override
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + StudentTable.TABLE_NAME);
            onCreate(db);
        }
    }

    public Long addStudent (Student s) {
        ContentValues values = new ContentValues();
        values.put(StudentTable.COLUMN_NAME, s.getName());
        values.put(StudentTable.COLUMN_COURSE, s.getCourse());
        values.put(StudentTable.COLUMN_EMAIL, s.getEmail());

        return  db.insert(StudentTable.TABLE_NAME, null, values);
    }

    public Integer removeStudent (Student s) {
        String[] args = {s.getId().toString()};
        return db.delete(StudentTable.TABLE_NAME, StudentTable._ID+ "=?", args);
    }

    public Integer updateStudent (Student s) {
        String[] args = {s.getId().toString()};
        ContentValues values = new ContentValues();
        values.put(StudentTable.COLUMN_NAME, s.getName());
        values.put(StudentTable.COLUMN_COURSE, s.getCourse());
        values.put(StudentTable.COLUMN_EMAIL, s.getEmail());

        return db.update(StudentTable.TABLE_NAME, values, StudentTable._ID + "=?", args);
    }

    public Student getStudent (Long id) {
        String[] cols = {StudentTable._ID, StudentTable.COLUMN_NAME, StudentTable.COLUMN_COURSE, StudentTable.COLUMN_EMAIL};
        String[] args = {id.toString()};
        Cursor cursor = db.query(StudentTable.TABLE_NAME, cols,StudentTable._ID + "=?", args, null, null, StudentTable._ID);

        if (cursor.getCount() != 1) {
            return null;
        }

        cursor.moveToNext();
        Student s = new Student();
        s.setId(cursor.getLong((cursor.getColumnIndex(StudentTable._ID))));
        s.setName(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_NAME)));
        s.setCourse(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_COURSE)));
        s.setEmail(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_EMAIL)));

        return s;
    }

    public List<Student> getStudents () {
        String[] cols = {StudentTable._ID, StudentTable.COLUMN_NAME, StudentTable.COLUMN_COURSE, StudentTable.COLUMN_EMAIL};
        Cursor cursor = db.query(StudentTable.TABLE_NAME, cols,null, null, null, null, StudentTable.COLUMN_NAME);
        List<Student> students = new ArrayList<>();
        Student s;

        while (cursor.moveToNext()) {
            s = new Student();
            s.setId(cursor.getLong(cursor.getColumnIndex(StudentTable._ID)));
            s.setName(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_NAME)));
            s.setCourse(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_COURSE)));
            s.setEmail(cursor.getString(cursor.getColumnIndex(StudentTable.COLUMN_EMAIL)));
            students.add(s);
        }

        return students;
    }


}

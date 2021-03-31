package com.paulamendonca.studentssqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StudentAdapter extends BaseAdapter {
    LayoutInflater inflater;
    StudentSQLiteDatabase db;

    public StudentAdapter (Context ctx, StudentSQLiteDatabase db) {
        inflater = LayoutInflater.from(ctx);
        this.db = db;
    }

    @Override
    public int getCount() {
        return db.getStudents().size();
    }

    @Override
    public Object getItem(int position) {
        return db.getStudents().get(position);
    }

    @Override
    public long getItemId(int position) {
        return db.getStudents().get(position).getId();
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        v = inflater.inflate(R.layout.student_list_item, null);
        TextView txtStudentName = v.findViewById(R.id.txtStudentName);
        txtStudentName.setText(db.getStudents().get(position).getName());
        return v;
    }
}

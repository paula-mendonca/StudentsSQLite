package com.paulamendonca.studentssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listStudent;
    StudentAdapter studentAdapter;
    StudentSQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new StudentSQLiteDatabase(getBaseContext());
        listStudent = findViewById(R.id.listStudent);
        studentAdapter = new StudentAdapter(getBaseContext(), db);
        listStudent.setAdapter(studentAdapter);

        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeToUpdate(id);
            }
        });
        listStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.removeStudent((Student) studentAdapter.getItem(position));
                studentAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void changeToAdd (View v) {
        Intent it = new Intent(getBaseContext(), AddStudent.class);
        startActivity(it);
    }

    public void changeToUpdate (Long id) {
        Intent it = new Intent(getBaseContext(), EditStudent.class);
        it.putExtra("ID", id);
        startActivity(it);;
    }
}
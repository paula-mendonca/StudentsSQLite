package com.paulamendonca.studentssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddStudent extends AppCompatActivity {
    StudentSQLiteDatabase db;
    EditText txtName, txtCourse, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        txtName = findViewById(R.id.txtName);
        txtCourse = findViewById(R.id.txtCourse);
        txtEmail = findViewById(R.id.txtEmail);

        db = new StudentSQLiteDatabase(getBaseContext());
    }

    public void addStudent (View v) {
        Student s = new Student();
        s.setName(txtName.getText().toString());
        s.setCourse(txtCourse.getText().toString());
        s.setEmail(txtEmail.getText().toString());

        db.addStudent(s);
        finish();
    }
}
package com.paulamendonca.studentssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditStudent extends AppCompatActivity {
    StudentSQLiteDatabase db;
    EditText txtName, txtCourse, txtEmail;
    Student current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        txtName = findViewById(R.id.txtName);
        txtCourse = findViewById(R.id.txtCourse);
        txtEmail = findViewById(R.id.txtEmail);

        db = new StudentSQLiteDatabase(getBaseContext());
        Long id = getIntent().getLongExtra("ID", 0);
        current = db.getStudent(id);

        txtName.setText(current.getName());
        txtCourse.setText(current.getCourse());
        txtEmail.setText(current.getEmail());
    }

    public void updateStudent (View v) {
        current.setName(txtName.getText().toString());
        current.setCourse(txtCourse.getText().toString());
        current.setEmail(txtEmail.getText().toString());
        db.updateStudent(current);
        finish();
    }
}
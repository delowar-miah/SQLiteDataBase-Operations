package com.delowar.sqlitecruddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import database.DataBaseOperation;
import model.Student;

public class MainActivity extends AppCompatActivity {

   private EditText eTName, eTPhoneNo,etEmail, eTCGPA;
   private Button saveBtn,showBtn;
   private DataBaseOperation dataBaseOperation;
   private String name,email,phone;
   private int rowId;
   private Float cgpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTName = findViewById(R.id.nameET);
        eTPhoneNo = findViewById(R.id.phoneET);
        etEmail = findViewById(R.id.mailET);
        eTCGPA = findViewById(R.id.cgpaET);
        saveBtn = findViewById(R.id.saveBtn);
        showBtn = findViewById(R.id.showBtn);

        rowId=getIntent().getIntExtra("id",0);
        name=getIntent().getStringExtra("name");
        cgpa=getIntent().getFloatExtra("cgpa",0);
        email=getIntent().getStringExtra("email");
        phone=getIntent().getStringExtra("phone");

        eTName.setText(name);
        eTCGPA.setText(String.valueOf(cgpa));
        etEmail.setText(email);
        eTPhoneNo.setText(phone);
        if (rowId>0){
            saveBtn.setText("Edit Button");
        }


        dataBaseOperation=new DataBaseOperation(this);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = false;
                String name =eTName.getText().toString().trim();
                if (name.isEmpty()){
                    eTName.setError("Name is missing!");
                    error = true;
                }else if (name.length()<6){
                    eTName.setError("Name is too short");
                    error = true;
                }else {
                    error = false;
                }

                String phoneNo = eTPhoneNo.getText().toString().trim();
                if (phoneNo.isEmpty()) {
                    eTPhoneNo.setError("Phone No is missing!");
                    error = true;
                } else if (phoneNo.length() == 11) {
                    if (phoneNo.startsWith("017") || phoneNo.startsWith("018") || phoneNo.startsWith("019") ||
                            phoneNo.startsWith("015") || phoneNo.startsWith("016") || phoneNo.startsWith("012") || phoneNo.startsWith("013") || phoneNo.startsWith("014")) {
                        error = false;
                    } else {
                        eTPhoneNo.setError("Phone no is not valid!");
                        error = true;
                    }
                } else {
                    eTPhoneNo.setError("Phone No should be 11 digit!");
                    error = true;
                }

                String email =etEmail.getText().toString().trim();
                if (email.isEmpty()){
                    etEmail.setError("Email is missing!");
                    error = true;
                }else {
                    error = false;
                }

                String cgpaStr = eTCGPA.getText().toString();
                Float cgpaF=null;
                if (cgpaStr.isEmpty()){
                    eTCGPA.setError("CGPA Missing!");
                    error=true;
                }else {
                    cgpaF= Float.valueOf(cgpaStr);
                    if (cgpaF>4.0){
                        eTCGPA.setError("CGPA should less than 4.0!");
                        error=true;
                    }
                }

                if (error){
                    Toast.makeText(MainActivity.this, "Data is not correct", Toast.LENGTH_SHORT).show();
                }else {
                    if (rowId > 0){
                        Student student=new Student(name,email,phoneNo,cgpaF);
                        boolean status=dataBaseOperation.updateStudentInformation(student,rowId);
                        if (status){
                            Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,StudentListActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(),"Update failed",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        //save
                        Student aStudent = new Student(name,phoneNo,email,cgpaF);
                        boolean status=dataBaseOperation.addStudentInformation(aStudent);
                        if (status){
                            Toast.makeText(MainActivity.this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
                            clearData();
                        }else {
                            Toast.makeText(getApplicationContext(),"Data store failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
    private void clearData() {
        eTName.setText(null);
        etEmail.setText(null);
        eTPhoneNo.setText(null);
        eTCGPA.setText(null);
    }

    public void showDetails(View view) {
        startActivity(new Intent(MainActivity.this,StudentListActivity.class));
    }
}
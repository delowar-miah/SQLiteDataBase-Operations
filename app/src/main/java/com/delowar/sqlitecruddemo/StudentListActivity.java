package com.delowar.sqlitecruddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.CustomAdapter;
import database.DataBaseOperation;
import model.Student;

public class StudentListActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    private ArrayList<Student> studentArrayList;
    private DataBaseOperation dataBaseOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        listView=findViewById(R.id.myListView);
        dataBaseOperation=new DataBaseOperation(this);
        studentArrayList=dataBaseOperation.getAllInformation();
        adapter=new CustomAdapter(this,studentArrayList);
        listView.setAdapter(adapter);
    }
}
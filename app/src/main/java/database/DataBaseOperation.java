package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.Student;

public class DataBaseOperation {
    private SQLiteDataBaseHelper sqLiteDataBaseHelper;
    private Student student;
    private SQLiteDatabase sqLiteDatabase;

    public DataBaseOperation(Context context){
        sqLiteDataBaseHelper=new SQLiteDataBaseHelper(context);
    }
    public void open(){
        sqLiteDatabase=sqLiteDataBaseHelper.getWritableDatabase();
    }
    public void close(){
        sqLiteDatabase.close();
    }
    public boolean addStudentInformation(Student student){
        this.open();
        ContentValues values=new ContentValues();
        values.put(SQLiteDataBaseHelper.COL_STD_NAME,student.getName());
        values.put(SQLiteDataBaseHelper.COL_STD_EMAIL,student.getEmail());
        values.put(SQLiteDataBaseHelper.COL_STD_PHONE,student.getPhone());
        values.put(SQLiteDataBaseHelper.COL_STD_CGPA,student.getCgpa());
        long returnValue=sqLiteDatabase.insert(SQLiteDataBaseHelper.TABLE_NAME,null,values);
        this.close();
        if (returnValue > 0){
            return true;
        }else {
            return false;
        }
    }
    public ArrayList<Student> getAllInformation(){
          ArrayList<Student>studentArrayList=new ArrayList<>();
          this.open();
          Cursor cursor=sqLiteDatabase.query(SQLiteDataBaseHelper.TABLE_NAME,null,null,null,null,null,null);
          cursor.moveToFirst();
          if (cursor != null && cursor.getCount() > 0){
              for (int i=0;i<cursor.getCount();i++){
                  int id=cursor.getInt(cursor.getColumnIndex(SQLiteDataBaseHelper.COL_ID));
                  String name=cursor.getString(cursor.getColumnIndex(SQLiteDataBaseHelper.COL_STD_NAME));
                  String email=cursor.getString(cursor.getColumnIndex(SQLiteDataBaseHelper.COL_STD_EMAIL));
                  String phone=cursor.getString(cursor.getColumnIndex(SQLiteDataBaseHelper.COL_STD_PHONE));
                  Float cgpa=cursor.getFloat(cursor.getColumnIndex(SQLiteDataBaseHelper.COL_STD_CGPA));
                  studentArrayList.add(new Student(name,email,phone,cgpa,id));
                  cursor.moveToNext();
              }
          }
        this.close();
        cursor.close();
        return studentArrayList;
    }
    public boolean deleteStudentInformation(int rowId){
        this.open();
        int returnValue=sqLiteDatabase.delete(SQLiteDataBaseHelper.TABLE_NAME,SQLiteDataBaseHelper.COL_ID+" =?",new String[]
                {Integer.toString(rowId)});
        if (returnValue >0 ){
            return true;
        }else {
            return false;
        }
    }
    public boolean updateStudentInformation(Student student,int rowId){
        this.open();
        ContentValues values=new ContentValues();
        values.put(SQLiteDataBaseHelper.COL_STD_NAME,student.getName());
        values.put(SQLiteDataBaseHelper.COL_STD_EMAIL,student.getEmail());
        values.put(SQLiteDataBaseHelper.COL_STD_PHONE,student.getPhone());
        values.put(SQLiteDataBaseHelper.COL_STD_CGPA,student.getCgpa());
        int returnValue=sqLiteDatabase.update(SQLiteDataBaseHelper.TABLE_NAME,values,SQLiteDataBaseHelper.COL_ID+" =?"
        ,new String[]{Integer.toString(rowId)});
        this.close();
        if (returnValue > 0){
            return true;
        }else {
            return false;
        }
    }
}

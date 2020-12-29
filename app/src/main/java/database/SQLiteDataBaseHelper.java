package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final int DATABASE_VERSION=1;
    public SQLiteDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static final String TABLE_NAME="student_table";
    public static final String COL_ID="Id";
    public static final String COL_STD_NAME="Name";
    public static final String COL_STD_EMAIL="Email";
    public static final String COL_STD_PHONE="Phone";
    public static final String COL_STD_CGPA="CGPA";
    public static final String CREATE_TABLE="create table "+TABLE_NAME+"("+COL_ID+" integer primary key, "
            + COL_STD_NAME+" text,"
            +COL_STD_EMAIL+" text, "
            +COL_STD_PHONE+" text, "
            +COL_STD_CGPA+" text );";
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
          sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
          onCreate(sqLiteDatabase);
    }
}

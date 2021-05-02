package gachon.mpclass.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import gachon.mpclass.databasetest.R;
//이 부분은 결과창을 보기 위해, 만들어 놓은 activity.
public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;


    SqliteOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create the database
        SqliteManager sqm=new SqliteManager(this,"User.db");//get the Command from SqliteManager
        //sqm.insert("johnbas","12345","john",179,67,"Female");
        sqm.select();
        Log.i("db1"," "+sqm.GetID());




    }



}

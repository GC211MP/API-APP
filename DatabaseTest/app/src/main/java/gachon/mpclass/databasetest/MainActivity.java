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

    TextView tv;
    SqliteOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.td);
        SqliteManager sqm=new SqliteManager(this,"User.db");//get the Command from SqlitManager
        sqm.insert("jonny","34567i",190,120,"Male"); //test result
        sqm.select();
        //create the database

    }

    public void println(String data)
    {
        tv.append(data+"\n");
    }

}

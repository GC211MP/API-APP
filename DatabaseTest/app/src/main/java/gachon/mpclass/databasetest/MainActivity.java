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

import java.util.ArrayList;

import gachon.mpclass.databasetest.R;
//이 부분은 결과창을 보기 위해, 만들어 놓은 activity.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SqliteDto sql= new SqliteDto("jang","1234","haein",178,64,"Male"); //testing
       // StoreManager.getInstance().EnrollSqlite(this, sql); //testing value
        //StoreManager.getInstance().ReadSqlite(this,"jang"); //testing
        //Log.i("db1"," "+StoreManager.getInstance().UpdateSqlite(this, sql, "jim","12345"));//testing
        Button a = findViewById(R.id.mdb);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //new DataDAO().read("c_date", true);
                    }
                }).start();


            }
        });




//        Log.i("db1"," "+sqm.GetID());

    }





}



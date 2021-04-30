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
    String tableName;
    TextView tv;
    SqliteOpenHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.td);
        helper=new SqliteOpenHelper(MainActivity.this,"User.db",null,1);
        //create the database
        insert("jang","12345",156,56,"Male");
        insert("John","12345",179,46,"Female");
        insert("Kim","12233",169,53,"Male");
        update("jang",189,79);
        delete("Kim");
        select();
    }
    //insert the user //새로 만든 user 삽입.
    public void insert(String id,String pwd,int ht, int wt, String sex)
    {
        database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("password",pwd);
        values.put("height",ht);
        values.put("weight",wt);
        values.put("sex",sex);
        database.insert("user",null,values);
        Log.i("db1","Success");
    }
    //update the user information, 키와 몸무게 수정 가능.
    public void update(String id,int ht, int wt)
    {
        database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("height",ht);
        values.put("weight",wt);
        database.update("user",values,"id=?",new String[]{id});
        Log.i("db1","Success update");
    }
    //delete the user
    public void delete(String id)
    {
        database=helper.getWritableDatabase();
        database.delete("user","id=?",new String[]{id});
        Log.i("db1",id+"정상적으로 삭제 되었습니다.");
    }
    // print the all user. we can get the data using different command
    //필요한 조건으로 충분히 가능.
    public void select()
    {
        database=helper.getWritableDatabase();
        Cursor c=database.rawQuery("select * from user",null);
        while(c.moveToNext())
        {
            String id=c.getString(0);
            String pw=c.getString(1);
            int ht=c.getInt(2);
            int wt=c.getInt(3);
            String sex=c.getString(4);
            Log.i("db1","id: "+id+" "+pw+" "+ht+" "+wt+ " "+sex);
        }
    }
    public void println(String data)
    {
        tv.append(data+"\n");
    }

}

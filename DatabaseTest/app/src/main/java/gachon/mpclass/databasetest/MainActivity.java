package gachon.mpclass.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static java.sql.DriverManager.println;


public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database;
    String tableName;
    TextView tv;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.td);
        button1=(Button)findViewById(R.id.mdb);
        button2=(Button)findViewById(R.id.ddl);
        button3=(Button)findViewById(R.id.ddl2);
        button4=(Button)findViewById(R.id.dml);
        button5=(Button)findViewById(R.id.dml2);
        button6=(Button)findViewById(R.id.select);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDatabase("mydb");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTableUser();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTableData();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enroll("Flysamsung", "John","1234");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordEnroll(2,1,100);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeQuery();
            }
        });
    }
    public void createDatabase(String name)
    {
        println("createDatabase 호출됨");
        database=openOrCreateDatabase(name,MODE_PRIVATE,null);
        println("데이터베이스 생성함: "+name);
    }
    public void createTableUser()
    {
        println("createTable 호출됨");
        if(database==null)
        {
            println("데이터베이스 먼저 생성 필요");
            return;
        }
        String usql="create table if not exists USER ( idx INTEGER PRIMARY KEY autoincrement , user_id  VARCHAR(32) not null , user_name VARCHAR(32) not null, c_date TIMESTAMP default CURRENT_TIMESTAMP , user_password VARCHAR(128) not null);";
        database.execSQL(usql);
        println("USER 테이블 생성함 ");
    }
    public void createTableData()
    {
        println("호출됨");
        if(database==null)
        {
            println("데이터베이스 먼저 생성 필요");
            return;
        }
        String dsql="create table if not exists DATA ( idx INTEGER PRIMARY KEY autoincrement , user_idx INTEGER  not null , stage_id INTEGER not null, elapsed_time INTEGER not null, FOREIGN KEY(user_idx) REFERENCES USER(idx));";
        database.execSQL(dsql);
        println("DATA 테이블 생성함");

    }
    public void Enroll(String id, String name, String pwd)
    {
        //조건 필요 아이디 중복 등 같은 부분.
        database.execSQL("insert into USER (user_id, user_name, user_password) values ("+"'"+id+"'"+", "+"'"+name+"'"+", "+"'"+pwd+"'"+");");
        println("가입 완료 됨");
    }
    public void RecordEnroll(int idx, int st_num, int time)
    {
        //idx=전역변수
        //조건 필요
        database.execSQL("insert into DATA (user_idx, stage_id, elapsed_time) values ("+idx+", "+st_num+", "+time+");");
        println("기록 됨");
    }
    public void executeQuery()
    {
        println("executionQuery 호출됨");
        Cursor cursor=database.rawQuery("select *  from DATA",null);
        int count=cursor.getCount();
        for(int i=0;i<count;i++)
        {
            cursor.moveToNext();
            int id=cursor.getInt(0);
            int uid=cursor.getInt(1);
            int sid=cursor.getInt(2);
            int time=cursor.getInt(3);
            println("레코드 "+id+" "+uid+" "+sid+" "+time);
        }
    }
    public void println(String data)
    {
        tv.append(data+"\n");
    }

}
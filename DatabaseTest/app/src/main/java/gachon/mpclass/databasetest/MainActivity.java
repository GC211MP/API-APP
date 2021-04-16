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

        DAO test=new DAO();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.createDatabase("mydb");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               test.createTableUser();
                test.createTableData();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    test.Enroll("Flysamsung", "John","1234");
                    test.Enroll("john", "jameson","jang14");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                test.RecordEnroll(1,1,100);
                test.RecordEnroll(1,2,500);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.executeQuery1();
            }
        });

    }
    //미완성
    public class DAO {
        //로그인 되어 있는 id?
        String curid=null;
        int curindex=0;
        public void createDatabase(String name) {
            // make database
            println("createDatabase 호출됨");
            database = openOrCreateDatabase(name, MODE_PRIVATE, null);
            println("데이터베이스 생성함: " + name);
        }

        public void createTableUser() {
            //make user table
            println("createTable 호출됨");
            if (database == null) {
                println("데이터베이스 먼저 생성 필요");
                return;
            }
            String usql = "create table if not exists USER ( idx INTEGER PRIMARY KEY autoincrement , user_id  VARCHAR(32) not null , user_name VARCHAR(32) not null, c_date TIMESTAMP default CURRENT_TIMESTAMP , user_password VARCHAR(128) not null);";
            database.execSQL(usql);
            println("USER 테이블 생성함 ");
        }

        public void createTableData() {
            // make record table
            println("호출됨");
            if (database == null) {
                println("데이터베이스 먼저 생성 필요");
                return;
            }
            String dsql = "create table if not exists DATA ( idx INTEGER PRIMARY KEY autoincrement , user_idx INTEGER  not null , stage_id INTEGER not null, elapsed_time INTEGER not null, FOREIGN KEY(user_idx) REFERENCES USER(idx));";
            database.execSQL(dsql);
            println("DATA 테이블 생성함");

        }

        public void Enroll(String id, String name, String pwd) {
            //조건 필요 아이디 중복 등 같은 부분.
            //회원가입 부분.
            //아이디 중복 부분이랑, 그런 부분 수정필요.
            database.execSQL("insert into USER (user_id, user_name, user_password) values (" + "'" + id + "'" + ", " + "'" + name + "'" + ", " + "'" + pwd + "'" + ");");
            println("가입 완료 됨");
        }
        //회원 정보 수정 부분 필요(Ex)
        public void ModifyName(String name)
        {
            database.execSQL("update USER SET user_name="+"'"+name+"'"+ " where user_id like"+"'"+curid+"'");
        }
        //비밀번호 변경
        public void Modifypwd(String pwd)
        {;
            database.execSQL("update USER SET password="+"'"+pwd+"'"+"where user_id like"+"'"+curid+"'");
        }
        public void RecordEnroll(int idx, int st_num, int time) {
            //idx=전역변수
            //조건 필요
            //달리기 완료 후, 기록 넣기, 같은 stage를 돌았을 경우에는, update를 해주어야함.
            database.execSQL("insert into DATA (user_idx, stage_id, elapsed_time) values (" + idx + ", " + st_num + ", " + time + ");");
            //up
            println("기록 됨");
        }
        public void Recordupdate(int st_num, int time)
        {
            //조건 필요(기록 갱신인지, 기록에 못 미쳣는지)
            database.execSQL("update DATA SET stage_id=st_num, elapsed_time=time where user_idx="+curindex);
        }
        public void executeQuery1() {
            println("executionQuery 호출됨");
            Cursor cursor = database.rawQuery("select *  from DATA", null);
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                int id = cursor.getInt(0);
                int uid = cursor.getInt(1);
                int sid = cursor.getInt(2);
                int time = cursor.getInt(3);
                println("레코드 " + id + " " + uid + " " + sid + " " + time);
            }
        }
        // data 파일 전체 출력.
        public void executeQuery2() {
            Cursor cursor = database.rawQuery("select * from DATA",null);
        }
        //랭킹 나오는 query, stage 1일경우 출력.

        public void executeQuery3()
        {
            Cursor cursor=database.rawQuery("select id, c_data, elapsed_time from DATA,USER where USER.idx=DATA.user_indx AND DATA.stage_id=1 order by elapsed_time ASC",null);
        }
    }
    //user table's DTO
    public class DTO {
        private String id;
        private String pwd;
        private String name;
        public String getId()
        {
            return id;
        }
        public String getPwd()
        {
            return pwd;
        }
        public String getName()
        {
            return name;
        }
        public void setId(String id)
        {
            this.id=id;
        }
        public void setPwd(String password)
        {
            this.pwd=password;
        }
        public void setName(String Name)
        {
            this.name=Name;
        }
    }
    // DATA's DTO
    public class DDTO
    {
        private int user_index;
        private int sid;
        private int etime;
        public int getUser_index()
        {
            return user_index;
        }
        public int getSid()
        {
            return sid;
        }
        public int getEtime()
        {
            return etime;
        }
        public void setUser_index(int idx)
        {
            this.user_index=idx;
        }
        public void setSid(int stid)
        {
            this.sid=stid;
        }
        public void setEtime(int time)
        {
            this.etime=time;
        }
    }
    public void println(String data)
    {
        tv.append(data+"\n");
    }

}
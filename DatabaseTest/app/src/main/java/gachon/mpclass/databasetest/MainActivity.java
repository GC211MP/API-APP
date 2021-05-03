package gachon.mpclass.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import static java.sql.DriverManager.println;

//이 부분은 결과창을 보기 위해, 만들어 놓은 activity.
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
    Button button7;
    Button button8;
    Button button9;
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
        button6=(Button)findViewById(R.id.rank);
        button7=(Button)findViewById(R.id.modifyid);
        button8=(Button)findViewById(R.id.modifypw);
        button9=(Button)findViewById(R.id.update);
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
                    test.Enroll("play","Jansen","6278");
                    test.Enroll("fly","kershaw","123456");
                    test.Enroll("tothe","bauer","12345678");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                test.RecordEnroll(1,1,100);
                test.RecordEnroll(2,1,300);
                test.RecordEnroll(3,1,20);
                test.RecordEnroll(4,1,90);
                test.RecordEnroll(5,1,50);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.executeQuery1();
                test.executeQuery2();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.Ranking1();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               test.ModifyName("bellinger");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.Mdpwd("7924"); //비밀번호 변경


            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.Recordupdate(1,150); //기록 갱신 테스트
            }
        });
    }
    //미완성
    public class DAO {
        //로그인 되어 있는 id?
        int curindex=2; //아이디가 아니라 현재 접속한 index로 표현
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
        //회원 게스트로 등록할 경우, 기록이 된다.
        public void Enroll(String id, String name, String pwd) {
            //조건 필요 아이디 중복 등 같은 부분.
            //회원가입 부분.
            //아이디 중복 부분이랑, 그런 부분 수정필요.(idx로 해서, id중복은 가능한지 확인 필요.
               database.execSQL("insert into USER (user_id, user_name, user_password) values (" + "'" + id + "'" + ", " + "'" + name + "'" + ", " + "'" + pwd + "'" + ");");
               println("가입 완료 됨");
           }
        //회원 정보 수정 부분 필요(Ex)
        //아이디로 조회해서, 수정을 할 것인지. idx로 수정할 것인지 문제점 봉착, id가 P.K가 아니다 보니, 부분 수정이 필요.
        public void ModifyName(String name)
        {
            Cursor cursor=database.rawQuery("select idx from USER where idx ="+curindex,null);
            cursor.moveToNext();
            int idx=cursor.getInt(0);
            if(curindex==idx)
            {
                database.execSQL("update USER SET user_name=" + "'" + name + "'" + " where idx = "+curindex );
            }
            else {
                println("가입자가 아님"); //수정 필요.
            }
        }
        //비밀번호 변경 //조건 필요, idx로 인증을 하는 것인지, id로 인증을 하는 것인지. 현재 id값이 중복이 허용이 되어 있기 때문에,
        public void Mdpwd(String pwd)
        {
            Cursor cursor=database.rawQuery("select idx from USER where idx ="+curindex,null);
            cursor.moveToNext();
            int idx=cursor.getInt(0);
            if(curindex==idx) {
                database.execSQL("update USER SET user_password=" + "'" + pwd + "'" + "where idx="+curindex );
            }
            else {
                println("가입자가 아님"); //수정 필요.
            }
        }
        public void RecordEnroll(int idx, int st_num, int time) {
            //idx=전역변수
            //조건 필요
            //달리기 완료 후, 기록 넣기, 같은 stage를 돌았을 경우에는, update를 해주어야함.
            database.execSQL("insert into DATA (user_idx, stage_id, elapsed_time) values (" + idx + ", " + st_num + ", " + time + ");");
            //up
            println("기록 됨");
        }
        //기록 업데이트 되는 경우,
        public void Recordupdate(int st_num, int time)
        {

            // 그 전에 기록이 있는 지 확인
            Cursor cursor=database.rawQuery("select exists(select * from DATA where user_idx="+curindex+" and stage_id="+st_num+")",null);
            cursor.moveToNext();
            int nw=cursor.getInt(0);
            // 그전에 기록이 있으면 1을 return 그 전에 기록이 없으면, return 0
            //조건 필요(기록 갱신인지 new=1, 기록에 못 미쳣는지 new=0) //예시
           if(nw==1)
           {
               Cursor c1=database.rawQuery("select elapsed_time from DATA where user_idx="+curindex+" and stage_id="+st_num,null);
               c1.moveToNext();
               int extime=c1.getInt(0);
               if(extime>time) //기록을 갱신했을 경우, 업데이트
               {
                   database.execSQL("update DATA SET elapsed_time="+time+" where user_idx="+curindex+" and stage_id="+st_num);
               }
               else //기존의 기록이 빠를경우
               {
                   println("기존의 기록이 더 빠릅니다");
               }
            }
            // 처음의 기록일 경우, 기존 기록이 없을 경우
            else
           {
                RecordEnroll(curindex, st_num, time);
           }
        }
        //전체 데이터 정보 출력.
        public void executeQuery1() {
            Cursor cursor = database.rawQuery("select *  from DATA", null);
            int count = cursor.getCount();
            println("레코드");
            for (int i = 0; i < count; i++) {
                cursor.moveToNext();
                int id = cursor.getInt(0);
                int uid = cursor.getInt(1);
                int sid = cursor.getInt(2);
                int time = cursor.getInt(3);
                println("ID:  "+ id +" Userindex:  " + uid +" STAGE LEVEL: " +sid+" Elapsed time" + time);
            }
        }
        // USER 파일 전체 출력.
        public void executeQuery2() {
            Cursor cursor = database.rawQuery("select * from USER",null);
            int count=cursor.getCount();
            println("회원정보");
            for(int i=0; i<count;i++)
            {
                cursor.moveToNext();
                int index=cursor.getInt(0);
                String id=cursor.getString(1);
                String name=cursor.getString(2);
                String time=cursor.getString(3);
                String pw=cursor.getString(4);
                println("회원번호"+index+" "+" ID: "+id+" Name: "+name+" 가입일: "+time+" password: "+pw);
            }
        }
        //랭킹 나오는 query, stage 1,2,3,4일경우 출력.
        //query만 구성.
        public void Ranking1()
        {
            Cursor cursor=database.rawQuery("select user_id, c_date, elapsed_time from DATA,USER where USER.idx=DATA.user_idx AND DATA.stage_id=1 order by elapsed_time ASC",null);
            //커서에서 데이터 뽑아낸다음에, UI에 띄워주기만 하면 된다.
            int c=cursor.getCount();
            println("STAGE 1 Ranking");
            for(int i=0;i<c;i++)
            {
                cursor.moveToNext();
                String rid=cursor.getString(0);
                String ctime=cursor.getString(1);
                String etime=cursor.getString(2);
                println((i+1)+"등"+ " "+" ID :"+rid+" 가입일 : "+ctime+" 주파기록: "+etime);
            }
        }
        public void Ranking2()
        {
            Cursor cursor=database.rawQuery("select user_id, c_date, elapsed_time from DATA,USER where USER.idx=DATA.user_idx AND DATA.stage_id=2 order by elapsed_time ASC",null);
            //커서에서 데이터 뽑아낸다음에, UI에 띄워주기만 하면 된다.
            int c=cursor.getCount();
            println("STAGE 2 Ranking");
            for(int i=0;i<c;i++)
            {
                cursor.moveToNext();
                String rid=cursor.getString(0);
                String ctime=cursor.getString(1);
                String etime=cursor.getString(2);
                println((i+1)+"등"+ " "+"ID :"+rid+"가입일 : "+ctime+"주파기록: "+etime);
            }
        }
        public void Ranking3()
        {
            Cursor cursor=database.rawQuery("select user_id, c_date, elapsed_time from DATA,USER where USER.idx=DATA.user_idx AND DATA.stage_id=3 order by elapsed_time ASC",null);
            //커서에서 데이터 뽑아낸다음에, UI에 띄워주기만 하면 된다.
            int c=cursor.getCount();
            println("STAGE 2 Ranking");
            for(int i=0;i<c;i++)
            {
                cursor.moveToNext();
                String rid=cursor.getString(0);
                String ctime=cursor.getString(1);
                String etime=cursor.getString(2);
                println((i+1)+"등"+ " "+" ID :"+rid+" 가입일 : "+ctime+" 주파기록: "+etime);
            }
        }
        public void Ranking4()
        {
            Cursor cursor=database.rawQuery("select user_id, c_date, elapsed_time from DATA,USER where USER.idx=DATA.user_idx AND DATA.stage_id=4 order by elapsed_time ASC",null);
            //커서에서 데이터 뽑아낸다음에, UI에 띄워주기만 하면 된다.
            int c=cursor.getCount();
            println("STAGE 2 Ranking");
            for(int i=0;i<c;i++)
            {
                cursor.moveToNext();
                String rid=cursor.getString(0);
                String ctime=cursor.getString(1);
                String etime=cursor.getString(2);
                println((i+1)+"등"+ " "+" ID :"+rid+" 가입일 : "+ctime+" 주파기록: "+etime);
            }
        }
    }
    //user table's DTO, 수정 필요, dao에 따라서, 사용 가능.
    public class DTO {
        private String id;
        private String pwd;
        private String name;
        private int idx;
        private Timestamp t;
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
        public void setIdx(int idx)
        {
            this.idx=idx;
        }
        public int getIdx()
        {
            return idx;
        }
        public Timestamp getT()
        {
            return t;
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
package gachon.mpclass.databasetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteManager {
    SQLiteDatabase database;
    SqliteOpenHelper helper;
    public SqliteManager(Context context, String name) {
        helper = new SqliteOpenHelper(context, name, null, 1);
    }
    public static SqliteManager open(Context context, String name) {
        return new SqliteManager(context, name);
    }
    //write agent info 이후에 가능
    public void insert(String id,String pwd,int ht, int wt, String sex)
    {
        database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("password",pwd);
        values.put("height",ht);
        values.put("weight",wt);
        values.put("sex",sex);
        values.put("distance",0);
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
    //updat the distance. when finishing the running
    public void updatedis(String id,float dis)
    {
        database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        //get the previous distance
        Cursor c=database.rawQuery("select distance from user where id="+"'"+id+"'",null);
        c.moveToNext();
        float prevdis=c.getFloat(0);
        float resultdis=prevdis+dis;
        // sum of previous distance+ new distance=>final distance
        values.put("distance",resultdis);
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
            float dis=c.getFloat(5);
            Log.i("db1","id: "+id+" "+pw+" "+ht+" "+wt+ " "+sex+" "+dis);
        }
    }
    //check the id is exists
    public boolean checkID(String tid)
    {
        database=helper.getWritableDatabase();
        Cursor c=database.rawQuery("select exists(select"+"'"+tid+"'"+" from user where id="+"'"+tid+"'"+")" ,null );
        c.moveToNext();
        int check=c.getInt(0);
        if(check==1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //로그인 기능이 구현되야 할지 잘 몰라서 일단 구현.
    public boolean Login(String tid, String tpw)
    {
        database=helper.getWritableDatabase();
        Cursor c=database.rawQuery("select password from user where id="+"'"+tid+"'",null );
        c.moveToNext();
        String checkpw=c.getString(0);
        if(tpw==checkpw)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // Get the login user's height
    public int getcurHeight(String cid)
    {
        database=helper.getWritableDatabase();
        Cursor c=database.rawQuery("select height from user where id="+"'"+cid+"'",null );
        c.moveToNext();
        int cheight=c.getInt(0);
        return cheight;
    }
    //Get the login user's weight
    public int getcurWeight(String cid)
    {
        database=helper.getWritableDatabase();
        Cursor c=database.rawQuery("select weight from user where id="+"'"+cid+"'",null );
        c.moveToNext();
        int cweight=c.getInt(0);
        return cweight;
    }
    //Get the login user's sex
    public String getcurSex(String cid)
    {
        database=helper.getWritableDatabase();
        Cursor c=database.rawQuery("select sex from user where id="+"'"+cid+"'",null );
        c.moveToNext();
        String csex=c.getString(0);
        return csex;
    }

}

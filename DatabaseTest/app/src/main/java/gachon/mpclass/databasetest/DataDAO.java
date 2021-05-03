package gachon.mpclass.databasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.transform.Result;

//->게임이 끝나면, (user_id, user_name, stage, distance, 칼로리) 서버에 보낸다.
public class DataDAO {
    //Enroll the data
    public void Create(DataDTO dt) throws ClassNotFoundException, SQLException
    {
        // class.forName("");
        Connection conn= DriverManager.getConnection("");
        PreparedStatement ps=conn.prepareStatement("insert into DATA(user_index, stage_id, distance, calories, score) values(?,?,?,?,?)");
        ps.setInt(1, dt.getUser_index());
        ps.setInt(2, dt.getStage_id());
        ps.setInt(3, dt.getDistance());
        ps.setFloat(4, dt.getCalories());
        ps.setInt(5, dt.getScore());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    // Rank 읽어오려면, DTO에 있는 정보로, 부족 그리고 리스트로 가져와야하기 때문에, 어떻게 만들어야될지 모르겟다.
    public ArrayList<Rank> Read(int stage) throws ClassNotFoundException, SQLException
    {
        ArrayList<Rank> rk=new ArrayList<Rank>();
        // class.forName("");
        Connection conn= DriverManager.getConnection("");
        PreparedStatement ps=conn.prepareStatement("select user_id, stage_id, distance, calories, score from USER, DATA WHERE USER.index=DATA.user_index and DATA.stage_id=? order by desc ");
        ps.setInt(1, stage);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            String rid=rs.getString("user_id");
            int rsid=rs.getInt("stage_id");
            int dis=rs.getInt("distance");
            float calories=rs.getFloat("calories");
            int score=rs.getInt("score");
            Rank rank=new Rank(rid, rsid, dis, calories,score);
            rk.add(rank);
        }
        rs.close();
        conn.close();
        return rk;
    }
    public void Update(DataDTO dt, int score) throws ClassNotFoundException, SQLException
    {
        // class.forName("");
        Connection conn= DriverManager.getConnection("");
        PreparedStatement ps=conn.prepareStatement("select score from DATA where user_index=?");
        ps.setInt(1, dt.getUser_index());
        ResultSet rs=ps.executeQuery();
        int prevscore= rs.getInt("score");
        if(prevscore>score) //기존 점수가 더 높을 경우
        {
            return;
        }
        else //기록 갱신용
        {
           ps=conn.prepareStatement("update DATA set score=? where user_index=?");
           ps.setInt(1, score);
           ps.setInt(2, dt.getUser_index());
           ps.executeUpdate();
           ps.close();
           conn.close();
        }
    }
    //미구현.
    public void Delete(DataDTO dt) throws ClassNotFoundException, SQLException
    {

    }



}


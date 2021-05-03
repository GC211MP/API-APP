package gachon.mpclass.databasetest;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// WriteAgent 가 끝나면, (id, password, user_name, 성별, 키, 몸무게) 를 서버에 보낸다.
public class UserDAO {
    public void Create(UserDTO user) throws ClassNotFoundException, SQLException
    {
        //connecting 하는 방식은 수정이 필요하다.
       // class.forName("");
        Connection conn= DriverManager.getConnection("");
        PreparedStatement ps=conn.prepareStatement("insert into USER(user_id, user_name, user_password) values(?,?,?)");
        ps.setString(1, user.getUser_id());
        ps.setString(2, user.getUser_name());
        ps.setString(3, user.getPassword());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    public UserDTO Read(String id) throws ClassNotFoundException, SQLException
    {
        // class.forName("");
        Connection conn= DriverManager.getConnection("");
        PreparedStatement ps=conn.prepareStatement("select * from USER where id=?");
        ps.setString(1, id);
        ResultSet rs=ps.executeQuery();
        rs.next();
        UserDTO user=new UserDTO();
        user.setIndex(rs.getInt("index"));
        user.setUser_id(rs.getString("user_id"));
        user.setUser_name(rs.getString("user_name"));
        user.setC_date(rs.getString("c_date"));
        user.setUser_password(rs.getString("user_password"));
        rs.close();
        ps.close();
        conn.close();
        return user;
    }
    public void Update(UserDTO user, String name, String pw) throws ClassNotFoundException, SQLException
    {
        // class.forName("");
        Connection conn= DriverManager.getConnection("");
        PreparedStatement ps=conn.prepareStatement("update USER set user_name=? and user_password=? where user_id=?");
        ps.setString(1, name);
        ps.setString(2, pw);
        ps.setString(3, user.getUser_id());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    public void Delete(UserDTO user) throws ClassNotFoundException, SQLException
    {
        // class.forName("");
        Connection conn= DriverManager.getConnection("");
        PreparedStatement ps=conn.prepareStatement("delete from USER where user_id=?");
        ps.setString(1, user.getUser_id());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }


    }




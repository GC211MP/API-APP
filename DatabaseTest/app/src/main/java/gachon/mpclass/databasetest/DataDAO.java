package gachon.mpclass.databasetest;

import java.sql.SQLException;
//->게임이 끝나면, (user_id, user_name, stage, distance, 칼로리) 서버에 보낸다.
public class DataDAO {
    public void Create(DataDTO dt) throws ClassNotFoundException, SQLException
    {

    }
    public DataDTO Read(String id) throws ClassNotFoundException, SQLException
    {
        DataDTO dto=new DataDTO();
        return dto;

    }
    public void Update(DataDTO dt) throws ClassNotFoundException, SQLException
    {

    }
    public void Delete(DataDTO dt) throws ClassNotFoundException, SQLException
    {

    }



}


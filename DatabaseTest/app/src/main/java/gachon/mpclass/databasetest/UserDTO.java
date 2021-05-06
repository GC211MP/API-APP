package gachon.mpclass.databasetest;


// WriteAgent 가 끝나면, (id, password, user_name, 성별, 키, 몸무게) 를 서버에 보낸다.
public class UserDTO {

    // Attribute
    private int index;
    private String user_id;
    private String user_name;
    private String c_date;
    private String user_password;

    // Getter
    public Integer getIndex()
        {
            return index;
        }
    public String getUser_id()
        {
            return user_id;
        }
    public String getUser_name()
        {
            return user_name;
        }
    public String getC_date()
        {
            return c_date;
        }
    public String getPassword()
        {
            return user_password;
        }

    // Setter
    public void setIndex(int idx) {
        this.index = idx;
    }
    public void setUser_id(String id)
    {
        this.user_id=id;
    }
    public void setUser_name(String s)
    {
        this.user_name=s;
    }
    public void setC_date(String time)
    {
        this.c_date=time;
    }
    public void setUser_password(String pw){this.user_password=pw;}

}

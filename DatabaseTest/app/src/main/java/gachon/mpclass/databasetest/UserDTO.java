package gachon.mpclass.databasetest;


// WriteAgent 가 끝나면, (id, password, user_name, 성별, 키, 몸무게) 를 서버에 보낸다.
public class UserDTO {

    // Attribute
    private String user_id;
    private String user_name;
    private String user_password;

    public UserDTO(String id, String name, String pw)
    {
        this.user_id=id;
        this.user_name=name;
        this.user_password=pw;
    }
    public UserDTO()
    {

    }

    // Getter

    public String getUser_id()
        {
            return user_id;
        }
    public String getUser_name()
        {
            return user_name;
        }
    public String getPassword()
        {
            return user_password;
        }

    // Setter

    public void setUser_id(String id)
    {
        this.user_id=id;
    }
    public void setUser_name(String s)
    {
        this.user_name=s;
    }
    public void setUser_password(String pw){this.user_password=pw;}

}

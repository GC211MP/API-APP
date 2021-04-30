package gachon.mpclass.databasetest;


// WriteAgent 가 끝나면, (id, password, user_name, 성별, 키, 몸무게) 를 서버에 보낸다.
public class UserDTO {
        private String id;
        private String pwd;
        private String name;
       private String sex;
       private int ht;
       private  int wt;
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
        public String getSex()
        {
            return sex;
        }
        public int getWt()
        {
            return wt;
        }
        public int getHt()
        {
            return ht;
        }
    public void setId(String id) {
        this.id = id;
    }
    public void setPwd(String pw)
    {
        this.pwd=pw;
    }
    public void setSex(String s)
    {
        this.sex=s;
    }
    public void setName(String nm)
    {
        this.name=nm;
    }
    public void setHt(int h)
    {
        this.ht=h;
    }
    public void setWt(int w)
    {
        this.wt=w;
    }
}

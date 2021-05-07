package gachon.mpclass.databasetest;

public class SqliteDto {
    private String id;
    private String password;
    private String name;
    private int height;
    private int weight;
    private String sex;
    public SqliteDto() {

    }
    public SqliteDto(String uid, String pw, String name, int ht, int wt, String sex) {
        this.id=uid;
        this.password=pw;
        this.name=name;
        this.height=ht;
        this.weight=wt;
        this.sex=sex;
    }
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
    public Integer getHeight() {
        return height;
    }
    public Integer getWeight() {
        return weight;
    }

    public String getSex() {
        return sex;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

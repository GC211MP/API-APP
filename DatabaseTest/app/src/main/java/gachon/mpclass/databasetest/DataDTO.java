package gachon.mpclass.databasetest;


//->게임이 끝나면, (user_id, user_name, stage, distance,score, 칼로리) 서버에 보낸다.
//추가 수정이 필요하다.
public class DataDTO {

    // Attribute
    private int index;
    private int user_index;
    private int stage_id;
    private int distance;
    private double calories;
    private int score;

    // Getter
    public Integer getIndex()
    {
        return index;
    }
    public Integer getUser_index()
    {
        return user_index;
    }
    public  Integer getStage_id()
    {
        return stage_id;
    }
    public Integer getDistance()
    {
        return distance;
    }
    public double getCalories()
    {
        return calories;
    }
    public int getScore()
    {
        return score;
    }

    // Setter
    public void setIndex(int idx)
    {
        this.index=idx;
    }
    public void setUser_index(int uidx)
    {
        this.user_index=uidx;
    }
    public void setCalories(double calo)
    {
        this.calories=calo;
    }
    public void setDistance(int dis)
    {
        this.distance=dis;
    }
    public void setStage_id(int level)
    {
        this.stage_id=level;
    }
    public void setScore(int sc)
    {
        this.score=sc;
    }
}

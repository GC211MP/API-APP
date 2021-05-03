package gachon.mpclass.databasetest;


//->게임이 끝나면, (user_id, user_name, stage, distance,score, 칼로리) 서버에 보낸다.
//추가 수정이 필요하다.
public class DataDTO {
    private int index;
    private int user_index;
    private int stage_id;
    private int distance;
    private float calories;
    private int score;

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
    public float getCalories()
    {
        return calories;
    }
    public int getScore()
    {
        return score;
    }
    public void setIndex(int idx)
    {
        this.index=idx;
    }
    public void setUser_index(int uidx)
    {
        this.user_index=uidx;
    }
    public void setCalories(float calo)
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

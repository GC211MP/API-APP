package gachon.mpclass.databasetest;


//->게임이 끝나면, (user_id, user_name, stage, distance,score, 칼로리) 서버에 보낸다.
//추가 수정이 필요하다.
public class DataDTO {
    private int stage_level;
    private float distance;
    private float calories;
    private int score;

    public float getCalories()
    {
        return calories;
    }
    public float getDistance()
    {
        return distance;
    }
    public int getStage_level()
    {
        return stage_level;
    }
    public int getScore()
    {
        return score;
    }
    public void setCalories(float calo)
    {
        this.calories=calo;
    }
    public void setDistance(float dis)
    {
        this.distance=dis;
    }
    public void setStage_level(int level)
    {
        this.stage_level=level;
    }
    public void setScore(int sc)
    {
        this.score=sc;
    }
}

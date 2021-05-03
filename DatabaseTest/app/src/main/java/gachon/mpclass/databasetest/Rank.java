package gachon.mpclass.databasetest;

public class Rank {
    private String uid;
    private int stage_id;
    private int distance;
    private float calories;
    private int score;
    Rank(String rid, int rsid, int rdis, float calo, int sc)
    {
        this.uid=rid;
        this.stage_id=rsid;
        this.distance=rdis;
        this.calories=calo;
        this.score=sc;
    }
    public void setUid(String id)
    {
        this.uid=id;
    }
    public void setStage_id(int level)
    {
        this.stage_id=level;
    }
    public void setDistance(int dis)
    {
        this.distance=dis;
    }
    public void setCalories(float calo)
    {
        this.calories=calo;
    }
    public void setScore(int sc)
    {
        this.score=sc;
    }
    public String getUid()
    {
        return uid;
    }
    public int getDistance()
    {
        return distance;
    }
    public int getStage_id()
    {
        return stage_id;
    }
    public float getCalories()
    {
        return calories;
    }
    public int getScore()
    {
        return score;
    }
}

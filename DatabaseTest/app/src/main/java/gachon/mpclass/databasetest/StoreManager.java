package gachon.mpclass.databasetest;
import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StoreManager {

    // Singleton
    private static StoreManager instance = new StoreManager();

    StoreManager(){}

    public static StoreManager getInstance(){
        return instance;
    }

    // # Main Features
    // > Implemented with Singleton
    // - set rank data
    // - get rank table data
    // - get all rank table data
    // - get total distance
    // - get total calorie
    // - get total score


    // set rank
    public boolean setRank(DataDTO enrollData){
        DataDAO dao = new DataDAO();
        boolean res = dao.create(enrollData);
        return res;
    }
    // get rank table data
    public ArrayList<DataDTO> getRankTable(String feature, boolean isAsc, int stageId){
        DataDAO dao = new DataDAO();
        return dao.read(feature, isAsc, stageId);
    }
    // get all rank table data
    // feature, isAsc: Order by `feature` with ascending or descending order
    public ArrayList<DataDTO> getAllStageRankTable(String feature, boolean isAsc){
        DataDAO dao = new DataDAO();
        return dao.read(feature, isAsc, -1);
    }
    // Enroll the user
    public boolean EnrollUser(UserDTO enrolluser) {
        UserDAO udao=new UserDAO();
        boolean res2=udao.Create(enrolluser);
        return res2;
    }
    // Read the user
    public UserDTO ReadUser(String id) {
        UserDAO udao1=new UserDAO();
        return udao1.Read(id);
    }
    // Update the user's name and password
    public boolean UpdateUser(UserDTO updateuser,String name, String pw) {
        UserDAO udao2=new UserDAO();
        boolean res3=udao2.Update(updateuser,name, pw);
        return res3;
    }
    //이 부분이 근데 sqlite는 context가 parameter로 들어 있어서, 확신 X
    public boolean EnrollSqlite(Context context, SqliteDto sdto){
        SqliteManager sqm=new SqliteManager(context,"user.db");
        boolean res4=sqm.insert(sdto);
        return res4;
    }
    public SqliteDto ReadSqlite(Context context, String id) {
        SqliteManager sqm=new SqliteManager(context, "user.db");
        return sqm.Read(id);
    }
    public boolean UpdateSqlite(Context context, SqliteDto sdto, String name, String pw) {
        SqliteManager sqm=new SqliteManager(context, "user.db");
        boolean res4=sqm.update(sdto, name, pw);
        return res4;
    }

    // - get total distance
    //   - stageId == -1 => total of all stages
    public int getTotalDistance(int userIndex, int stageId){
        try {
            Log.e("=====", "https://api.gcmp.doky.space/data/distance?uidx=" + userIndex + (stageId != -1 ? "&stage=" + stageId : ""));
            URL url = new URL("https://api.gcmp.doky.space/data/distance?uidx=" + userIndex + (stageId != -1 ? "&stage=" + stageId : ""));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);
            String resultJson = "";
            resultJson = builder.toString();
            JSONObject json = new JSONObject(resultJson);

            return json.getInt("total_distance");

        } catch (Exception e) {
            Log.e("APIManager", "GET getUser method failed: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }


    // - get total calorie
    //   - stageId == -1 => total of all stages
    public int getTotalCalorie(int userIndex, int stageId){
        try {
            Log.e("=====", "https://api.gcmp.doky.space/data/calorie?uidx=" + userIndex + (stageId != -1 ? "&stage=" + stageId : ""));
            URL url = new URL("https://api.gcmp.doky.space/data/calorie?uidx=" + userIndex + (stageId != -1 ? "&stage=" + stageId : ""));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);
            String resultJson = "";
            resultJson = builder.toString();
            JSONObject json = new JSONObject(resultJson);

            return json.getInt("total_calorie");

        } catch (Exception e) {
            Log.e("APIManager", "GET getUser method failed: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }


    // - get total score
    //   - stageId == -1 => total of all stages
    public int getTotalScore(int userIndex, int stageId){
        try {
            Log.e("=====", "https://api.gcmp.doky.space/data/score?uidx=" + userIndex + (stageId != -1 ? "&stage=" + stageId : ""));
            URL url = new URL("https://api.gcmp.doky.space/data/score?uidx=" + userIndex + (stageId != -1 ? "&stage=" + stageId : ""));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);
            String resultJson = "";
            resultJson = builder.toString();
            JSONObject json = new JSONObject(resultJson);

            return json.getInt("total_score");

        } catch (Exception e) {
            Log.e("APIManager", "GET getUser method failed: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

}
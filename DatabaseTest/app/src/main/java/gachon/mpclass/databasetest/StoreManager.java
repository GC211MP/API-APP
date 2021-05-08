package gachon.mpclass.databasetest;
import android.app.Person;
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
import java.util.List;

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


    //이 부분이 근데 sqlite는 context가 parameter로 들어 있어서, 확신 X, 돌아가긴 돈다.
    //sqlite에 사용자 등록
    public boolean enrollUser(Context context, SqliteDto sDto){

        // 서버 연동 필요
        UserDAO uDao = new UserDAO();
        boolean isOk = uDao.create(new UserDTO(sDto.getId(), sDto.getName()), sDto.getPassword());

        SqliteManager sqm = new SqliteManager(context,"user.db");
        boolean res = false;
        if(isOk)
            res = sqm.insert(sDto);

        return res;
    }


    //sqlite에서 id를 통해서, 사용자 값을 읽어온다
    public PersonalData readUserById(Context context, String id, List<Integer> stages) {

        // SQLite
        SqliteManager sqm = new SqliteManager(context, "user.db");
        SqliteDto sDto = sqm.Read(id);

        // API Comm.
        UserDAO uDao = new UserDAO();
        UserDTO uDto = uDao.read(id);
        int uidx = uDto.getUser_idx();

        ArrayList<Integer> totalDistance = new ArrayList<Integer>();
        for(int i = 0; i < stages.size(); i++)
            totalDistance.add(this.getTotalDistance(uidx, stages.get(i)));

        ArrayList<Integer> totalCalorie = new ArrayList<Integer>();
        for(int i = 0; i < stages.size(); i++)
            totalCalorie.add(this.getTotalDistance(uidx, stages.get(i)));

        ArrayList<Integer> totalScore = new ArrayList<Integer>();
        for(int i = 0; i < stages.size(); i++)
            totalScore.add(this.getTotalDistance(uidx, stages.get(i)));

        int totalAllScore = this.getTotalScore(uidx, -1);


        PersonalData res = new PersonalData(
                sDto.getId(),
                sDto.getPassword(),
                sDto.getName(),
                sDto.getSex(),
                sDto.getHeight(),
                sDto.getWeight(),
                totalDistance,
                totalCalorie,
                totalScore,
                totalAllScore
        );

        return res;
    }


    protected class PersonalData {
        String id;
        String password;
        String userName;
        String sex;
        int userHeight;
        int userWeight;
        ArrayList<Integer> totalDistanceByStage;
        ArrayList<Integer> totalCalorieByStage;
        ArrayList<Integer> totalScoreByStage;
        int totalScore;
        PersonalData(String id, String password, String userName, String sex, int userHeight, int userWeight, ArrayList<Integer> totalDistanceByStage, ArrayList<Integer> totalCalorieByStage, ArrayList<Integer> totalScoreByStage, int totalScore){
            this.id = id;
            this.password = password;
            this.userName = userName;
            this.sex = sex;
            this.userHeight = userHeight;
            this.userWeight = userWeight;
            this.totalDistanceByStage = totalDistanceByStage;
            this.totalCalorieByStage = totalCalorieByStage;
            this.totalScoreByStage = totalScoreByStage;
            this.totalScore = totalScore;
        }
    }


    //sqlite에서 이름과 비밀번호 수정.
    public boolean updateUserNamePassword(Context context, String userId, String name, String pw) {


        // 서버 연동 필요
        UserDAO uDao = new UserDAO();
        boolean isOk = uDao.update(userId, name, pw);

        SqliteManager sqm = new SqliteManager(context, "user.db");

        boolean res = false;
        if(isOk)
            res = sqm.updateNamePassword(userId, name, pw);

        return res;
    }


    //sqlite에서 키와 몸무게 수정.
    public boolean updateUserHeightWeight(Context context, SqliteDto dto, int ht, int wt){
        SqliteManager sqm = new SqliteManager(context, "user.db");
        boolean res = sqm.updateHeightWeight(dto, ht, wt);
        return res;
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
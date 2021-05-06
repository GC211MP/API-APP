package gachon.mpclass.databasetest;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIManager {

    // Singleton
    private static APIManager instance = new APIManager();

    APIManager(){}

    public static APIManager getInstance(){
        return instance;
    }



    // https://api.gcmp.doky.space/data/distance?uidx=1 수신 코드
    // https://api.gcmp.doky.space/data/calorie?uidx=1 수신 코드
    // https://api.gcmp.doky.space/data/score?uidx=1 수신 코드



    // # USER
    public void getUser(String uid) {
        try {
            URL url = new URL("https://api.gcmp.doky.space/user?uid=" + uid);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);

            String result = "";
            result = builder.toString();
            JSONObject json = new JSONObject(result);

            int user_idx = json.getInt("user_idx");
            String user_id = json.getString("user_id");
            String user_name = json.getString("user_name");


            Log.e("APIManager", json.toString());
            Log.e("APIManager", "user_id: " + user_id);
            Log.e("APIManager", "user_idx: " + user_idx);
            Log.e("APIManager", "user_name: " + user_name);

        }
        catch (Exception e) {
            Log.e("APIManager","GET getUser method failed: "+e.getMessage());
            e.printStackTrace();
        }
    }


    public void postUser(String userId, String userName, String userPassword) {
        String result = null;
        try {
            URL url=new URL("https://api.gcmp.doky.space/user");

            JSONObject json = new JSONObject();
            json.put("user_id", userId);
            json.put("user_name", userName);
            json.put("user_password", userPassword);
            String body = json.toString();


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", "length");
            conn.setRequestProperty("Content-Type", "application/json");

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.write(body.getBytes("UTF-8"));
            os.flush();
            os.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder builder1 = new StringBuilder();
            while((inputLine = in.readLine()) != null) {
                builder1.append(inputLine);
            }
            result = builder1.toString();
            in.close();

            Log.e("APIManager", result);

        }
        catch(Exception e)
        {
            Log.e("APIManager","POST postUser method failed: "+e.getMessage());
            e.printStackTrace();
        }
    }


    public void patchUser(String userId, String userName, String userPassword) {
        String result = null;
        try {
            URL url=new URL("https://api.gcmp.doky.space/user");

            JSONObject json = new JSONObject();
            json.put("user_id", userId);
            json.put("user_name", userName);
            json.put("user_password", userPassword);
            String body = json.toString();


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PATCH");
            conn.setRequestProperty("Content-Length", "length");
            conn.setRequestProperty("Content-Type", "application/json");

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.write(body.getBytes("UTF-8"));
            os.flush();
            os.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder builder1 = new StringBuilder();
            while((inputLine = in.readLine()) != null) {
                builder1.append(inputLine);
            }
            result = builder1.toString();
            in.close();

            Log.e("APIManager", result);

        }
        catch(Exception e) {
            Log.e("APIManager","POST postUser method failed: "+e.getMessage());
            e.printStackTrace();
        }
    }


    // # DATA
    public void getData(String feature, boolean isAsc) {
        try {
            URL url = new URL("https://api.gcmp.doky.space/data?c=" + feature + "&o=" + (isAsc ? "asc" : "desc"));
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);


            String result = "";
            result = builder.toString();
            JSONArray json = new JSONArray(result);

            Log.e("APIManager", json.toString());

            for(int i = 0; i < json.length(); i++){
                String cDate = json.getJSONObject(i).getString("c_date");
                String userName = json.getJSONObject(i).getString("user_name");
                int stageId = json.getJSONObject(i).getInt("stage_id");
                int elapsedTime = json.getJSONObject(i).getInt("elapsed_time");
                Log.e("APIManager", "c_date: " + cDate);
                Log.e("APIManager", "user_name: " + userName);
                Log.e("APIManager", "stage_id: " + stageId);
                Log.e("APIManager", "elapsed_time: " + elapsedTime);
            }
        }
        catch (Exception e) {
            Log.e("APIManager","GET getUser method failed: "+e.getMessage());
            e.printStackTrace();
        }
    }


    public void postData(int userIdx, int stageId, int elapsedTime) {
        String result = null;
        try {
            URL url=new URL("https://api.gcmp.doky.space/data");

            JSONObject json = new JSONObject();
            json.put("user_idx", userIdx);
            json.put("stage_id", stageId);
            json.put("elapsed_time", elapsedTime);
            String body = json.toString();


            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", "length");
            conn.setRequestProperty("Content-Type", "application/json");

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.write(body.getBytes("UTF-8"));
            os.flush();
            os.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder builder1 = new StringBuilder();
            while((inputLine = in.readLine()) != null) {
                builder1.append(inputLine);
            }
            result = builder1.toString();
            in.close();

            Log.e("APIManager", result);

        }
        catch(Exception e) {
            Log.e("APIManager","POST postUser method failed: "+e.getMessage());
            e.printStackTrace();
        }

    }

}
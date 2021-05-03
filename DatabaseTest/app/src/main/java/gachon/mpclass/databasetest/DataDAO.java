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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.transform.Result;

//->게임이 끝나면, (user_id, user_name, stage, distance, 칼로리) 서버에 보낸다.
public class DataDAO {
    //Enroll the data
    public void Create(DataDTO dt)
    {
        String result = null;
        try {
            URL url=new URL("https://api.gcmp.doky.space/data");
            JSONObject json = new JSONObject();
            json.put("user_idx", dt.getUser_index());
            json.put("stage_id", dt.getStage_id());
            json.put("distance",dt.getDistance());
            json.put("calories",dt.getCalories());
            json.put("score",dt.getScore());
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
            e.printStackTrace();
        }
    }
    // Rank 읽어오기 위해서 Rank정보를 가져올 수 있게, 따로 클래스를 가져온다.
    //stage와 feature인 score에 따라, 정보를 순차적으로 가져온다.
    public ArrayList<Rank> Read(int stage, String feature) {
        ArrayList<Rank> rk = new ArrayList<Rank>(); //Ranking 을 위한  Rank를 담을 class를 arraylist로 선언해서, arraylist에 담는다. 
        try {
            URL url = new URL("https://api.gcmp.doky.space/data?c=" + feature); //서버에 어떻게 보낼지에 대한 정보 부족. 서버에서 수정해주면 될것 같다.
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
            for (int i = 0; i < json.length(); i++) {
                String rid = json.getJSONObject(i).getString("user_id");
                int sid = json.getJSONObject(i).getInt("stage_id");
                int dis = json.getJSONObject(i).getInt("distance");
                double calories = json.getJSONObject(i).getDouble("calories");
                int score = json.getJSONObject(i).getInt("score");
                Rank rank = new Rank(rid, sid, dis, calories, score);
                rk.add(rank);
            }

        } catch (Exception e) {
            Log.e("APIManager", "GET getUser method failed: " + e.getMessage());
            e.printStackTrace();
        }
        return rk;
    }
    // 미구현
    public void Update(DataDTO dt, int score) throws ClassNotFoundException, SQLException
    {

    }
    //미구현.
    public void Delete(DataDTO dt) throws ClassNotFoundException, SQLException
    {

    }



}


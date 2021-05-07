package gachon.mpclass.databasetest;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// WriteAgent 가 끝나면, (id, password, user_name, 성별, 키, 몸무게) 를 서버에 보낸다.
public class UserDAO {
    public void Create(UserDTO user) {
        String result=null;
        try {
            URL url = new URL("https://api.gcmp.doky.space/user");
            JSONObject json = new JSONObject();
            json.put("user_id", user.getUser_id());
            json.put("user_name", user.getUser_name());
            json.put("user_password", user.getPassword());
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
    // Read the user's data and return the user DTO
    public UserDTO Read(String id) {
        UserDTO dto=new UserDTO();
        try {
            URL url = new URL("https://api.gcmp.doky.space/user?uid=" + id);
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
            JSONObject json = new JSONObject(result);
            String user_id = json.getString("user_id");
            String user_name = json.getString("user_name");
            String password=json.getString("password");
            dto.setUser_id(user_id);
            dto.setUser_name(user_name);
            dto.setUser_password(password);
            Log.i("APIManger", result);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return dto;
    }


    // update 이름이랑 비밀번호 바꾸기.
    public void Update(UserDTO user, String name, String pw) {
        String result = null;
        try {
            URL url=new URL("https://api.gcmp.doky.space/user");
            JSONObject json = new JSONObject();
            json.put("user_id", user.getUser_id());
            json.put("user_name", name); //new name
            json.put("user_password", pw); //new password
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


    //미구현.
    public void Delete(UserDTO user) {

    }

}


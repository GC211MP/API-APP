package gachon.mpclass.apitest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIComm {

    String result;

    public void getUser() {
        try {
            URL url = new URL("http://api.gcmp.doky.space/api/user");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            };
            result = builder.toString();
        }
        catch (Exception e)
        {
            Log.e("REST_API","GET method failed: "+e.getMessage());
            e.printStackTrace();
        }
    }
}

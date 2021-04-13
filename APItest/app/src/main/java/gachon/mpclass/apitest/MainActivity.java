package gachon.mpclass.apitest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.Handler;
public class MainActivity extends AppCompatActivity {
    String result=null;
    TextView textview;
    Button button1;
    Handler handler=new Handler();
    TextView tvw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview=(TextView)findViewById(R.id.tvw);
        button1=(Button)findViewById(R.id.request);
        tvw=(TextView)findViewById(R.id.tv2);
        button1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sendGET();
                            sendPost();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
                    });

        }
        //send get method to api
    public void sendGET()  throws Exception {
        try {
            URL url = new URL("http://api.gcmp.doky.space/api/test");
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
            textview.append(result);
        }
        catch (Exception e)
        {
            Log.e("REST_API","GET method failed: "+e.getMessage());
            e.printStackTrace();
        }
    }
    //send post to api
    public void sendPost() throws Exception{
        String result=null;
        try {
            URL url=new URL("http://api.gcmp.doky.space/api/test");
            HttpURLConnection con=(HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            DataOutputStream ot=new DataOutputStream(con.getOutputStream());
            ot.flush();
            ot.close();
            BufferedReader in =new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder builder1=new StringBuilder();
            while((inputLine=in.readLine())!=null)
            {
                builder1.append(inputLine);
            }
            result=builder1.toString();
            tvw.append(result);
            in.close();
        }
        catch(Exception e)
        {
            Log.e("REST_API","POST method failed: "+e.getMessage());
            e.printStackTrace();
        }

    }
}
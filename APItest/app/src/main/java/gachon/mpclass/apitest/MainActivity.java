package gachon.mpclass.apitest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview=(TextView)findViewById(R.id.tvw);
        button1=(Button)findViewById(R.id.request);
        button1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            doInBackground();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
                    });

        }
    public void doInBackground()  throws Exception {
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
            }
            result = builder.toString();
            textview.append(result);
        }
        catch (Exception e)
        {
            Log.e("REST_API","GET method failed: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
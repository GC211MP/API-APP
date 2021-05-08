package gachon.mpclass.databasetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Button> button = new ArrayList<Button>();
        button.add(findViewById(R.id.b1));
        button.add(findViewById(R.id.b2));
        button.add(findViewById(R.id.b3));
        button.add(findViewById(R.id.b4));
        button.add(findViewById(R.id.b5));
        button.add(findViewById(R.id.b6));
        button.add(findViewById(R.id.b7));
        button.add(findViewById(R.id.b8));
        button.add(findViewById(R.id.b9));
        button.add(findViewById(R.id.b10));
        button.add(findViewById(R.id.b11));
        button.add(findViewById(R.id.b12));

        StoreManager inst = StoreManager.getInstance();


        Integer[] stages = {1, 2};

        for(int i = 0; i<button.size(); i++){
            final int ii = i;
            Button btn = button.get(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread(new Runnable() {

                        ArrayList<DataDTO> res;
                        boolean isResult = false;
                        int resInt = -1;

                        @Override
                        public void run() {
                            switch (ii){
                                case 0:
                                    System.out.println("0000000000");
                                    inst.setRank(getApplicationContext(), new DataDTO("testtest", 2, 300, 300, 300));
                                    break;
                                case 1:
                                    System.out.println("1111111111");
                                    res = inst.getRankTable("c_date", false, 1);
                                    System.out.println(res);
                                    break;
                                case 2:
                                    System.out.println("2222222222");
                                    res = inst.getAllStageRankTable("c_date", false);
                                    System.out.println(res);
                                    break;
                                case 3:
                                    System.out.println("3333333333");
                                    isResult = false;
                                    isResult = inst.enrollUser(getApplicationContext(), new SqliteDto("testtest", "3333", "testman", 200, 100, "man"));
                                    System.out.println(isResult);
                                    break;
                                case 4:
                                    System.out.println("4444444444");
                                    StoreManager.PersonalData resPer = inst.readUserData(getApplicationContext(), stages);
                                    System.out.println(resPer);
                                    break;
                                case 5:
                                    System.out.println("5555555555");
                                    isResult = false;
                                    isResult = inst.updateUserNamePassword(getApplicationContext(), "uhug", "uhug", "uhug");
                                    System.out.println(isResult);
                                    break;
                                case 6:
                                    System.out.println("6666666666");
                                    isResult = false;
                                    isResult = inst.updateUserHeightWeight(getApplicationContext(), 400, 200);
                                    System.out.println(isResult);
                                    break;
                                case 7:
                                    System.out.println("7777777777");
                                    resInt = -1;
                                    resInt = inst.getTotalDistance(1, -1);
                                    System.out.println(resInt);
                                    break;
                                case 8:
                                    System.out.println("8888888888");
                                    resInt = -1;
                                    resInt = inst.getTotalDistance(1, 2);
                                    System.out.println(resInt);
                                    break;
                                case 9:
                                    System.out.println("9999999999");
                                    resInt = -1;
                                    resInt = inst.getTotalCalorie(2, -1);
                                    System.out.println(resInt);
                                    break;
                                case 10:
                                    System.out.println("10.10.10.10.10");
                                    resInt = -1;
                                    resInt = inst.getTotalScore(2, -1);
                                    System.out.println(resInt);
                                    break;
                                case 11:
                                    System.out.println("11.11.11.11.11");
                                    break;
                            }
                            //new DataDAO().read("c_date", true);
                        }
                    }).start();
                }
            });
        }

    }





}



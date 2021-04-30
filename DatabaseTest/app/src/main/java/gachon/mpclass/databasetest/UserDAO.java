package gachon.mpclass.databasetest;
// WriteAgent 가 끝나면, (id, password, user_name, 성별, 키, 몸무게) 를 서버에 보낸다.
public class UserDAO {
    public class Enroll implements  Runnable
    {
        public void run()
        {
           // APIManager.getInstance().postUser();

        }
    }
    public class Modify implements Runnable{
        public void run()
        {
            //Nothing

        }
    }


}

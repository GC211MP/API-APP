package gachon.mpclass.apitest;

public class UserDTO {

    UserDTO(String userId, String userName, String userPasswd){
        this.userId = userId;
        this.userName = userName;
        this.userPasswd = userPasswd;
    }

    private String userId;
    private String userName;
    private String userPasswd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }
}

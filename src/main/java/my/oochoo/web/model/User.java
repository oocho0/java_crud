package my.oochoo.web.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
    private int userKey;
    private String userId;
    private String password;
    private String userName;
    private Date regDate;
    private Date modDate;

    public int getUserKey() {
        return userKey;
    }

    public void setUserKey(int userKey) {
        this.userKey = userKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> userMap = new HashMap<>();
        if (this.userId != null) {
            userMap.put("userId", this.userId);
        }
        if (this.password != null) {
            userMap.put("password", this.password);
        }
        if (this.userName != null) {
            userMap.put("userName", this.userName);
        }
        if (this.regDate != null) {
            userMap.put("regDate", this.regDate);
        }
        if (this.modDate != null) {
            userMap.put("modDate", this.modDate);
        }
        return userMap;
    }
}

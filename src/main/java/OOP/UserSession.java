/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOP;

/**
 *
 * @author Justin Yong
 */
public class UserSession {
    private static UserSession instance;
    private String userID;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    public UserSession(String userID, String username, String email, String phoneNumber, String password) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    private UserSession(String userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public static void createSession(String userID, String username) {
        if (instance == null) {
            instance = new UserSession(userID, username);
        }
    }

    public static UserSession getInstance() {
        return instance;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public static void clearSession() {
        instance = null;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}

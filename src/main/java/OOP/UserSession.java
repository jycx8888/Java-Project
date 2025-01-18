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
}

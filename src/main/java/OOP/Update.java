/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOP;

/**
 *
 * @author Justin Yong
 */
public abstract class Update {
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected String password;

    public Update(String name, String email, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (validateName(name)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (validateEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (validatePhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (validatePassword(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    public void updateProfile(String name, String email, String phoneNumber, String password) {
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
    }

    public abstract void saveProfile();

    // Validation methods
    private boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean validateEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

    private boolean validatePassword(String password) {
        return password != null && password.length() >= 6;
    }
}
//public class Update {
     
    
//}

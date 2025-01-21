/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOP;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Update {
    private String userID;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    public Update(String userID, String name, String email, String phoneNumber, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.matches("[a-zA-Z\\s]+")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email.");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\d+") && phoneNumber.startsWith("01") && phoneNumber.length() == 10) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,14}$")) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password.");
        }
    }

    public boolean validateProfile(String name, String email, String phoneNumber, String password, String filePath) {
        // Retrieve the current user's data from the file
        String[] currentUserInfo = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 5 && parts[0].equals(userID)) {
                    currentUserInfo = parts;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (currentUserInfo == null) {
            JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Skip validation for unchanged email and phone number
        boolean emailChanged = !email.equals(currentUserInfo[2]);
        boolean phoneNumberChanged = !phoneNumber.equals(currentUserInfo[3]);
        
        if (!Validator.validateProfile(name, email, phoneNumber, password)) {
            return false;
        }

        if (emailChanged || phoneNumberChanged) {
            if (Validator.isEmailOrPhoneNumberRegistered(email, phoneNumber, filePath)) {
                JOptionPane.showMessageDialog(null, "Email or phone number is already registered for this position.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    public void editProfile(String name, String email, String phoneNumber, String password, String filePath) {
        if (!validateProfile(name, email, phoneNumber, password, filePath)) {
            return;
        }

        // Read the file into a list of strings
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Modify the specific line
        for (int i = 0; i < fileContent.size(); i++) {
            String[] parts = fileContent.get(i).split(", ");
            if (parts.length >= 5 && parts[0].equals(userID)) {
                fileContent.set(i, userID + ", " + name + ", " + email + ", " + phoneNumber + ", " + password);
                break;
            }
        }
        
        // Write the list back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String content : fileContent) {
                writer.write(content);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isEmailOrPhoneNumberRegistered(String email, String phoneNumber) {
        String filePath;
        if (userID.startsWith("R")) {
            filePath = "src/main/java/OOP/Resident_Info.txt";
        } else if (userID.startsWith("S")) {
            filePath = "src/main/java/OOP/Staff_Info.txt";
        } else {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 5) {
                    String fileEmail = parts[2];
                    String filePhoneNumber = parts[3];
                    if (fileEmail.equals(email) || filePhoneNumber.equals(phoneNumber)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
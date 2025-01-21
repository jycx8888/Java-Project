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
            throw new IllegalArgumentException("Invalid name");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("\\d+") && phoneNumber.startsWith("01") && phoneNumber.length() == 10) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,14}$")) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    public void editProfile(String name, String email, String phoneNumber, String password, String filePath) {
        // Validation logic
        if (name.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!name.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "Name cannot have numbers or syntax", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Phone number must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!phoneNumber.startsWith("01") || !phoneNumber.matches("[0-9]+") || phoneNumber.length() != 10) {
            JOptionPane.showMessageDialog(null, "Phone number must start with '01' and must be 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(null, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,14}$")) {
            JOptionPane.showMessageDialog(null, "Invalid password. Password must be 8-14 characters long, contain at least one capital letter, one number, and one special character.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if email or phone number is already registered for the selected position
        if (isEmailOrPhoneNumberRegistered(email, phoneNumber)) {
            JOptionPane.showMessageDialog(null, "Email or phone number is already registered for this position.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Proceed with profile update logic
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> fileContent = new ArrayList<>();
            String[] userInfo = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 5 && parts[0].equals(userID)) {
                    userInfo = parts;
                } else {
                    fileContent.add(line);
                }
            }

            if (userInfo != null) {
                userInfo[1] = name;
                userInfo[2] = email;
                userInfo[3] = phoneNumber;
                userInfo[4] = password;
                fileContent.add(String.join(", ", userInfo));
            }

            // Write updated content back to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String content : fileContent) {
                    writer.write(content);
                    writer.newLine();
                }
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
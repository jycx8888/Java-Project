/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOP;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Update {
    protected String userID;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected String password;

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

    public void editProfile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> fileContent = new ArrayList<>();
            String[] userInfo = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 5 && parts[0].equals(userID)) {
                    userInfo = parts;
                }
                fileContent.add(line);
            }

            if (userInfo != null) {
                JTextField newNameField = new JTextField(userInfo[1]);
                JTextField newEmailField = new JTextField(userInfo[2]);
                JTextField newPhoneField = new JTextField(userInfo[3]);
                JPasswordField newPasswordField = new JPasswordField(userInfo[4]);

                Object[] message = {
                    "Name:", newNameField,
                    "Email:", newEmailField,
                    "Phone:", newPhoneField,
                    "Password:", newPasswordField
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Edit Profile", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    updateProfile(newNameField.getText(), newEmailField.getText(), newPhoneField.getText(), new String(newPasswordField.getPassword()));
                    saveUpdatedProfile(filePath, fileContent);
                }
            } else {
                JOptionPane.showMessageDialog(null, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUpdatedProfile(String filePath, List<String> fileContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : fileContent) {
                String[] parts = line.split(", ");
                if (parts.length >= 5 && parts[0].equals(userID)) {
                    writer.write(userID + ", " + name + ", " + email + ", " + phoneNumber + ", " + password);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

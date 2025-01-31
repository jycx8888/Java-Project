/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class Validator {

    public static boolean validateProfile(String name, String email, String phoneNumber, String password) {
        if (name.isEmpty() || password.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!name.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "Name cannot have numbers or syntax", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Phone number must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!phoneNumber.startsWith("01") || !phoneNumber.matches("[0-9]+") || phoneNumber.length() != 10) {
            JOptionPane.showMessageDialog(null, "Phone number must start with '01' and must be 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if ((!email.contains("@")) || (!email.contains("."))) {
            JOptionPane.showMessageDialog(null, "Email must contain '@' and '.'", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,14}$")) {
            JOptionPane.showMessageDialog(null, "Invalid password. Password must be 8-14 characters long, contain at least one capital letter, one number, and one special character.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // Overloaded method for registration
    public static boolean isEmailRegistered(String email, String currentFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(currentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(", ");
                if (details.length >= 3 && details[2].trim().equals(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Overloaded method for registration
    public static boolean isPhoneNumberRegistered(String phoneNumber, String currentFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(currentFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(", ");
                if (details.length >= 4 && details[3].trim().equals(phoneNumber)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEmailRegistered(String email, String filePath, String currentEmail) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 5) {
                    String fileEmail = parts[2];
                    if (fileEmail.equals(email) && !fileEmail.trim().equals(currentEmail)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean isPhoneNumberRegistered(String phoneNumber, String filePath, String currentPhoneNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 5) {
                    String filePhoneNumber = parts[3];
                    if (filePhoneNumber.equals(phoneNumber) && !filePhoneNumber.trim().equals(currentPhoneNumber)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isRateNameRegistered(String rateName, String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(", ");
            if (parts[0].equals(rateName)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }
}
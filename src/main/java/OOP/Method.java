/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Method {
    public static boolean isEmailRegistered(String email, String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(", ");
            if (parts[2].equals(email)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    public static boolean isPhoneNumberRegistered(String phoneNumber, String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(", ");
            if (parts[3].equals(phoneNumber)) {
                reader.close();
                return true;
            }
        }
        reader.close();
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

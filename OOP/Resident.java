import java.io.*;
import java.util.*;

public class Resident{
    static Scanner sc = new Scanner(System.in);

    private String residentId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String status;

    public static void main(String[] args) {   
            startPageResident();
    }
    
    public static void delay(){
        try {
            Thread.sleep(500); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace(); // This catches and handles any interruption in the sleep
        }
    }

    // Constructor
    public Resident(String residentId, String name, String email, String phoneNumber, String password, String status) {
        this.residentId = residentId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.status = status;
    }

    // Getters and Setters
    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    // Method to generate a sequential resident ID from a file
    public static String generateResidentId() throws IOException {
        File file = new File("ResidentIDGenerator.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int id = Integer.parseInt(reader.readLine());
        reader.close();

        String residentId = "R" + String.format("%02d", id);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(Integer.toString(id + 1));
        writer.close();

        return residentId;
    }

    // Method to register a new resident member
    public static void registerResident() {
        String empty = "";
        String name = "";
        while (true) {
            System.out.print("Please enter your name(Enter 'E' for exit): ");
            name = sc.nextLine();
            if (name.equals(empty)) {
                delay();
                System.out.println("Name cannot be empty");
                continue;
            } else if (!name.matches("[a-zA-Z\\s]+")) {
                delay();
                System.out.println("Name cannot have numbers or syntax");
                continue;
            }else if(name.equalsIgnoreCase("E")){
                System.out.println("Exiting the register page...");
                delay();
                startPageResident();
            }
            break;
        }

        String email = "";
        while (true) {
            System.out.print("Please enter your email(Enter 'E' for exit): ");
            email = sc.nextLine();
            if (email.equals(empty)) {
                delay();
                System.out.println("Email cannot be empty");
                continue;
            } else if (!email.contains("@") || !email.contains(".com")) {
                delay();
                System.out.println("Email must contain '@' and '.com'");
                continue;
            } else if(email.equalsIgnoreCase("E")){
                System.out.println("Exiting the register page...");
                delay();
                startPageResident();
            }else {
                try {
                    if (isEmailRegistered(email)) {
                        delay();
                        System.out.println("Email is already registered. Please enter a different email.");
                        continue;
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while checking if the email is registered.");
                    e.printStackTrace();
                }
            }
            break;
        }

        String phoneNumber = "";
        while (true) {
            System.out.print("Please enter phone number(Enter 'E' for exit): ");
            phoneNumber = sc.nextLine();
            if (phoneNumber.equals(empty)) {
                delay();
                System.out.println("Phone number cannot be empty");
                continue;
            } else if (!phoneNumber.startsWith("01")) {
                delay();
                System.out.println("Phone number must start with '01'");
                continue;
            }else if (!phoneNumber.matches("[0-9]+") || phoneNumber.length() != 10){
                delay();
                System.out.println("phone number must be 10 digits");
                continue;
            }else if(phoneNumber.equalsIgnoreCase("E")){
                System.out.println("Exiting the register page...");
                delay();
                startPageResident();
            }else {
                try {
                    if (isPhoneNumberRegistered(phoneNumber)) {
                        delay();
                        System.out.println("Phone number is already registered. Please enter a different phone number.");
                        continue;
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while checking if the phone number is registered.");
                    e.printStackTrace();
                }
            }
            break;
        }

        String password = "";
        while (true) {
            System.out.print("Enter password(Enter 'E' for exit): ");
            password = sc.nextLine();
            if (password.equals(empty)) {
                delay();
                System.out.println("Password cannot be empty");
                continue;
            } else if (!password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,14}$")) {
                delay();
                System.out.println("Password must be 8-14 characters long, contain at least one capital letter, one number, and one special character");
                continue;
            }else if(password.equalsIgnoreCase("E")){
                System.out.println("Exiting the register page...");
                delay();
                startPageResident();
            }
            break;
        }

        try {
            String residentId = generateResidentId();
            String status = "Unapproved";
            Resident resident = new Resident(residentId, name, email, phoneNumber, password, status);
            saveResidentToFile(resident);
            String residentDetails = "[" + resident.getResidentId() + ", " + resident.getName() + ", " + resident.getEmail() + ", " + resident.getPhoneNumber() + "]";
            System.out.println(residentDetails);
            System.out.println("Register Successfully! Your registration is waiting for approval.");
        } catch (IOException e) {
            System.out.println("An error occurred while generating resident ID.");
            e.printStackTrace();
        }
    }

    public static boolean isEmailRegistered(String email) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("ResidentInformation.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.replace("[", "").replace("]", "").split(", ");
            if (parts[2].equals(email)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    public static boolean isPhoneNumberRegistered(String phoneNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("ResidentInformation.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.replace("[", "").replace("]", "").split(", ");
            if (parts[3].equals(phoneNumber)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    public static void saveResidentToFile(Resident resident) throws IOException {
        FileWriter myWriter = new FileWriter("UnapproveUser.txt", true);
        myWriter.write("[" + resident.residentId + ", " + resident.name + ", " + resident.email + ", " + resident.phoneNumber + ", " + resident.password + ", "  + resident.status + "]\r\n");
        myWriter.close();
    }

    public static void loginResident() throws IOException {
        String empty = "";
        boolean found = false;

        while (!found) {
            String email = "";
            while (true) {
                System.out.print("Enter your email(Enter 'E' for exit): ");
                email = sc.nextLine();
                if (email.equals(empty)) {
                    delay();
                    System.out.println("Email cannot be empty");
                    continue;
                } else if (email.equalsIgnoreCase("E")) {
                    System.out.println("Exiting the login page...");
                    delay();
                    startPageResident();
                }
                break;
            }

            String password = "";
            while (true) {
                System.out.print("Enter your password(Enter 'E' for exit): ");
                password = sc.nextLine();
                if (password.equals(empty)) {
                    delay();
                    System.out.println("Password cannot be empty");
                    continue;
                } else if (password.equalsIgnoreCase("E")) {
                    System.out.println("Exiting the login page...");
                    delay();
                    startPageResident();
                }
                break;
            }

            try(BufferedReader reader = new BufferedReader(new FileReader("ResidentInformation.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.replace("[", "").replace("]", "").split(", ");
                String fileResidentId = details[0];
                String fileName = details[1];
                String fileEmail = details[2];
                String filePhoneNumber = details[3];
                String filePassword = details[4];
                String fileStatus = details[5];

                if (fileEmail.equals(email) && filePassword.equals(password) && fileStatus.equals("Approved")) {
                    System.out.println("Login successful! Welcome, " + fileName + ", " + fileResidentId);
                    found = true;
                    residentMainPage();
                    break;
                }

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        if (!found) {
            System.out.println("Invalid email or password. Please try again.");
        }
      } 
    }

   //where everything starts for resident
    public static void startPageResident(){
        while (true) {
           System.out.println("""
            -------Welcome to APU Hostel Management Fees Payment System (Resident)-------
           Which option would you like to access?
           1. Register
           2. Login
           3. Exit""");
           System.out.print("Enter your choice (numbers only): ");
           String choice = sc.nextLine();
           String empty = "";
           if (choice.equals("1")) {
               registerResident();
               continue;
           } else if (choice.equals("2")) {
                try {
                    loginResident();
                    if (true) {
                    while (true) { 
                        residentMainPage();
                    }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred during login.");
                    e.printStackTrace();
                }
                continue;
           } else if (choice.equals("3")) {
               System.out.println("Exiting the system...");
               delay();
               Start.startPage();
           } else if (choice.equals(empty)) {
                delay();
                System.out.println("Cannot be empty.");
            
           }else {
               System.out.println("Invalid choice. Please try again.");
           } 
        }

    }
        // -------Welcome to Resident Management System-------
    public static void residentMainPage(){
        System.out.println("""
        -----------------Resident Main Menu-----------------
        1) Update details
        2) View payment records
        3) Exit""");
        System.out.print("Which option would you like to access? (numbers only): ");
        String choice = sc.nextLine();
        if (choice.equals("1")){
            update();
        }
        else if(choice.equals("2")){
            System.out.println("View payment records");
        }
        else if(choice.equals("3")){
            System.out.println("Exiting the system...");
            delay();
            startPageResident();
        }
        else{
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static void update()
    {
        while(true){
        System.out.println("""
        -------Update Details-------
        1) Update Name
        2) Update Email
        3) Update Phone Number
        4) Update Password
        5) Exit""");
        System.out.print("Which option would you like to access? (numbers only): ");
        String choice = sc.nextLine();
        if (choice.equals("1")){
            update_name();
            continue;
        }
        else if(choice.equals("2")){
            update_email();
            continue;
        }
        else if(choice.equals("3")){
            update_phone();
            continue;
        }
        else if(choice.equals("4")){
            update_password();
            continue;
        }
        else if(choice.equals("5")){
            System.out.println("Exiting the system...");
            delay();
            break;
        }
        else{
            System.out.println("Invalid choice. Please try again.");
        }
    }
    }


    public static void update_name() {
        while (true) { 
        System.out.print("Please enter your old name: ");
        String oldname = sc.nextLine().trim();
        if (oldname.isEmpty()) {
            delay();
            System.out.println("Name cannot be empty");
            continue;
        } else if (!oldname.matches("[a-zA-Z\\s]+")) {
            delay();
            System.out.println("Name cannot have numbers or syntax");
            continue;
        }
        boolean nameExists = false;
        StringBuilder fileContent = new StringBuilder();

        // Check if the old name exists in the file and prepare to update the content
        try (BufferedReader reader = new BufferedReader(new FileReader("ResidentInformation.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(oldname)) {
                    String[] parts = line.split(", ");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].equals(oldname)) {
                            nameExists = true;
                            System.out.print("Enter your new name: ");
                            String newname = sc.nextLine().trim();
                            if (newname.isEmpty()) {
                                delay();
                                System.out.println("Name cannot be empty");
                                continue;
                            } else if (!newname.matches("[a-zA-Z\\s]+")) {
                                delay();
                                System.out.println("Name cannot have numbers or syntax");
                                continue;
                            }
                            parts[i] = newname;
                            break;
                        }
                    }
                    line = String.join(", ", parts);
                }
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!nameExists) {
            System.out.println("Name not found.");
            continue;
        } else if (nameExists) {
            // Write the updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ResidentInformation.txt"))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }        
            System.out.println("Name updated successfully.");
            break;
        }


    }
    }

    public static void update_email() {
        while (true) { 
        System.out.print("Please enter your old email: ");
        String oldemail = sc.nextLine().trim();
            if (oldemail.isEmpty()) {
                delay();
                System.out.println("Email cannot be empty");
                continue;
            } else if (!oldemail.contains("@") || !oldemail.contains(".com")) {
                delay();
                System.out.println("Email must contain '@' and '.com'");
                continue;
            }
        boolean emailExists = false;
        StringBuilder fileContent = new StringBuilder();

        // Check if the old email exists in the file and prepare to update the content
        try (BufferedReader reader = new BufferedReader(new FileReader("ResidentInformation.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(oldemail)) {
                    String[] parts = line.split(", ");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].equals(oldemail)) {
                            emailExists = true;
                            System.out.print("Enter your new email: ");
                            String newemail = sc.nextLine().trim();
                            if (oldemail.isEmpty()) {
                                delay();
                                System.out.println("Email cannot be empty");
                                continue;
                            } else if (!oldemail.contains("@") || !oldemail.contains(".com")) {
                                delay();
                                System.out.println("Email must contain '@' and '.com'");
                                continue;
                            }
                            parts[i] = newemail;
                            break;
                        }
                    }
                    line = String.join(", ", parts);
                }
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!emailExists) {
            System.out.println("email not found.");
        } else if (emailExists) {
            // Write the updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ResidentInformation.txt"))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("email updated successfully.");
            break;
        }

    }
    }

    public static void update_phone(){
        while (true) { 
        System.out.print("Please enter your old phone number: ");
        String oldphone = sc.nextLine().trim();
        if (oldphone.isEmpty()) {
            delay();
            System.out.println("Phone number cannot be empty");
            continue;
        } else if (!oldphone.startsWith("01")) {
            delay();
            System.out.println("Phone number must start with '01'");
            continue;
        }else if (!oldphone.matches("[0-9]+") || oldphone.length() != 10){
            delay();
            System.out.println("phone number must be 10 digits");
            continue;
        }
        boolean phoneExists = false;
        StringBuilder fileContent = new StringBuilder();

        // Check if the old phone number exists in the file and prepare to update the content
        try (BufferedReader reader = new BufferedReader(new FileReader("ResidentInformation.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(oldphone)) {
                    String[] parts = line.split(", ");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].equals(oldphone)) {
                            phoneExists = true;
                            System.out.print("Enter your new phone number: ");
                            String newphone = sc.nextLine().trim();
                            if (newphone.isEmpty()) {
                                delay();
                                System.out.println("Phone number cannot be empty");
                                continue;
                            } else if (!newphone.startsWith("01")) {
                                delay();
                                System.out.println("Phone number must start with '01'");
                                continue;
                            }else if (!newphone.matches("[0-9]+") || newphone.length() != 10){
                                delay();
                                System.out.println("phone number must be 10 digits");
                                continue;
                            }
                            parts[i] = newphone;
                            break;
                        }
                    }
                    line = String.join(", ", parts);
                }
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!phoneExists) {
            System.out.println("Phone number not found.");
        } else if (phoneExists){
            // Write the updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ResidentInformation.txt"))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Phone number updated successfully.");
            break;            
        }

    }
    }

    public static void update_password(){
        while (true) { 
        System.out.print("Please enter your old password: ");
        String oldpass = sc.nextLine().trim();
        if (oldpass.isEmpty()) {
            delay();
            System.out.println("Password cannot be empty");
            continue;
        } else if (!oldpass.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,14}$")) {
            delay();
            System.out.println("Password must be 8-14 characters long, contain at least one capital letter, one number, and one special character");
            continue;
        }
        boolean passExists = false;
        StringBuilder fileContent = new StringBuilder();

        // Check if the old password exists in the file and prepare to update the content
        try (BufferedReader reader = new BufferedReader(new FileReader("ResidentInformation.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(oldpass)) {
                    String[] parts = line.split(", ");
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].equals(oldpass)) {
                            passExists = true;
                            System.out.print("Enter your new password: ");
                            String newpass = sc.nextLine().trim();
                            if (newpass.isEmpty()) {
                                delay();
                                System.out.println("Password cannot be empty");
                                continue;
                            } else if (!newpass.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,14}$")) {
                                delay();
                                System.out.println("Password must be 8-14 characters long, contain at least one capital letter, one number, and one special character");
                                continue;
                            }
                            parts[i] = newpass;
                            break;
                        }
                    }
                    line = String.join(", ", parts);
                }
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!passExists) {
            System.out.println("password not found.");
        } else if (passExists){
            // Write the updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("ResidentInformation.txt"))) {
                writer.write(fileContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("password updated successfully.");
            break;            
        }

    }
    }
}

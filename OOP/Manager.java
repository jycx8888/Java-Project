import java.util.*;
import java.io.*;

public class Manager{
    static Scanner sc = new Scanner(System.in);

    private String managerId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String status;

    public static void main(String[] args) {   
            startPageManager();
    }
    
    public static void delay(){
        try {
            Thread.sleep(500); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace(); // This catches and handles any interruption in the sleep
        }
    }

    // Constructor
    public Manager(String managerId, String name, String email, String phoneNumber, String password, String status) {
        this.managerId = managerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.status = status;
    }

    // Getters and Setters
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
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

    // Method to generate a sequential manager ID from a file
    public static String generateManagerId() throws IOException {
        File file = new File("ManagerIDGenerator.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int id = Integer.parseInt(reader.readLine());
        reader.close();

        String managerId = "M" + String.format("%02d", id);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(Integer.toString(id + 1));
        writer.close();

        return managerId;
    }

    // Method to register a new manager member
    public static void registerManager() {
        String name = "";
        while (true) {
            System.out.print("Please enter your name(Enter 'E' for exit): ");
            name = sc.nextLine().trim();
            if (name.equals("")) {
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
                startPageManager();
            }
            break;
        }

        String email = "";
        while (true) {
            System.out.print("Please enter your email(Enter 'E' for exit): ");
            email = sc.nextLine().trim();
            if (email.equals("")) {
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
                startPageManager();
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
            phoneNumber = sc.nextLine().trim();
            if (phoneNumber.equals("")) {
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
                startPageManager();
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
            password = sc.nextLine().trim();
            if (password.equals("")) {
                delay();
                System.out.println("Password cannot be empty");
                continue;
            } else if (!password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,14}$")) {
                delay();
                System.out.println("Password must be 8-14 characters long, contain at least one capital letter, one number, and one special character");
                continue;
            }else if(password.equalsIgnoreCase("E")){
                System.out.println("Exiting the register page...");
                delay();
                startPageManager();
            }
            break;
        }

        try {
            String managerId = generateManagerId();
            String status = "Unapproved";
            Manager manager = new Manager(managerId, name, email, phoneNumber, password, status);
            saveManagerToFile(manager);
            String managerDetails = "[" + manager.getManagerId() + ", " + manager.getName() + ", " + manager.getEmail() + ", " + manager.getPhoneNumber() + "]";
            System.out.println(managerDetails);
            System.out.println("Register successfully! Your registration is waiting for approval.");
        } catch (IOException e) {
            System.out.println("An error occurred while generating manager ID.");
            e.printStackTrace();
        }
    }

    public static boolean isEmailRegistered(String email) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("ManagerInformation.txt"));
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
        BufferedReader reader = new BufferedReader(new FileReader("ManagerInformation.txt"));
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

    public static void saveManagerToFile(Manager manager) throws IOException {
        FileWriter myWriter = new FileWriter("UnapproveUser.txt", true);
        myWriter.write("[" + manager.managerId + ", " + manager.name + ", " + manager.email + ", " + manager.phoneNumber + ", " + manager.password + ", " +  manager.status + "]\r\n");
        myWriter.close();
    }

    // Method to login a manager member
    public static void loginManager() throws IOException {
        boolean found = false;

        while(!found){
            String email = "";
            while (true) {
                System.out.print("Enter your email(Enter 'E' for exit): ");
                email = sc.nextLine().trim();
                if (email.equals("")) {
                    delay();
                    System.out.println("Email cannot be empty");
                    continue;
                }else if(email.equalsIgnoreCase("E")){
                    System.out.println("Exiting the login page...");
                    delay();
                    startPageManager();
                }
                break;
            }

            String password = "";
            while (true) {
                System.out.print("Enter your password(Enter 'E' for exit): ");
                password = sc.nextLine().trim();
                if (password.equals("")) {
                    delay();
                    System.out.println("Password cannot be empty");
                    continue;
                }else if(password.equalsIgnoreCase("E")){
                    System.out.println("Exiting the login page...");
                    delay();
                    startPageManager();
                }
                break;
                
            }
           
           try(BufferedReader reader = new BufferedReader(new FileReader("ManagerInformation.txt"))){
           String line;
           while ((line = reader.readLine()) != null) {
               String[] details = line.replace("[", "").replace("]", "").split(", ");
               String fileStaffId = details[0];
               String fileName = details[1];
               String fileEmail = details[2];
               String filePhoneNumber = details[3];
               String filePassword = details[4];
               String fileStatus = details[5];
   

               if (fileEmail.equals(email) && filePassword.equals(password) && fileStatus.equals("Approved")) {
                   System.out.println("Login successful! Welcome, " + fileName + ", " + fileStaffId);
                   found = true;
                   managerMainPage();
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


    public static void startPageManager(){
        while (true) {
           System.out.println("-------Welcome to APU Hostel Management Fees Payment System (Manager)-------");
           System.out.println("""
           Which option would you like to access?
           1. Register
           2. Login
           3. Exit""");
           System.out.print("Enter your choice: ");
           String choice = sc.nextLine().trim();
           if (choice.equals("1")) {
               registerManager();
               continue;
           } else if (choice.equals("2")) {
                try {
                    loginManager();
                } catch (IOException e) {
                    System.out.println("An error occurred during login.");
                    e.printStackTrace();
                }
                continue;
           } else if (choice.equals("3")) {
               System.out.println("Exiting the system...");
               delay();
               Start.startPage();;
           } else if (choice.equals("")) {
                delay();
                System.out.println("Cannot be empty.");
            
           }else {
               System.out.println("Invalid choice. Please try again.");
           } 
        }

    }

    public static void managerMainPage() {
        while (true) {
            System.out.println("-------Welcome to Manager Management System-------");
            System.out.println("1. Approve user registration");
            System.out.println("2. View user account");
            System.out.println("3. Fix/Update rate");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine().trim();
            if (choice.equals("1")) {
                try {
                    approveUserRegistration();
                } catch (IOException e) {
                    System.out.println("An error occurred while approving user registration.");
                    e.printStackTrace();
                }
            } else if (choice.equals("2")) {
                viewUserAccount();
            } else if (choice.equals("3")) {
                fixUpdateRate();
            } else if (choice.equals("4")) {
                System.out.println("Thank you for using Manager Management System.");
                delay();
                startPageManager();
            } else if (choice.equals("")) {
                delay();
                System.out.println("Cannot be empty.");
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static void approveUserRegistration() throws IOException {
        File file = new File("UnapproveUser.txt");
        if (!file.exists()) {
            System.out.println("No unapproved users found.");
            return;
        }

        List<String> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int index = 1;
        while ((line = reader.readLine()) != null) {
            users.add(line);
            String[] details = line.replace("[", "").replace("]", "").split(", ");
            System.out.println(index + ". " + "ID: " + details[0] + ", Name: " + details[1] + ", Email: " + details[2] + ", Phone: " + details[3] + ", Status: " + details[5]);
            index++;
        }
        reader.close();

        if (users.isEmpty()) {
            System.out.println("No unapproved users found.");
            return;
        }

        while (true) {
        System.out.print("Enter the number of the user to approve (Enter 0 for exit): ");
        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter a valid number.");
            return;
        }
        
        int userIndex;
        try {
            userIndex = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        
        if (userIndex == 0) {
            System.out.println("Exiting the approve user registration page...");
            delay();
            managerMainPage();
        }else if(userIndex < 1 || userIndex > users.size()) {
            System.out.println("Invalid user number.");
            return;
        }else{
            System.out.println("Invalid input. Please try again.");
        }

        

        String selectedUser = users.get(userIndex - 1);
        String[] details = selectedUser.replace("[", "").replace("]", "").split(", ");
        details[5] = "Approved"; // Update status to approved

        // Determine the appropriate file based on the user ID prefix
        String userId = details[0];
        String targetFile = "";
        if (userId.startsWith("S")) {
            targetFile = "StaffInformation.txt";
        } else if (userId.startsWith("R")) {
            targetFile = "ResidentInformation.txt";
        } else if (userId.startsWith("M")) {
            targetFile = "ManagerInformation.txt";
        }

        // Write the approved user to the appropriate file
        BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile, true));
        writer.write("[" + String.join(", ", details) + "]\r\n");
        writer.close();

        // Remove the approved user from the unapproved users list
        users.remove(userIndex - 1);

        // Write the remaining unapproved users back to the file
        writer = new BufferedWriter(new FileWriter(file));
        for (String user : users) {
            writer.write(user);
            writer.newLine();
        }
        writer.close();

        System.out.println("User approved and moved to " + targetFile + " successfully.");
        break;
      }
    }
   

    public static void viewUserAccount() {
        while (true) {
            System.out.println("""
                Choose the type of account to view:
                1. Staff
                2. Resident
                3. Manager
                4. Exit""");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            String fileName = "";
            String userType = "";
            switch (choice) {
                case "1":
                    fileName = "StaffInformation.txt";
                    userType = "Staff";
                    break;
                case "2":
                    fileName = "ResidentInformation.txt";
                    userType = "Resident";
                    break;
                case "3":
                    fileName = "ManagerInformation.txt";
                    userType = "Manager";
                    break;
                case "4":
                    System.out.println("Exiting the view user account page...");
                    return;
                case "":
                    delay();
                    System.out.println("Cannot be empty. Please enter a valid choice.");
                    continue;
                default:
                    System.out.println("Invalid input. Please try again.");
                    continue;
            }
            
            while (true) {
                System.out.print("Please enter the " + userType + " ID (Enter E for exit): ");
                String userId = sc.nextLine();
                
                if (userId.equalsIgnoreCase("e")) {
                    System.out.println("Exiting the view user account page...");
                    delay();
                    managerMainPage();
                } else if (userId.equals("")) {
                    System.out.println("Cannot be empty.");
                    continue;
                }else if(userId.matches("^[MRS]\\d{2}$")){
                    boolean found = false;
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                      String line;
                      while ((line = reader.readLine()) != null) {
                          System.out.println("Reading line: " + line); // Debugging information
                          String[] details = line.replace("[", "").replace("]", "").split(", ");
                          if (details.length < 6) {
                            System.out.println("Error: Invalid data format in file.");
                            continue;
                          }
                          
                          if (details[0].equals(userId)) {
                              System.out.println(userType + " ID: " + details[0]);
                              System.out.println("Name: " + details[1]);
                              System.out.println("Email: " + details[2]);
                              System.out.println("Phone Number: " + details[3]);
                              System.out.println("Status: " + details[5]);
                              found = true;
                          } 
                        }
                    }catch (IOException e) {
                        System.out.println("An error occurred while viewing account.");
                        e.printStackTrace();
                    }
               
                    if (found){
                        System.out.println("""
                        -------------------------------
                        1. Delete account
                        2. Update account
                        3. Exit""");
                        while (true) {
                           System.out.print("Enter your choice: ");
                           String choice2 = sc.nextLine();
                             if (choice2.equals("1")) {
                              while (true) {
                                  System.out.print("Are you sure you want to delete this account? (Y/N): ");
                                  String confirm = sc.nextLine();
                                  if (confirm.equalsIgnoreCase("Y")) {
                                       deleteAccount(userId);
                                  } else if (confirm.equalsIgnoreCase("N")) {
                                       System.out.println("Account deletion cancelled.");
                                       viewUserAccount();;
                                  } else {
                                       System.out.println("Invalid input. Please try again.");
                                  }
                              }
                             } else if (choice2.equals("2")) {
                                  updateAccount(userId);
                                  break;
                             } else if (choice2.equals("3")) {
                                  System.out.println("Exiting the view " + userType + " account page...");
                                  delay();
                                  viewUserAccount();
                            } else if (choice2.equals("")) {
                                  System.out.println("Cannot be empty.");
                            }else {
                                  System.out.println("Invalid input. Please try again.");
                            }
                         }                      
                    }else{
                         System.out.println(userType + " ID not found.");
                         break;
                    }
                }else {
                    System.out.println("Invalid input. Please try again.");
                    continue;
                }
                    
            }
        
        }
    }


    public static void deleteAccount(String userId) {
        String fileName = "";
        char userType = userId.charAt(0);

        switch (userType) {
            case 'M':
                fileName = "ManagerInformation.txt";
                break;
            case 'S':
                fileName = "StaffInformation.txt";
                break;
            case 'R':
                fileName = "ResidentInformation.txt";
                break;
            default:
                System.out.println("Invalid user ID.");
                return;
        }

        List<String> lines = new ArrayList<>();
        boolean found = false;

        // Read the file content into memory
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.replace("[", "").replace("]", "").split(", ");
                if (!details[0].equals(userId)) {
                    lines.add(line);
                } else {
                    found = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (found) {
            System.out.println("User ID " + userId + " deleted successfully.");
        } else {
            System.out.println("User ID " + userId + " not found.");
            return;
        }

        // Write the modified content back to the same file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAccount(String userId)
    {
        String id = userId;
        
        System.out.println("""
        -------Update Details-------
        1) Update Name
        2) Update Email
        3) Update Phone Number
        4) Update Password
        5) Exit""");
        while(true){
        System.out.print("Which option would you like to access? (numbers only): ");
        String choice = sc.nextLine();
        if (choice.equals("1")){
            updateName(id);
            continue;
        }
        else if(choice.equals("2")){
            updateEmail(id);
            continue;
        }
        else if(choice.equals("3")){
            updatePhone(id);
            continue;
        }
        else if(choice.equals("4")){
            updatePass(id);
            continue;
        }
        else if(choice.equals("5")){
            System.out.println("Exiting the system...");
            delay();
            break;
        }else if(choice.equals("")){
            delay();
            System.out.println("Cannot be empty.");
            continue;
        }else{
            System.out.println("Invalid choice. Please try again.");
        }
      }
    }


    public static void updateName(String userId) {
        String fileName = "";
        char userType = userId.charAt(0);

        switch (userType) {
            case 'M':
                fileName = "ManagerInformation.txt";
                break;
            case 'S':
                fileName = "StaffInformation.txt";
                break;
            case 'R':
                fileName = "ResidentInformation.txt";
                break;
            default:
                System.out.println("Invalid user ID.");
                return;
        }

        List<String> lines = new ArrayList<>();
        boolean nameExists = false;

        // Read the file content into memory
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.replace("[", "").replace("]", "").split(", ");
                if (parts.length < 6) {
                    System.out.println("Error: Invalid data format in file.");
                    continue;
                }
                if (parts[0].equals(userId)) {
                    while (true) {
                        System.out.print("Please enter your old name: ");
                        String oldName = sc.nextLine().trim();
                        if (oldName.isEmpty()) {
                            delay();
                            System.out.println("Name cannot be empty");
                            continue;
                        } else if (!oldName.matches("[a-zA-Z\\s]+")) {
                            delay();
                            System.out.println("Name cannot have numbers or syntax");
                            continue;
                        }
                        if (parts[1].equals(oldName)) {
                            while (true) {
                            System.out.print("Enter your new name: ");
                            String newName = sc.nextLine().trim();
                            if (newName.isEmpty()) {
                                delay();
                                System.out.println("Name cannot be empty");
                                continue;
                            } else if (!newName.matches("[a-zA-Z\\s]+")) {
                                delay();
                                System.out.println("Name cannot have numbers or syntax");
                                return;
                            }
                            parts[1] = newName;
                            nameExists = true;
                            line ="[" + String.join(", ", parts) + "]";
                            break;
                            }
                        } else {
                            System.out.println("Old name does not match.");
                            continue;
                        }
                        break;
                    }
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (nameExists) {
            // Write the modified content back to the same file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Name updated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User ID not found or old name does not match.");
        }
    }


    public static void updateEmail(String userId) {
        String fileName = "";
        char userType = userId.charAt(0);

        switch (userType) {
            case 'M':
                fileName = "ManagerInformation.txt";
                break;
            case 'S':
                fileName = "StaffInformation.txt";
                break;
            case 'R':
                fileName = "ResidentInformation.txt";
                break;
            default:
                System.out.println("Invalid user ID.");
                return;
        }

        List<String> lines = new ArrayList<>();
        boolean emailExists = false;

        // Read the file content into memory
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.replace("[", "").replace("]", "").split(", ");
            if (parts.length < 6) {
                System.out.println("Error: Invalid data format in file.");
                continue;
            }
            if (parts[0].equals(userId)) {
                while (true) {
                    System.out.print("Please enter your old email: ");
                    String oldEmail = sc.nextLine().trim();
                    if (oldEmail.isEmpty()) {
                        delay();
                        System.out.println("Email cannot be empty");
                        continue;
                    } else if (!oldEmail.contains("@") || !oldEmail.contains(".com")) {
                        delay();
                        System.out.println("Email must contain '@' and '.com'");
                        continue;
                    }
                    if (parts[2].equals(oldEmail)) {
                        while (true) {
                            System.out.print("Enter your new email: ");
                            String newEmail = sc.nextLine().trim();
                            if (newEmail.isEmpty()) {
                                delay();
                                System.out.print("Email cannot be empty");
                                return;
                            } else if (!newEmail.contains("@") || !newEmail.contains(".com")) {
                                delay();
                                System.out.print("Email must contain '@' and '.com'");
                                return;
                            }
                            parts[2] = newEmail;
                            emailExists = true;
                            line = "[" + String.join(", ", parts) + "]";
                            break;
                        }
                    } else {
                        System.out.println("Old email does not match.");
                        continue;
                    }
                    break;
                }
            }
            lines.add(line);
        }
            
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (emailExists) {
            // Write the modified content back to the same file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Email updated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User ID not found or old email does not match.");
        }
    }



    public static void updatePhone(String userId) {
        String fileName = "";
        char userType = userId.charAt(0);

        switch (userType) {
            case 'M':
                fileName = "ManagerInformation.txt";
                break;
            case 'S':
                fileName = "StaffInformation.txt";
                break;
            case 'R':
                fileName = "ResidentInformation.txt";
                break;
            default:
                System.out.println("Invalid user ID.");
                return;
        }

        List<String> lines = new ArrayList<>();
        boolean phoneExists = false;

        // Read the file content into memory
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.replace("[", "").replace("]", "").split(", ");
                if (parts.length < 6) {
                    System.out.println("Error: Invalid data format in file.");
                    continue;
                }
                if (parts[0].equals(userId)) {
                    while (true) {
                        System.out.print("Please enter your old phone number: ");
                        String oldPhone = sc.nextLine().trim();
                        if (oldPhone.isEmpty()) {
                            delay();
                            System.out.println("Phone number cannot be empty");
                            continue;
                        }else if (!oldPhone.startsWith("01")) {
                            delay();
                            System.out.println("Phone number must start with '01'");
                            continue;
                        }else if (!oldPhone.matches("[0-9]+") || oldPhone.length() != 10){
                            delay();
                            System.out.println("phone number must be 10 digits");
                            continue;
                        } else if (!oldPhone.matches("\\d+")) {
                            delay();
                            System.out.println("Phone number must contain only digits");
                            continue;
                        }
                        if (parts[3].equals(oldPhone)) {
                            while (true) {
                                System.out.print("Enter your new phone number: ");
                                String newPhone = sc.nextLine().trim();
                                if (newPhone.isEmpty()) {
                                    delay();
                                    System.out.println("Phone number cannot be empty");
                                    continue;
                                } else if (!newPhone.startsWith("01")) {
                                    delay();
                                    System.out.println("Phone number must start with '01'");
                                    continue;
                                }else if (!newPhone.matches("[0-9]+") || newPhone.length() != 10){
                                    delay();
                                    System.out.println("phone number must be 10 digits");
                                    continue;
                                } else if (!newPhone.matches("\\d+")) {
                                    delay();
                                    System.out.println("Phone number must contain only digits");
                                    continue;
                                }
                                parts[3] = newPhone;
                                phoneExists = true;
                                line = "[" + String.join(", ", parts) + "]";
                                break;
                            }
                        } else {
                            System.out.println("Old phone number does not match.");
                            continue;
                        }
                        break;
                    }
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (phoneExists) {
            // Write the modified content back to the same file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Phone number updated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User ID not found or old phone number does not match.");
        }
    }


    public static void updatePass(String userId){
        String fileName = "";
        char userType = userId.charAt(0);

        switch (userType) {
            case 'M':
                fileName = "ManagerInformation.txt";
                break;
            case 'S':
                fileName = "StaffInformation.txt";
                break;
            case 'R':
                fileName = "ResidentInformation.txt";
                break;
            default:
                System.out.println("Invalid user ID.");
                return;
        }

        List<String> lines = new ArrayList<>();
        boolean passwordExists = false;

        // Read the file content into memory
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.replace("[", "").replace("]", "").split(", ");
                if (parts.length < 6) {
                    System.out.println("Error: Invalid data format in file.");
                    continue;
                }
                if (parts[0].equals(userId)) {
                    while (true) {
                        System.out.print("Please enter your old password: ");
                        String oldPass = sc.nextLine().trim();
                        if (oldPass.isEmpty()) {
                            delay();
                            System.out.println("Password cannot be empty");
                            continue;
                        } else if (!oldPass.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,14}$")) {
                            delay();
                            System.out.println("Password must be 8-14 characters long, contain at least one capital letter, one number, and one special character");
                            continue;
                        }

                        if (parts[4].equals(oldPass)) {
                            while (true) {
                                System.out.print("Enter your new password: ");
                                String newPass = sc.nextLine().trim();
                                if (newPass.isEmpty()) {
                                    delay();
                                    System.out.println("Password cannot be empty");
                                    return;
                                } else if (!newPass.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,14}$")) {
                                    delay();
                                    System.out.println("Password must be 8-14 characters long, contain at least one capital letter, one number, and one special character");
                                    return;
                                }
                                parts[4] = newPass;
                                passwordExists = true;
                                line = "[" + String.join(", ", parts) + "]";
                                break;
                            }
                        } else {
                            System.out.println("Old password does not match.");
                            return;
                        }
                        break;
                    }
                }
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (passwordExists) {
            // Write the modified content back to the same file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Phone number updated successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("User ID not found or old phone number does not match.");
        }
    }


    public static void fixUpdateRate() {
        // Implement the fixUpdateRate method here
        /* room(standard, family, deluxe) 
         * service (cleaning, food and drink, laundry)
         * tax(service tax, room tax, cleaning tax)
         * additional fee (deposit, damage fee, extra bed fee, late checkout fee)
        */
        // Read the rates from the file


        try (BufferedReader reader = new BufferedReader(new FileReader("rates.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(", ");
                System.out.println(lines[0]);
                //System.out.println("--------Rates--------");
                //System.out.println("1) Standard Room: RM" + lines[0]);
                //System.out.println("2) Family Room: RM" + lines[1]);
                //System.out.println("3) Deluxe Room: RM" + lines[2]);
                //System.out.println("4) Cleaning Service: RM" + lines[3]);
                //System.out.println("5) Food and Drink Service: RM" + lines[4]);
                //System.out.println("6) Laundry Service: RM" + lines[5]);
                //System.out.println("7) Service Tax: RM" + lines[6]);
                //System.out.println("8) Room Tax: RM" + lines[7]);
                //System.out.println("9) Cleaning Tax: RM" + lines[8]);
                //System.out.println("10) Deposit Fee: RM" + lines[9]);
                //System.out.println("11) Damage Fee: RM" + lines[10]);
                //System.out.println("12) Extra Bed Fee: RM" + lines[11]);
                //System.out.println("13) Late Checkout Fee: RM" + lines[12]);
                }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        

        // Display the rates in ascending order
    //    System.out.println("--------Rates--------");
    //    System.out.println("a) Room");
    //    System.out.println("");
    //    System.out.println("b) Service");
    //    rates.entrySet().stream()
    //            .filter(entry -> entry.getKey().toLowerCase().contains("service"))
    //            .sorted(Map.Entry.comparingByKey())
    //            .forEach(entry -> System.out.println("  i) " + entry.getKey() + ": RM" + entry.getValue()));
//
    //    System.out.println("c) Tax");
    //    rates.entrySet().stream()
    //            .filter(entry -> entry.getKey().toLowerCase().contains("tax"))
    //            .sorted(Map.Entry.comparingByKey())
    //            .forEach(entry -> System.out.println("  i) " + entry.getKey() + ": RM" + entry.getValue()));
//
    //    System.out.println("d) Additional Fee");
    //    rates.entrySet().stream()
    //            .filter(entry -> entry.getKey().toLowerCase().contains("fee"))
    //            .sorted(Map.Entry.comparingByKey())
    //            .forEach(entry -> System.out.println("  i) " + entry.getKey() + ": RM" + entry.getValue()));
//
    //    // Ask the manager if they want to update the rates
    //    System.out.print("Do you want to update the rates? (yes/no): ");
    //    String response = sc.nextLine().trim().toLowerCase();
    //    if (response.equals("yes")) {
    //        System.out.print("Enter the name of the rate you want to update: ");
    //        String rateName = sc.nextLine().trim();
    //        if (rates.containsKey(rateName)) {
    //            System.out.print("Enter the new rate for " + rateName + ": RM");
    //            double newRate = sc.nextDouble();
    //            sc.nextLine(); // Consume the newline
    //            rates.put(rateName, newRate);
//
    //            // Write the updated rates back to the file
    //            try (BufferedWriter writer = new BufferedWriter(new FileWriter("rates.txt"))) {
    //                for (Map.Entry<String, Double> entry : rates.entrySet()) {
    //                    writer.write(entry.getKey() + ": RM" + entry.getValue());
    //                    writer.newLine();
    //                }
    //                System.out.println("Rates updated successfully.");
    //            } catch (IOException e) {
    //                e.printStackTrace();
    //            }
    //        } else {
    //            System.out.println("Rate not found.");
    //        }
    //    } else {
    //        System.out.println("No changes made to the rates.");
    //    }
    }
}
/* 
1) Room
  i) Standard Room: RM300.0
  ii) Family Room: RM500.0
  iii) Deluxe Room: RM700.0
2) Tax
  i) Service Tax: RM50.0
  ii) Room Tax: RM100.0
  iii) Cleaning Tax: RM20.0*/
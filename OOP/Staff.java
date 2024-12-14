import java.util.*;
import java.io.*;

public class Staff{
    static Scanner sc = new Scanner(System.in);

    private String staffId;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String status;

    public static void main(String[] args) {   
            startPageStaff();
    }
    
    public static void delay(){
        try {
            Thread.sleep(500); // 1 second delay
        } catch (InterruptedException e) {
            e.printStackTrace(); // This catches and handles any interruption in the sleep
        }
    }

    // Constructor
    public Staff(String staffId, String name, String email, String phoneNumber, String password, String status) {
        this.staffId = staffId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.status = status;
    }

    // Getters and Setters
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    // Method to generate a sequential staff ID from a file
    public static String generateStaffId() throws IOException {
        File file = new File("StaffIDGenerator.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int id = Integer.parseInt(reader.readLine());
        reader.close();

        String staffId = "S" + String.format("%02d", id);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(Integer.toString(id + 1));
        writer.close();

        return staffId;
    }

    // Method to register a new staff member
    public static void registerStaff() {
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
                startPageStaff();
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
                startPageStaff();
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
                startPageStaff();
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
            } else if (!password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,14}$")) {
                delay();
                System.out.println("Password must be 8-14 characters long, contain at least one capital letter, one number, and one special character");
                continue;
            }else if(password.equalsIgnoreCase("E")){
                System.out.println("Exiting the register page...");
                delay();
                startPageStaff();
            }
            break;
        }

        try {
            String staffId = generateStaffId();
            String status = "Unapproved";
            Staff staff = new Staff(staffId, name, email, phoneNumber, password, status);
            saveStaffToFile(staff);
            String staffDetails = "[" + staff.getStaffId() + ", " + staff.getName() + ", " + staff.getEmail() + ", " + staff.getPhoneNumber() + "]";
            System.out.println(staffDetails);
            System.out.println("Register successfully! Your registration is waiting for approval.");
        } catch (IOException e) {
            System.out.println("An error occurred while generating staff ID.");
            e.printStackTrace();
        }
    }

    public static boolean isEmailRegistered(String email) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("StaffInformation.txt"));
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
        BufferedReader reader = new BufferedReader(new FileReader("StaffInformation.txt"));
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

    public static void saveStaffToFile(Staff staff) throws IOException {
        FileWriter myWriter = new FileWriter("UnapproveUser.txt", true);
        myWriter.write("[" + staff.staffId + ", " + staff.name + ", " + staff.email + ", " + staff.phoneNumber + ", " + staff.password + ", " +  staff.status + "]\r\n");
        myWriter.close();
    }

    // Method to login a staff member
    public static void loginStaff() throws IOException {
        String empty = "";
        boolean found = false;

        while(!found){
            String email = "";
            while (true) {
                System.out.print("Enter your email(Enter 'E' for exit): ");
                email = sc.nextLine();
                if (email.equals(empty)) {
                    delay();
                    System.out.println("Email cannot be empty");
                    continue;
                }else if(email.equalsIgnoreCase("E")){
                    System.out.println("Exiting the login page...");
                    delay();
                    startPageStaff();
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
                }else if(password.equalsIgnoreCase("E")){
                    System.out.println("Exiting the login page...");
                    delay();
                    startPageStaff();
                }
                break;
                
            }
           
           try(BufferedReader reader = new BufferedReader(new FileReader("StaffInformation.txt"))){
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
                   staffMainPage();
                   break;
               }
           }reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        if (!found) {
            System.out.println("Invalid email or password. Please try again.");
        } 
     }
   }


    public static void startPageStaff(){
        while (true) {
           System.out.println("-------Welcome to APU Hostel Management Fees Payment System (Staff)-------");
           System.out.println("""
           Which option would you like to access?
           1. Register
           2. Login
           3. Exit""");
           System.out.print("Enter your choice: ");
           String choice = sc.nextLine();
           String empty = "";
           if (choice.equals("1")) {
               registerStaff();
               continue;
           } else if (choice.equals("2")) {
                try {
                    loginStaff();
                } catch (IOException e) {
                    System.out.println("An error occurred during login.");
                    e.printStackTrace();
                }
                continue;
           } else if (choice.equals("3")) {
               System.out.println("Exiting the system...");
               delay();
               Start.startPage();;
           } else if (choice.equals(empty)) {
                delay();
                System.out.println("Cannot be empty.");
            
           }else {
               System.out.println("Invalid choice. Please try again.");
           } 
        }

    }

    public static void staffMainPage(){
        System.out.println("-------Welcome to Staff Management System-------");
        System.out.println("1. Make payment for resident");
        System.out.println("2. Generate receipt");
        System.out.println("3. Update individual login account");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        String choice = sc.next();
    }



    
}

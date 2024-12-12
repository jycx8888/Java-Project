import java.util.*;

public class Start {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        startPage();
    }


    public static void startPage(){   
        while (true){
           System.out.println("------Welcome to APU Hostel Management System------");
           System.out.println("1. Manager ");
           System.out.println("2. Staff ");
           System.out.println("3. Resident ");
           System.out.println("4. Exit ");
           System.out.print("Please select your role: ");
           String role = sc.next();
              if (role.equals("1")){
                Manager.startPageManager();
                break;
              } else if (role.equals("2")){
                Staff.startPageStaff();
                break;
              } else if (role.equals("3")){
                Resident.startPageResident();
                break;
              } else if (role.equals("4")){
                System.out.println("Thank you for using APU Hostel Management System.");
                System.exit(0);
              } else {
                System.out.println("Invalid input. Please try again.");
              }
        }
    }

    
}

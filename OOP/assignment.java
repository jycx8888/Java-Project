import java.util.Scanner;

// import java.util.Scanner;
// import java.io.File;
// import java.util.Scanner;
// import java.io.File;



public class assignment 
{   
    static Scanner sc = new Scanner(System.in);

        public static void delay()
        {
            try {
                Thread.sleep(500); // 1 second delay
            } catch (InterruptedException e) {
                e.printStackTrace(); // This catches and handles any interruption in the sleep
            }
        }

        public static void delay1()
        {
            try {
                Thread.sleep(1000); // 1 second delay
            } catch (InterruptedException e) {
                e.printStackTrace(); // This catches and handles any interruption in the sleep
            }
        }

        //R = resident
        public static void Rmainmenu()
        {
            System.out.println("-------------------------------Welcome Back!-------------------------------");
            System.out.println("""
                Which option would you like to access?
                1) Login 
                2) Register
                3) Exit
                """);
        }


        public static void login()
        {   
            System.out.print("Enter name: ");
            String name = sc.next();
            System.out.print("Enter password: ");
            String password = sc.next();
            System.out.println(name);
            System.out.println(password);
        }


        public static void register()
        {
            while (true) { 
            String empty = "";     
                while (true)
                {
                    System.out.print("Enter name: ");    
                    String name = sc.nextLine();
                    if (name.equals(empty))
                    {
                        delay();
                        System.out.println("name cannot be empty");
                        continue;
                    }
                    else if (!name.matches("[a-zA-Z\\s]+"))
                    {
                        delay();
                        System.out.println("name cannot have numbers or syntax");
                        continue;
                    }
                    break;
                }
                while (true) {
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    if (password.equals(empty)) {
                        delay();
                        System.out.println("password cannot be empty");
                        continue;
                    } else if (!password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,14}$")) {
                        delay();
                        System.out.println("password must be 8-14 characters long, contain at least one capital letter, one number, and one special character");
                        continue;
                    }
                    break;
                }
                while (true)
                {
                    System.out.print("Enter email: ");
                    String email = sc.nextLine();
                    if (email.equals(empty))
                    {
                        delay();
                        System.out.println("email cannot be empty");
                        continue;
                    }
                    else if (!email.contains("@") || !email.contains(".com"))
                    {
                        delay();
                        System.out.println("email must contain '@' and '.com'");
                        continue;
                    }
                    break;
                }
                while (true)
                {
                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine();
                    if (phone.equals(empty))
                    {
                        delay();
                        System.out.println("phone number cannot be empty");
                        continue;
                    }
                    else if (!phone.matches("[0-9]+") || phone.length() != 10)
                    {
                        delay();
                        System.out.println("phone number must be 10 digits");
                        continue;
                    }
                break;
                }
            break;
            }
        }


    public static void main(String[] args)
    {
        while (true) 
        { 
            Rmainmenu();
            System.out.print("Which option would you like to access: ");
            String uoption = sc.nextLine();
            String empty = "";
            if (uoption.equals("1"))
            {
                delay();
                login();
                break;
            }
            else if (uoption.equals("2"))
            {
                register();
                break;
            }
            else if (uoption.equals("3"))
            {
                delay();
                System.out.println("Exiting...");
                delay1();
                System.out.println("Thank you for using our program");
                break;
            }
            else if (uoption.equals(empty))
            {
                delay();
                System.out.println("Cannot be empty");
                delay();
                continue;
            }
            else
            {
                delay();
                System.out.println("Please try again");
                delay();
                continue;
            }
        }
    }
}

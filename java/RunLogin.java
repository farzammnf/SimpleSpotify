import java.util.InputMismatchException;
import java.util.Scanner;

public class RunLogin {
    public static void catchBody() {
        clearScreen();
        System.err.println("invalid command");
        runLogin();
    }

    public static void clearScreen() {
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
    }



    public static void runLogin() {
        String token = null;
        String[] user = printMenu();
        String userName = user[0];
        String PassWord = user[1];
        int command = checkInput();
        if (command == 1) {
           token = ServerAction.LogIn(userName,PassWord);
           if  (token == null) {
               runLogin();
           }
           UserMenu userMenu = new UserMenu();
           userMenu.printMenu(token);

        } else if (command == 2) {
            RunMenu.runMenu();
        } else {
            catchBody();
        }
    }



    public static String[] printMenu() {
        Scanner input = new Scanner(System.in);
        String user[] = new String[2];
        System.out.println("please enter your username :");
        user[0] = input.next();
        System.out.println("please enter your password :");
        user[1] = input.next();
        return user;
    }

    public static int checkInput() {
        System.out.println("please chose a command : \n"
                + "--------------------\n"
                + "1) Log in\n"
                + "2) Exit to menu\n");
        Scanner input = new Scanner(System.in);
        int command = 0;
        try {
            command = input.nextInt();
        } catch (InputMismatchException e) {
            catchBody();
        }
        return command;
    }
}

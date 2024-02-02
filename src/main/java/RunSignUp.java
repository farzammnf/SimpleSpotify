import java.util.InputMismatchException;
import java.util.Scanner;

public class RunSignUp {

    public static void catchBody() {
        System.err.println("invalid command");
        clearScreen();
        runSignUp();
    }

    public static void clearScreen() {
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
    }

    public static void createUser(String username,String password) {
        String token = ServerAction.SignUp(username,password);
        while (token == null) {
            runSignUp();
        }
        clearScreen();
        RunMenu.runMenu();
    }

    public static void runSignUp() {
        int command = checkInput();
        if (command == 1) {
            String[] user = printMenu();
            if (user != null) {
                String username = user[0];
                String password = user[1];
                createUser(username, password);
            } else {
                runSignUp();
            }
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
        if (!user[1].matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            System.err.println("Password does not have the requirements!");
            System.err.println("Please try again");
            return null;
        }

        return user;
    }

    public static int checkInput() {
        System.out.println("please chose a command : \n"
                + "--------------------\n"
                + "1) Sign Up\n"
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

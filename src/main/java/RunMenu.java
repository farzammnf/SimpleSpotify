

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.auth.ApiKeyAuth;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RunMenu {

    public static void catchBody() {
        clearScreen();
        System.err.println("invalid command");
        runMenu();
    }
    public static void clearScreen() {
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
    }
    public static void runMenu() {
        printMenu();
        int command = checkInput();
        if (command==1) {
            RunLogin.runLogin();
        }
        else if (command==2) {
            RunSignUp.runSignUp();
        }
        else if (command==3) {
            try {
                reset();
                System.out.println("DataBase is now reset!");
            } catch (ApiException e) {
                System.out.println(e.getResponseHeaders());
            }
            runMenu();
        }
        else {
            catchBody();
        }
    }
    public static void printMenu() {
        System.out.print(
                "please chose a command : \n"
                        + "--------------------\n"
                        + "1) Log in\n"
                        + "2) Sign Up\n"
                        + "3) Reset\n"
                        + "--------------------\n");
    }
    public static int checkInput() {
        Scanner input = new Scanner(System.in);
        int command = 0;
        try {
            command = input.nextInt();
        } catch (InputMismatchException e) {
            catchBody();
        }
        return command;
    }
    public static void reset() throws ApiException {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        DefaultApi defaultApi = new DefaultApi();
        defaultApi.ping();
        defaultApi.reset();
    }
}

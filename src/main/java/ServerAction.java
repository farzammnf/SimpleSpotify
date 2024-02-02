import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.AuthApi;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.AuthLoginBody;
import io.swagger.client.model.AuthSignupBody;

public class ServerAction {
    public static String SignUp(String user, String pass) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();

        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        AuthApi authApi = new AuthApi(defaultClient);
        String token = null;
        try {
            AuthSignupBody authSignupBody = new AuthSignupBody();
            authSignupBody.setUsername(user);
            authSignupBody.setPassword(pass);
            token = (authApi.signUp(authSignupBody).getToken());
            System.out.println("user signed up!");
        } catch (ApiException apiException) {
            System.err.println(apiException.getResponseBody());
        }
        return token;
    }
    public static String LogIn(String user, String pass) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        AuthApi authApi = new AuthApi(defaultClient);
        String token = null;
        try {

            AuthLoginBody authLoginBody = new AuthLoginBody();
            authLoginBody.setUsername(user);
            authLoginBody.setPassword(pass);
            token = (authApi.login(authLoginBody).getToken());
            System.out.println("Welcome " + user + " !");
        } catch (ApiException apiException) {

            System.err.println(apiException.getResponseBody());
        }
        return token;
    }


}


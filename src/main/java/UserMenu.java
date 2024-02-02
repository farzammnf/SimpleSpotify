import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.PremiumUsersApi;
import io.swagger.client.api.UsersApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.auth.OAuth;
import io.swagger.client.model.PlaylistsBody;
import io.swagger.client.model.Track;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class UserMenu {
    boolean isPremium = false;
    String token;

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void printMenu(String token) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        UsersApi usersApi = new UsersApi(defaultClient);
        defaultClient.setAccessToken(token);
        setToken(token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(token);
        try {
            if (usersApi.getProfileInfo().getPremiumUntil() != null) {
                setPremium(true);
                mainMenuPremium();
            } else {
                mainMenuUser();
            }
        } catch (ApiException apiException) {
            System.out.println(apiException.getResponseBody());
        }
    }

    public void mainMenuUser() throws ApiException {
        System.out.println("---------------------");
        System.out.println("1) Show profile info");
        System.out.println("2) All Music");
        System.out.println("3) My Playlists");
        System.out.println("4) New PlayList");
        System.out.println("5) Upgrade To Premium");
        System.out.println("6) Log Out");
        user();


    }

    public void mainMenuPremium() throws ApiException {
        System.out.println("---------------------");
        System.out.println("1) Show profile info");
        System.out.println("2) All Music");
        System.out.println("3) My Playlists");
        System.out.println("4) New PlayList");
        System.out.println("5) Add Friend");
        System.out.println("6) Friend requests");
        System.out.println("7) Show Friend PlayList");
        System.out.println("8) Friend-list");
        System.out.println("9) Log-Out");
        user();
    }

    public void user() throws ApiException {
        Scanner input = new Scanner(System.in);
        int command = 0;
        try {
            command = input.nextInt();
        } catch (InputMismatchException e) {
            catchBody();

        }

        if (isPremium) {
            switch (command) {
                case 1:
                    showProfile();
                case 2:
                    allMusic();
                case 3:
                    myPlayList();
                case 4:
                    createPlayList();
                case 5:
                    addFriend();
                case 6:
                    friendRequest();
                case 7:
                    friendPlaylist();
                case 8 :
                    showFriendList();
                case 9:
                    RunMenu.runMenu();
                default:
                    catchBody();
            }
        } else {
            switch (command) {
                case 1:
                    showProfile();
                case 2:
                    allMusic();
                case 3:
                    myPlayList();
                case 4:
                    createPlayList();
                case 5:
                    upgradeToPremium();
                case 6:
                    RunMenu.runMenu();
                default:
                    catchBody();
            }

        }
    }

    public void catchBody() throws ApiException {
        System.err.println("invalid command");
        printMenu(token);
    }

    public void showProfile() throws ApiException {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        UsersApi usersApi = new UsersApi(defaultClient);
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        System.out.println("UserName : " + usersApi.getProfileInfo().getUsername());
        System.out.println("Premium Status : " + usersApi.getProfileInfo().getPremiumUntil());
        System.out.println("--------------");
        System.out.println("1) Back to Menu");
        Scanner input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 1) {
            printMenu(this.token);
        } else {
            showProfile();
        }
    }

    public void upgradeToPremium() throws ApiException {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        UsersApi usersApi = new UsersApi(defaultClient);
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        try {
            System.out.println(usersApi.upgradeToPremiumTest());
            System.out.println("Upgrade was successful!");
            setPremium(true);
        } catch (ApiException apiException) {
            System.out.println(apiException.getResponseBody());
        }
        System.out.println("--------------");
        System.out.println("1) Back to Menu");
        Scanner input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 1) {
            printMenu(this.token);
        } else {
            upgradeToPremium();
        }
    }

    public void allMusic() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        UsersApi usersApi = new UsersApi(defaultClient);
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        try {
            if (!isPremium) {
                for (Track track : usersApi.getTracksInfo()) {
                    if (!track.isIsPremium()) {
                        System.out.println(track.toString());
                    }
                }
            } else {
                System.out.println(usersApi.getTracksInfo().toString());
            }
        } catch (ApiException apiException) {
            System.out.println(apiException.getResponseBody());
        }
        System.out.println("--------------");
        System.out.println("1) Back to Menu");
        Scanner input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 1) {
            printMenu(this.token);
        } else {
            allMusic();
        }

    }

    public void myPlayList() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        UsersApi usersApi = new UsersApi(defaultClient);
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        try {
            System.out.println(usersApi.getPlaylistsInfo().toString());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println("--------------");
        System.out.println("1) Back to Menu");
        System.out.println("2) Edit PlayList");
        System.out.println("3) Delete PlayList");
        Scanner input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 1) {
            printMenu(this.token);
        } else if (command == 2) {
            editPlayList();
        } else if (command == 3) {
            System.out.println("enter the id of PlayList you want to delete : ");
            int id = input.nextInt();
            try {
                System.out.println(usersApi.deletePlaylist(id));
            } catch (ApiException apiException) {
                System.out.println(apiException.getResponseBody());
            }
            System.out.println("Playlist removed!");
            myPlayList();
        } else {
            myPlayList();
        }
    }

    public void editPlayList() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        UsersApi usersApi = new UsersApi(defaultClient);
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        System.out.println("1) add new song to PlayList");
        System.out.println("2) remove song from PlayList");
        System.out.println("3) Back");
        Scanner input = new Scanner(System.in);
        int com = input.nextInt();
        if (com == 1) {
            System.out.println("Please Enter id of PlayList");
            int playListId = input.nextInt();
            System.out.println("Please Enter id of Song");
            String songId = input.next();
            try {
                System.out.println(usersApi.addTrackToPlaylist(playListId, songId));
                System.out.println(songId + " Added! ");
            } catch (ApiException apiException) {
                System.out.println(apiException.getResponseBody());
            }
            editPlayList();
        } else if (com == 2) {
            System.out.println("Please Enter id of PlayList");
            int playListId = input.nextInt();
            System.out.println("Please Enter id of Song");
            String songId = input.next();
            try {
                System.out.println(usersApi.removeTrackFromPlaylist(playListId, songId));
                System.out.println(songId + " Removed! ");
            } catch (ApiException apiException) {
                System.out.println(apiException.getResponseBody());
            }
            editPlayList();
        } else if (com == 3) {
            myPlayList();
        } else {
            editPlayList();
        }
    }

    public void createPlayList() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        UsersApi usersApi = new UsersApi(defaultClient);
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        Scanner input = new Scanner(System.in);
        System.out.println("please enter a name for your playlist : ");
        String name = input.next();
        try {
            PlaylistsBody playlistsBody = new PlaylistsBody();
            playlistsBody.setName(name);
            usersApi.createPlaylist(playlistsBody);
            System.out.println("PlayList Added! ");
            System.out.println("go to My-PlayLists in Menu to manage your Playlist");
        } catch (ApiException apiException) {
            System.out.println(apiException.getResponseBody());
        }
        createPlayListEx();

    }

    public void createPlayListEx() {
        System.out.println("--------------");
        System.out.println("1) Back to Menu");
        Scanner input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 1) {
            printMenu(this.token);
        } else {
            createPlayListEx();
        }
    }

    public void addFriend() {
        System.out.println("--------------");
        System.out.println("1) Send Friend-Request");
        System.out.println("2) Back to Menu");
        Scanner input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 1) {
            sendReq();
        }
        else if (command == 2) {
            printMenu(this.token);
        }
        else {
            addFriend();
        }
    }
    public void sendReq() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        PremiumUsersApi premiumUsersApi = new PremiumUsersApi(defaultClient);
        System.out.println("Enter Username : ");
        Scanner input = new Scanner(System.in);
        String user = input.next();
        try {
            System.out.println(premiumUsersApi.addFriend(user));
            System.out.println("friend request sent !");
        } catch (ApiException apiException) {
            System.out.println(apiException.getResponseBody());
        }
        addFriend();
    }

    public void friendRequest() {
        System.out.println("--------------");
        System.out.println("1) show Friend-Requests");
        System.out.println("2) Accept Friend-Request");
        System.out.println("3) Back to Menu");
        Scanner input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 1) {
            showRequest();
        }
        else if (command == 2) {
            acceptFriendRequest();
        }
        else if (command == 3) {
            printMenu(this.token);
        }
        else {
            addFriend();
        }
    }

    public void showRequest() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        PremiumUsersApi premiumUsersApi = new PremiumUsersApi(defaultClient);
        try {
            System.out.println(premiumUsersApi.getFriendRequests());
        } catch (ApiException apiException) {
            System.out.println(apiException.getResponseBody());
        }
        friendRequest();
    }

    public void acceptFriendRequest() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        PremiumUsersApi premiumUsersApi = new PremiumUsersApi(defaultClient);
        System.out.println("Enter Username : ");
        Scanner input = new Scanner(System.in);
        String user = input.next();
        try {
            System.out.println(premiumUsersApi.addFriend(user));
        } catch (ApiException apiException) {
            System.out.println(apiException.getResponseBody());
        }
        friendRequest();
    }

    public void friendPlaylist() {
        System.out.println("--------------");
        System.out.println("1) show Friend-PlayList");
        System.out.println("2) Back to Menu");
        Scanner input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 1) {
            showPlayList();
        }
        else if (command == 2) {
            printMenu(this.token);
        }
        else {
            addFriend();
        }
    }

    public void showPlayList() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        PremiumUsersApi premiumUsersApi = new PremiumUsersApi(defaultClient);
        System.out.println("Enter Username : ");
        Scanner input = new Scanner(System.in);
        String user = input.next();
        try {
            System.out.println(premiumUsersApi.getFriendPlaylists(user));
        } catch (ApiException apiException) {
            System.out.println(apiException.getCode());
            System.out.println(apiException.getResponseBody());
        }
        friendPlaylist();
    }

    public void showFriendList() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
        ApiKeyAuth.setApiKey("2f579bff58f71590c1af2f9f513844ca2de81d5d");
        defaultClient.setAccessToken(this.token);
        OAuth bearerAuth = (OAuth) defaultClient.getAuthentication("bearerAuth");
        bearerAuth.setAccessToken(this.token);
        PremiumUsersApi premiumUsersApi = new PremiumUsersApi(defaultClient);
        try {
            System.out.println(premiumUsersApi.getFriends());
        } catch (ApiException apiException) {
            System.out.println(apiException.getResponseBody());
        }
        System.out.println("-----------------");
        System.out.println("1) Back");
        Scanner input = new Scanner(System.in);
        int command = input.nextInt();
        if (command == 1) {
            printMenu(this.token);
        }
        else {
            showFriendList();
        }
    }
}

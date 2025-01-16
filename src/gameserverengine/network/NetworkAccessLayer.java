package gameserverengine.network;

import com.sun.javafx.util.Utils;
import gameserverengine.enums.RequestTypesEnum;
import gameserverengine.local.DataAccessLayer;
import gameserverengine.models.LoginRequestModel;
import gameserverengine.models.LoginResponseModel;
import gameserverengine.models.RequestModel;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import gameserverengine.utils.JsonUtils;
import static gameserverengine.utils.JsonUtils.jsonToInvitationModel;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;
import gameserverengine.models.InvitationModel;

public class NetworkAccessLayer {

    private static ServerSocket serverSocket;
    private static boolean running = true;
    
    public static void startListen() {
        try {
            serverSocket = new ServerSocket(1422);
            System.out.println("Server started on port 1422...");
            while (running) {
                new AuthHandler(serverSocket.accept());
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            stop();
        }
    }

    public static void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server Stopped...");
            }
        } catch (IOException e) {
            Logger.getLogger(NetworkAccessLayer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

class AuthHandler extends Thread {

    private BufferedReader inputReader;
    private PrintWriter outputWriter;
    private ArrayList<UserModel> users ;
    public AuthHandler(Socket clientSocket) {
        try {
            System.out.println("lodmcolsdmdsc");
            inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            this.start();
        } catch (IOException ex) {
            Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            String receivedJson = inputReader.readLine();
          //  RequestModel rewuestModel =  JsonUtils.jsonToRequestModel(receivedJson); 
          //  System.out.println(invitationModel);
          
          //         System.out.println(rewuestModel.getJsonData());
                   
            if (receivedJson != null) {
                RequestModel request = JsonUtils.jsonToRequestModel(receivedJson);
                if (request.getType() == RequestTypesEnum.REGISTER) {
                    register(request.getJsonData());
                } else if (request.getType() == RequestTypesEnum.LOGIN) {
                    login(request.getJsonData());
                } else if (request.getType() == RequestTypesEnum.USERSTABLE) {
                    sendOnlineUsers();
                } else if (request.getType() == RequestTypesEnum.INVITATION) {
                    reciveClientRequest(request.getJsonData());
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
    }

    private void register(String receivedJson) {
        UserModel user = JsonUtils.jsonToUserModel(receivedJson);
        System.out.println("Deserialized UserModel: " + user.getUserName());
        ResponseModel response = DataAccessLayer.register(user);
        String responseJson = JsonUtils.responseModelToJson(response);
        outputWriter.println(responseJson);
        System.out.println("Response sent to client as JSON: " + responseJson);
    }

    private void login(String receivedJson) {
        System.out.println("Deserialized UserModel: " + receivedJson);
        LoginRequestModel user = JsonUtils.jsonToLoginRequestModel(receivedJson);
        LoginResponseModel response = DataAccessLayer.login(user.getUserName(), user.getPassword());
        String responseJson = JsonUtils.responseModelToJson(response);
        outputWriter.println(responseJson);
        System.out.println("Response sent to client as JSON: " + responseJson);
    }

    private ArrayList<UserModel> sendOnlineUsers() {
        users = DataAccessLayer.getOnlinePlayer();
        if (users != null) {
            String arrayJson = JsonUtils.usersArrayToJson(users);
            System.out.println(arrayJson);
            outputWriter.println(arrayJson);
        } else {
            System.out.println("no online users");
        }
        return users;

    }

    public void reciveClientRequest(String receivedJson) {
         InvitationModel invitationModel = jsonToInvitationModel(receivedJson);
        System.out.println(invitationModel.getFrom());
        System.out.println(invitationModel.getTo());
        findPlayerThatIsInvited(invitationModel);
    }
public void findPlayerThatIsInvited(InvitationModel invitationModel) {
    String invitedUser = invitationModel.getTo();
    for (UserModel user : users) {
        if (user.getUserName().equals(invitedUser)) {
            sendInvitationToClient(invitationModel);
            return;
        }
    }
    System.out.println("User  " + invitedUser + " is not online.");
}
    public void sendInvitationToClient(InvitationModel invitationModel) {
    String invitationJson = JsonUtils.invitationModelToJson(invitationModel);
    outputWriter.println(invitationJson);
    System.out.println("Invitation sent to client: " + invitationJson);
}
    private void closeConnection() {
        try {
            inputReader.close();
            outputWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

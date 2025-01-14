package gameserverengine.network;

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
import java.util.ArrayList;

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
            if (receivedJson != null) {
                RequestModel request = JsonUtils.jsonToRequestModel(receivedJson);
                if(request.getType()==RequestTypesEnum.REGISTER){
                    register(request.getJsonData());
                }
                else if(request.getType()==RequestTypesEnum.LOGIN){
                    login(request.getJsonData());
                }
                 else if(request.getType()==RequestTypesEnum.USERSTABLE){
                    sendOnlineUsers();
                    
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
        LoginResponseModel response = DataAccessLayer.login(user.getUserName() , user.getPassword());
        String responseJson = JsonUtils.responseModelToJson(response);
        outputWriter.println(responseJson);
        System.out.println("Response sent to client as JSON: " + responseJson);
    }
    private void sendOnlineUsers(){
        ArrayList <UserModel> users= DataAccessLayer.getOnlinePlayer();
        if(users!=null){
        String arrayJson = JsonUtils.usersArrayToJson(users);
        System.out.println(arrayJson);
        outputWriter.println(arrayJson);
        }
        else{
            System.out.println("no online users");
        }
        
        
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

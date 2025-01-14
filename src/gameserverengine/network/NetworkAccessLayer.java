package gameserverengine.network;

import gameserverengine.enums.RequestTypesEnum;
import gameserverengine.local.DataAccessLayer;
import gameserverengine.models.LoginRequestModel;
import gameserverengine.models.LoginResponseModel;
import gameserverengine.models.RequestModel;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;
import gameserverengine.utils.Consts;
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
            serverSocket = new ServerSocket(Consts.PORT);
            System.out.println("Server started on port " + Consts.PORT + "...");

            while (running) {
                try {
                    new AuthHandler(serverSocket.accept());
                } catch (IOException e) {
                    if (running) { // Log only if not shutting down
                        Logger.getLogger(NetworkAccessLayer.class.getName()).log(Level.SEVERE, null, e);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            stop();
        }
    }

    public static void stop() {
        running = false; // Set running to false to exit the loop
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close(); // Close the server socket
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
    private Socket clientSocket;

    public AuthHandler(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
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

            if (inputReader != null) inputReader.close();
            if (outputWriter != null) outputWriter.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

package gameserverengine.network;

import gameserverengine.enums.RequestTypesEnum;
import gameserverengine.interfaces.LocalDatabaseFunctions;
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


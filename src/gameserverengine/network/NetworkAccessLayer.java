package gameserverengine.network;

import gameserverengine.enums.RequestTypesEnum;
import gameserverengine.local.DataAccessLayer;
import gameserverengine.models.GameRoomModel;
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
import java.util.Vector;
import piratesproject.models.InvitationModel;

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



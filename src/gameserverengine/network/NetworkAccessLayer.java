package gameserverengine.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkAccessLayer {

    private static ServerSocket serverSocket;
    private static volatile boolean running = true; // Ensure thread-safe access
    public static final Vector<AuthHandler> clientsVector = new Vector<>(); // Manage connected clients

    public static void startListen() {
        try {
            serverSocket = new ServerSocket(1422);
            System.out.println("Server started on port 1422...");

            while (running) {
                try {
                    AuthHandler handler = new AuthHandler(serverSocket.accept());
                    synchronized (clientsVector) {
                        clientsVector.add(handler);
                    }
                } catch (IOException e) {
                    if (running) {
                        Logger.getLogger(NetworkAccessLayer.class.getName()).log(Level.SEVERE, "Error accepting connection", e);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(NetworkAccessLayer.class.getName()).log(Level.SEVERE, "Server failed to start", ex);
        } finally {
            stop();
        }
    }

    public static void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            synchronized (clientsVector) {
                for (AuthHandler handler : clientsVector) {
                    handler.removeConnection();
                }
                clientsVector.clear();
            }

            System.out.println("Server stopped...");
        } catch (IOException e) {
            Logger.getLogger(NetworkAccessLayer.class.getName()).log(Level.SEVERE, "Error stopping server", e);
        }
    }
}

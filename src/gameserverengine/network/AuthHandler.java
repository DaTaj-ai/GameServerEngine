package gameserverengine.network;

import gameserverengine.GameServerController;
import gameserverengine.enums.RequestTypesEnum;
import gameserverengine.local.DataAccessLayer;
import gameserverengine.models.GameModel;
import gameserverengine.models.GameRoomModel;
import gameserverengine.models.LoginRequestModel;
import gameserverengine.models.Player;
import gameserverengine.models.RequestModel;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;
import gameserverengine.utils.Consts;
import gameserverengine.utils.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import piratesproject.models.InvitationModel;

public class AuthHandler extends Thread {

    private BufferedReader inputReader;
    private PrintWriter outputWriter;
    private Socket clientSocket;
    private String threadOwner;

    public static Vector<AuthHandler> clientsVector = new Vector<AuthHandler>();

    public AuthHandler(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            // AuthHandler.clientsVector.add(this);
            this.start();
        } catch (IOException ex) {
            Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String receivedJson = inputReader.readLine();
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
                    } else if (request.getType() == RequestTypesEnum.CONFIRM_INVITATION) {
                        startGame(request.getJsonData());

                    } else if (request.getType() == RequestTypesEnum.GAMEMOVE) {
                        sendMove(request.getJsonData());
                    } else if (request.getType() == RequestTypesEnum.EXIT) {
                        removeConnection();
                    } else if (request.getType() == RequestTypesEnum.AVALIBALE) {
                        setAvalible(request.getJsonData());
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void register(String receivedJson) {
        UserModel user = JsonUtils.jsonToUserModel(receivedJson);
        System.out.println("Deserialized UserModel: " + user.getUserName());
        AuthHandler.clientsVector.add(this);
        ResponseModel response = DataAccessLayer.register(user);
        String responseJson = JsonUtils.responseModelToJson(response);
        outputWriter.println(responseJson);
        System.out.println("Response sent to client as JSON: " + responseJson);
        threadOwner = user.getUserName();
    }

    private void login(String receivedJson) {
        System.out.println("Deserialized UserModel: " + receivedJson);
        LoginRequestModel user = JsonUtils.jsonToLoginRequestModel(receivedJson);
        ResponseModel response = DataAccessLayer.login(user.getUserName(), user.getPassword());
        AuthHandler.clientsVector.add(this);
        String responseJson = JsonUtils.responseModelToJson(response);
        outputWriter.println(responseJson);
        System.out.println("Response sent to client as JSON: " + responseJson);
        threadOwner = user.getUserName();
        Stage stage = null;
        GameServerController gameServerController = new GameServerController(stage);
        gameServerController.setgraphstate();
        try {
            String userName = user.getUserName();
            DataAccessLayer.setOnline(userName, Consts.ONLINE);
        } catch (SQLException ex) {
            Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendOnlineUsers() {
        ArrayList<UserModel> users = DataAccessLayer.getOnlinePlayer();
        System.out.println("users sended1");
        if (users != null) {
            String arrayJson = JsonUtils.usersArrayToJson(users);
            ResponseModel response = new ResponseModel(1, "", arrayJson, RequestTypesEnum.USERSTABLE);
            String responseJson = JsonUtils.responseModelToJson(response);
            outputWriter.println(responseJson);
        } else {
            System.out.println("no online users");
        }
    }

    public void reciveClientRequest(String receivedJson) {
        ResponseModel response = new ResponseModel(1, "start send", "", RequestTypesEnum.START_SENDING);
        String responseJson = JsonUtils.responseModelToJson(response);
        outputWriter.println(responseJson);
        sendInvitation(JsonUtils.jsonToInvitationModel(receivedJson));
    }

    public AuthHandler findRecipientHandler(String recipient) {
        for (AuthHandler handler : clientsVector) {
            if (handler.getThreadOwner().equals(recipient)) {
                return handler;
            }
        }
        return null;
    }

    public void sendInvitation(InvitationModel invitation) {
        System.out.println(JsonUtils.invitationModelToJson(invitation));

        AuthHandler recipientHandler = findRecipientHandler(invitation.getTo());
        if (recipientHandler != null) {
            ResponseModel response = new ResponseModel(
                    1,
                    "invitation",
                    invitation.getFrom(),
                    RequestTypesEnum.RECIEVE_INVITATION
            );

            String responseJson = JsonUtils.responseModelToJson(response);
            recipientHandler.outputWriter.println(responseJson);

            System.out.println("Invitation sent successfully.");
        } else {
            System.out.println("Recipient not found.");
        }
    }

    public void removeConnection() {
        try {
            DataAccessLayer.setOnline(threadOwner, 0);
            System.out.println("User : " + threadOwner + " logged out !!");
            clientsVector.remove(this);
            this.stop();
            outputWriter.close();
            inputReader.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMove(String receivedJson) {
        System.out.println("333333333333");
        System.out.println(receivedJson);
        GameModel move = JsonUtils.jsonToGameModel(receivedJson);
        String selectedUserName = move.getUsername();
        AuthHandler handler = getHandlerByOwner(selectedUserName);
        String data = JsonUtils.moveModelToJson(move.getMove());
        ResponseModel response = new ResponseModel(1, "", data, RequestTypesEnum.GAMEMOVE);
        String responseJson = JsonUtils.responseModelToJson(response);
        handler.outputWriter.println(responseJson);

    }

    public void startGame(String receivedJson) {
        InvitationModel invitationModel = JsonUtils.jsonToInvitationModel(receivedJson);
        String user1 = invitationModel.getFrom();
        String user2 = invitationModel.getTo();
        AuthHandler h1 = getHandlerByOwner(user1);
        AuthHandler h2 = getHandlerByOwner(user2);
        GameRoomModel model = new GameRoomModel(new Player(user1, "X"), new Player(user2, "O"));
        String game = JsonUtils.gameRoomModelToJson(model);
        ResponseModel response = new ResponseModel(1, "", game, RequestTypesEnum.CREATE_ROOM);
        String responseJson = JsonUtils.responseModelToJson(response);
        h1.outputWriter.println(responseJson);
        h2.outputWriter.println(responseJson);
    }

    private AuthHandler getHandlerByOwner(String username) {
        for (AuthHandler handler : clientsVector) {
            if (handler.getThreadOwner().equals(username)) {
                return handler;
            }
        }
        return null;
    }

    public String getThreadOwner() {
        return threadOwner;
    }

    private void closeConnection() {
        try {

            if (inputReader != null) {
                inputReader.close();
            }
            if (outputWriter != null) {
                outputWriter.close();
            }
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(AuthHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setAvalible(String receivedJson) {
        int value = Integer.parseInt(receivedJson);
        DataAccessLayer.setAvilableStatus(threadOwner,value);
    }
}

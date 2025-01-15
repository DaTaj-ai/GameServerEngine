package gameserverengine.models;

import gameserverengine.network.AuthHandler;
import java.net.Socket;

public class GameRoomModel {
    
    private String id;
    private String player1UserName;
    private String player2UserName;
    private AuthHandler player1handler;
    private AuthHandler player2handler;
    
    

    public GameRoomModel() {
    }

    public GameRoomModel(String id, String player1UserName, String player2UserName, AuthHandler player1handler, AuthHandler player2handler) {
        this.id = id;
        this.player1UserName = player1UserName;
        this.player2UserName = player2UserName;
        this.player1handler = player1handler;
        this.player2handler = player2handler;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayer1UserName() {
        return player1UserName;
    }

    public void setPlayer1UserName(String player1UserName) {
        this.player1UserName = player1UserName;
    }

    public String getPlayer2UserName() {
        return player2UserName;
    }

    public void setPlayer2UserName(String player2UserName) {
        this.player2UserName = player2UserName;
    }

    public AuthHandler getPlayer1handler() {
        return player1handler;
    }

    public void setPlayer1handler(AuthHandler player1handler) {
        this.player1handler = player1handler;
    }

    public AuthHandler getPlayer2handler() {
        return player2handler;
    }

    public void setPlayer2handler(AuthHandler player2handler) {
        this.player2handler = player2handler;
    }
    
    
    
}

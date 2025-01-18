package gameserverengine.models;

public class GameModel {
    private String username;
    private MoveModel move;

    public GameModel(String username, MoveModel move) {
        this.username = username;
        this.move = move;
    }

    public GameModel() {
    }
    
      public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MoveModel getMove() {
        return move;
    }

    public void setMove(MoveModel move) {
        this.move = move;
    }
}

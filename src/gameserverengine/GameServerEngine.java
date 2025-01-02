package gameserverengine;

import gameserverengine.local.DataAccessLayer;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;
import gameserverengine.utils.Consts;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameServerEngine extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new BorderPane();
        Scene scene = new Scene(root);
        
        stage.setTitle(Consts.APP_NAME);
        Image image = new Image(getClass().getResource(Consts.APP_LOGO_PATH).toString());
        stage.getIcons().add(image);
        
        UserModel user = new UserModel("abdo","kamel","abdokamel8886","12345678");
        ResponseModel response  = DataAccessLayer.register(user);
        
        System.out.println("Status : "+response.getStatus());
        System.out.println("Message : "+response.getMessage());
        
        stage.setScene(scene);
        stage.show();
        
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}

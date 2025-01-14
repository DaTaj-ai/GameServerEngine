package gameserverengine;

import gameserverengine.local.DataAccessLayer;
import gameserverengine.models.LoginResponseModel;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;
import gameserverengine.network.NetworkAccessLayer;
import gameserverengine.utils.Consts;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static gameserverengine.local.DataAccessLayer.getOnlinePlayer;

public class GameServerEngine extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new FXMLDocumentBase(stage);
        Scene scene = new Scene(root);

        stage.setTitle(Consts.APP_NAME);
        Image image = new Image(getClass().getResource(Consts.APP_LOGO_PATH).toString());
        stage.getIcons().add(image);
        
      
        stage.setScene(scene);
        stage.show();

        new Thread(() -> NetworkAccessLayer.startListen()).start();
        
        stage.setOnCloseRequest((event) -> {
            System.out.println("Closing application...");
            NetworkAccessLayer.stop();
            System.exit(0);
            Platform.exit();
        });

    }

    public static void main(String[] args) {
        launch(args);
        
    }

    @Override
    public void stop() {
        NetworkAccessLayer.stop();
    }

}

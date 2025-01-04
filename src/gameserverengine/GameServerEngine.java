package gameserverengine;

import gameserverengine.local.DataAccessLayer;
import gameserverengine.models.LoginResponseModel;
import gameserverengine.models.ResponseModel;
import gameserverengine.models.UserModel;
import gameserverengine.network.NetworkAccessLayer;
import gameserverengine.utils.Consts;
import javafx.application.Application;
import javafx.application.Platform;
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
<<<<<<< HEAD
        
        //UserModel user = new UserModel("abdo","kamel","abdokamel8886","12345678");
        //ResponseModel response  = DataAccessLayer.register(user);
        
        UserModel result = DataAccessLayer.getUser("abdokamel8886");
        System.out.println(result.getFirstName() + "this is the first user name ");
        System.out.println(result.getLastName());
        System.out.println(result.getUserName());
        
        LoginResponseModel testLogin = DataAccessLayer.login("abdokamel8886" , "dkjhd");
        System.out.println(testLogin.getStatus());
        System.out.println(testLogin.getMessage());
        
        LoginResponseModel testLogin2 = DataAccessLayer.login("abdokamel8886" , "12345678");
        System.out.println("this is number 2 \n"+testLogin2.getStatus());
        System.out.println(testLogin2.getMessage());
        
        LoginResponseModel testLogin3 = DataAccessLayer.login("abdokael8886" , "dkjhd");
        System.out.println(testLogin3.getStatus());
        System.out.println(testLogin3.getMessage());
        
        
        
        
       // System.out.println("Status : "+response.getStatus());
       // System.out.println("Message : "+response.getMessage());
        
=======

>>>>>>> 69b0521674d80a660b787557cc78e72f074bbb6c
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

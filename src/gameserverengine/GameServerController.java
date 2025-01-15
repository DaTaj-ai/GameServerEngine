package gameserverengine;

import gameserverengine.network.NetworkAccessLayer;
import gameserverengine.utils.Consts;
import gameserverengine.utils.NetworkHelper;
import gameserverengine.utils.SharedModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class GameServerController extends GameServerPage {
    
    public GameServerController(Stage stage) {
        super(stage);
        initView();
    }
    
    private void initView(){
        String serverIp = NetworkHelper.getWiFiIPAddress()!=null?
                NetworkHelper.getWiFiIPAddress() :Consts.LOCAL_IP ;
        
        ipAdress_label_type.setText(serverIp);
        PortTypeLabel.setText(""+Consts.PORT);
        onClicks();
    }
    private void onClicks(){
        
        button.setOnAction((ActionEvent event) -> {
            if(SharedModel.isRunning()){
                stopServer();
            }
            else{
                startServer();
            }
        });
    }
    
    private void startServer(){
        SharedModel.setRunning(true);
        button.setText("Stop");
        button.setStyle("-fx-background-color:red ;");
        new Thread(() -> NetworkAccessLayer.startListen()).start();
    }
    private void stopServer(){
        SharedModel.setRunning(false);
        button.setText("Start");
        button.setStyle("-fx-background-color:green ;");
        NetworkAccessLayer.stop();
    }
    
  
    
}

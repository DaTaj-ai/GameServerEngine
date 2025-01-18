package gameserverengine;

import gameserverengine.local.DataAccessLayer;
import gameserverengine.network.NetworkAccessLayer;
import gameserverengine.utils.Consts;
import gameserverengine.utils.NetworkHelper;
import gameserverengine.utils.SharedModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

public class GameServerController extends GameServerPage {
    
    private static XYChart.Series<String, Float> series;
    
    public GameServerController(Stage stage) {
        initView();
    }
    
    private void initView() {
        initGraph();
        String serverIp = NetworkHelper.getWiFiIPAddress() != null
                ? NetworkHelper.getWiFiIPAddress() : Consts.LOCAL_IP;
        
        ipAdress_label_type.setText(serverIp);
        PortTypeLabel.setText("" + Consts.PORT);
        onClicks();
    }
    
    private void onClicks() {
        
        button.setOnAction((ActionEvent event) -> {
            if (SharedModel.isRunning()) {
                stopServer();
            } else {
                startServer();
            }
        });
    }
    
    private void startServer() {
        //updateGraph(10, 3, 7);
        setgraphstate();
        SharedModel.setRunning(true);
        button.setText("Stop");
        button.setStyle("-fx-background-color:red ;");
        new Thread(() -> NetworkAccessLayer.startListen()).start();
        
    }
    
    private void stopServer() {
        SharedModel.setRunning(false);
        button.setText("Start");
        button.setStyle("-fx-background-color:green ;");
        NetworkAccessLayer.stop();
        //updateGraph(0, 0, 0);
        updateOnlineGraph(0);
        updateOffLineGraph(0);
        updateAvailableGraph(0);
    }
    
    public void initGraph() {
        
        int onlineCount = 0;
        int offlineCount = 0;
        int availableCount = 0;
        
        series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data("Offline", onlineCount));
        series.getData().add(new XYChart.Data("Online", offlineCount));
        series.getData().add(new XYChart.Data("Available", availableCount));
        series.setName("online");
        barChart.getData().add(series);
    }
    
    public static void setgraphstate() {
        int online = DataAccessLayer.getOnlinePlayerCount();
        int offline = DataAccessLayer.getOfflinePlayerCount();
        int avalible = DataAccessLayer.getAvailblePlayerCount();
        updateOnlineGraph(online);
        updateOffLineGraph(offline);
        updateAvailableGraph(avalible);
        //updateGraph(online, offline, avalible);
    }

//    public static void updateGraph(int onlineCount, int offlineCount, int availableCount) {
//
//        series = (XYChart.Series<String, Float>) barChart.getData().get(0);
//        for (XYChart.Data<String, Float> data : series.getData()) {
//            switch (data.getXValue()) {
//                case "Offline":
//                    data.setYValue((float) offlineCount);
//                    break;
//                case "Online":
//                    data.setYValue((float) onlineCount);
//                    break;
//                case "Available":
//                    data.setYValue((float) availableCount);
//                    break;
//            }
//        }
//
//    }
    public static void updateOnlineGraph(int onlineCount) {
        
        series = (XYChart.Series<String, Float>) barChart.getData().get(0);
        for (XYChart.Data<String, Float> data : series.getData()) {
            if (data.getXValue().equalsIgnoreCase("Online")) {
                data.setYValue((float) onlineCount);
            }
        }
        
    }
    
    public static void updateOffLineGraph(int offlineCount) {
        
        series = (XYChart.Series<String, Float>) barChart.getData().get(0);
        for (XYChart.Data<String, Float> data : series.getData()) {
            if (data.getXValue().equalsIgnoreCase("Offline")) {
                data.setYValue((float) offlineCount);
            }
            
        }
        
    }
    
    public static void updateAvailableGraph(int availableCount) {
        
        series = (XYChart.Series<String, Float>) barChart.getData().get(0);
        for (XYChart.Data<String, Float> data : series.getData()) {
            if (data.getXValue().equalsIgnoreCase("Available")) {
                data.setYValue((float) availableCount);
            }
            
        }
        
    }
    
}

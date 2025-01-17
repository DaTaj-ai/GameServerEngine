package gameserverengine;

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
        updateGraph(10,3,7);
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
        updateGraph(0, 0, 0);
    }

    public void initGraph() {

        int onlineCount = 0;
        int offlineCount = 0;
        int availableCount = 0;

        XYChart.Series<String, Float> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data("Offline", onlineCount));
        series.getData().add(new XYChart.Data("Online", offlineCount));
        series.getData().add(new XYChart.Data("Available", availableCount));
        series.setName("online");
        barChart.getData().add(series);
    }

    public void updateGraph(int onlineCount, int offlineCount, int availableCount) {
        if (!barChart.getData().isEmpty()) {
            XYChart.Series<String, Float> series = (XYChart.Series<String, Float>) barChart.getData().get(0);
            for (XYChart.Data<String, Float> data : series.getData()) {
                switch (data.getXValue()) {
                    case "Offline":
                        data.setYValue((float) offlineCount);
                        break;
                    case "Online":
                        data.setYValue((float) onlineCount);
                        break;
                    case "Available":
                        data.setYValue((float) availableCount);
                        break;
                }
            }
        }
    }

}

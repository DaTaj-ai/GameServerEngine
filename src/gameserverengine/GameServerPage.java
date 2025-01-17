package gameserverengine;

import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public abstract class GameServerPage extends AnchorPane {

    protected final ImageView imageView;
    protected final Rectangle rectangle;
    protected final Label label;
    protected final Label ipAdress_label_type;
    protected final Button button;
    protected final Label PortTypeLabel;
    protected final Label label0;
    protected final CategoryAxis categoryAxis;
    protected final NumberAxis numberAxis;
    protected final BarChart barChart;

    public GameServerPage() {

        imageView = new ImageView();
        rectangle = new Rectangle();
        label = new Label();
        ipAdress_label_type = new Label();
        button = new Button();
        PortTypeLabel = new Label();
        label0 = new Label();
        categoryAxis = new CategoryAxis();
        numberAxis = new NumberAxis();
        barChart = new BarChart(categoryAxis, numberAxis);
        
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(700.0);
        setPrefWidth(1000.0);
         
        imageView.setFitHeight(700.0);
        imageView.setFitWidth(1000.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("drawable/images/home_background.png").toExternalForm()));

        AnchorPane.setBottomAnchor(rectangle, 125.0);
        AnchorPane.setLeftAnchor(rectangle, 49.0);
        AnchorPane.setRightAnchor(rectangle, 48.0);
        AnchorPane.setTopAnchor(rectangle, 126.0);
        rectangle.setArcHeight(8.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.valueOf("#436399"));
        rectangle.setHeight(449.0);
        rectangle.setLayoutX(49.0);
        rectangle.setLayoutY(126.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setStrokeWidth(0.0);
        rectangle.setStyle("-fx-background-radius: 80;");
        rectangle.setWidth(903.0);

        AnchorPane.setBottomAnchor(label, 394.0);
        AnchorPane.setLeftAnchor(label, 668.0);
        AnchorPane.setRightAnchor(label, 154.0);
        AnchorPane.setTopAnchor(label, 255.0);
        label.setLayoutX(668.0);
        label.setLayoutY(255.0);
        label.setPrefHeight(51.0);
        label.setPrefWidth(178.0);
        label.setText("Ip Adress");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Old English Text MT", 34.0));

        AnchorPane.setBottomAnchor(ipAdress_label_type, 343.0);
        AnchorPane.setLeftAnchor(ipAdress_label_type, 643.0);
        AnchorPane.setRightAnchor(ipAdress_label_type, 93.0);
        AnchorPane.setTopAnchor(ipAdress_label_type, 306.0);
        ipAdress_label_type.setLayoutX(643.0);
        ipAdress_label_type.setLayoutY(306.0);
        ipAdress_label_type.setPrefHeight(51.0);
        ipAdress_label_type.setPrefWidth(264.0);
        ipAdress_label_type.setText("192.168.155.124");
        ipAdress_label_type.setTextFill(javafx.scene.paint.Color.WHITE);
        ipAdress_label_type.setFont(new Font("Old English Text MT", 34.0));

        AnchorPane.setBottomAnchor(button, 31.0);
        AnchorPane.setLeftAnchor(button, 403.0);
        AnchorPane.setRightAnchor(button, 398.0);
        AnchorPane.setTopAnchor(button, 601.0);
        button.setLayoutX(403.0);
        button.setLayoutY(601.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(59.0);
        button.setPrefWidth(199.0);
        button.setStyle("-fx-background-radius: 10px; -fx-background-color: green;");
        button.setText("Start");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font(31.0));

        PortTypeLabel.setLayoutX(761.0);
        PortTypeLabel.setLayoutY(376.0);
        PortTypeLabel.setPrefHeight(40.0);
        PortTypeLabel.setPrefWidth(105.0);
        PortTypeLabel.setStyle("-fx-background-radius: 10px;");
        PortTypeLabel.setText("192");
        PortTypeLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        PortTypeLabel.setOpaqueInsets(new Insets(0.0));
        PortTypeLabel.setFont(new Font("Old English Text MT", 34.0));

        label0.setLayoutX(661.0);
        label0.setLayoutY(372.0);
        label0.setPrefHeight(40.0);
        label0.setPrefWidth(105.0);
        label0.setStyle("-fx-background-radius: 10px;");
        label0.setText("Port");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setOpaqueInsets(new Insets(0.0));
        label0.setFont(new Font("Old English Text MT", 34.0));

        categoryAxis.setSide(javafx.geometry.Side.BOTTOM);

        numberAxis.setSide(javafx.geometry.Side.LEFT);
        barChart.setLayoutX(67.0);
        barChart.setLayoutY(159.0);

        getChildren().add(imageView);
        getChildren().add(rectangle);
        getChildren().add(label);
        getChildren().add(ipAdress_label_type);
        getChildren().add(button);
        getChildren().add(PortTypeLabel);
        getChildren().add(label0);
        barChart.getStylesheets().add(getClass().getResource("/gameserverengine/drawable/style/GameServerPage.css").toExternalForm());        
        getChildren().add(barChart);

    }
}

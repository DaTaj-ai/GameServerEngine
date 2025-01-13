package gameserverengine;

import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FXMLDocumentBase extends AnchorPane {

    protected final ImageView imageView;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final RowConstraints rowConstraints2;
    protected final Label label;
    protected final Label label0;
    protected final HBox hBox;
    protected final Button button;
    protected final CategoryAxis categoryAxis;
    protected final NumberAxis numberAxis;
    protected final StackedBarChart stackedBarChart;
    protected final Label ipAdress_label_type;
    protected final Label PortTypeLabel;
    protected final Label label1;

    public FXMLDocumentBase(Stage stage) {

        imageView = new ImageView();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        label = new Label();
        label0 = new Label();
        hBox = new HBox();
        button = new Button();
        categoryAxis = new CategoryAxis();
        numberAxis = new NumberAxis();
        stackedBarChart = new StackedBarChart(categoryAxis, numberAxis);
        ipAdress_label_type = new Label();
        PortTypeLabel = new Label();
        label1 = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(600.0);
        setPrefWidth(800.0);
        stage.setResizable(false);

        imageView.setFitHeight(600.0);
        imageView.setFitWidth(800.0);
        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("drawable/images/background.jpg").toExternalForm()));

        gridPane.setPrefHeight(600.0);
        gridPane.setPrefWidth(800.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(300.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(300.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(379.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(219.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(281.0);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(281.0);

        rowConstraints.setMaxHeight(152.0);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(152.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(220.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(155.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(240.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(157.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMaxHeight(136.0);
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(136.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setRowIndex(label, 1);
        label.setPrefHeight(40.0);
        label.setPrefWidth(151.0);
        label.setText("Ip Adress");
        GridPane.setMargin(label, new Insets(25.0, 0.0, 0.0, 140.0));
        label.setFont(new Font("Old English Text MT", 34.0));

        GridPane.setRowIndex(label0, 2);
        label0.setPrefHeight(40.0);
        label0.setPrefWidth(105.0);
        label0.setStyle("-fx-background-radius: 10px;");
        label0.setText("Port");
        label0.setOpaqueInsets(new Insets(0.0));
        GridPane.setMargin(label0, new Insets(0.0, 0.0, 50.0, 150.0));
        label0.setFont(new Font("Old English Text MT", 34.0));

        GridPane.setColumnIndex(hBox, 1);
        GridPane.setRowIndex(hBox, 3);
        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(200.0);

        button.setMnemonicParsing(false);
        button.setPrefHeight(51.0);
        button.setPrefWidth(195.0);
        button.setStyle("-fx-background-radius: 10px; -fx-background-color: green;");
        button.setText("Start");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        HBox.setMargin(button, new Insets(15.0, 0.0, 0.0, 0.0));

        categoryAxis.setSide(javafx.geometry.Side.BOTTOM);

        numberAxis.setSide(javafx.geometry.Side.LEFT);
        stackedBarChart.setPadding(new Insets(20.0, 50.0, 5.0, 50.0));
        GridPane.setMargin(stackedBarChart, new Insets(130.0, 100.0, 0.0, 0.0));
        GridPane.setColumnIndex(stackedBarChart, 2);
        GridPane.setRowIndex(stackedBarChart, 1);
        stackedBarChart.setMinHeight(USE_PREF_SIZE);
        stackedBarChart.setMinWidth(USE_PREF_SIZE);
        stackedBarChart.setPrefHeight(246.0);
        stackedBarChart.setPrefWidth(279.0);
        stackedBarChart.setStyle("-fx-background-radius: 10px;");

        GridPane.setColumnIndex(ipAdress_label_type, 1);
        GridPane.setRowIndex(ipAdress_label_type, 1);
        ipAdress_label_type.setPrefHeight(40.0);
        ipAdress_label_type.setPrefWidth(169.0);
        ipAdress_label_type.setText("192.1.1..55");
        ipAdress_label_type.setFont(new Font("Old English Text MT", 34.0));
        GridPane.setMargin(ipAdress_label_type, new Insets(25.0, 0.0, 0.0, 20.0));

        GridPane.setColumnIndex(PortTypeLabel, 1);
        GridPane.setRowIndex(PortTypeLabel, 2);
        PortTypeLabel.setPrefHeight(40.0);
        PortTypeLabel.setPrefWidth(105.0);
        PortTypeLabel.setStyle("-fx-background-radius: 10px;");
        PortTypeLabel.setText("192");
        PortTypeLabel.setOpaqueInsets(new Insets(0.0));
        PortTypeLabel.setFont(new Font("Old English Text MT", 34.0));
        GridPane.setMargin(PortTypeLabel, new Insets(0.0, 0.0, 50.0, 20.0));
        gridPane.setOpaqueInsets(new Insets(0.0));

        label1.setLayoutX(10.0);
        label1.setLayoutY(10.0);
        label1.setText("Ip");
        label1.setFont(new Font("Old English Text MT", 34.0));

        getChildren().add(imageView);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(label0);
        hBox.getChildren().add(button);
        gridPane.getChildren().add(hBox);
        gridPane.getChildren().add(stackedBarChart);
        gridPane.getChildren().add(ipAdress_label_type);
        gridPane.getChildren().add(PortTypeLabel);
        getChildren().add(gridPane);
        getChildren().add(label1);

    }
}

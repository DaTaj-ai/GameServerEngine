package gameserverengine;

import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
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
    protected final TextField textField;
    protected final TextField textField0;
    protected final Label label;
    protected final Label label0;
    protected final HBox hBox;
    protected final Button button;
    protected final Button button0;
    protected final CategoryAxis categoryAxis;
    protected final NumberAxis numberAxis;
    protected final StackedBarChart stackedBarChart;
    private Stage mystage;
    protected final VBox vBox;

    public FXMLDocumentBase(Stage stage) {
        this.mystage = stage;
        imageView = new ImageView();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        rowConstraints2 = new RowConstraints();
        textField = new TextField();
        textField0 = new TextField();
        label = new Label();
        label0 = new Label();
        hBox = new HBox();
        button = new Button();
        button0 = new Button();
        categoryAxis = new CategoryAxis();
        numberAxis = new NumberAxis();
        stackedBarChart = new StackedBarChart(categoryAxis, numberAxis);
        vBox = new VBox();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        imageView.setPickOnBounds(true);
        imageView.setImage(new Image(getClass().getResource("/gameserverengine/drawable/images/background.jpg").toExternalForm()));
        imageView.fitWidthProperty().bind(mystage.widthProperty());
        imageView.fitHeightProperty().bind(mystage.heightProperty());

        gridPane.setPrefHeight(400.0);
        gridPane.setPrefWidth(600.0);

        vBox.setPrefHeight(400.0);
        vBox.setPrefWidth(600.0);
        //vBox.translateYProperty().bind(mystage.heightProperty().multiply(0.2));
       // vBox.translateXProperty().bind(mystage.widthProperty().multiply(0.15));
        
        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(294.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(212.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(379.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(195.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(252.0);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(193.0);

        rowConstraints.setMaxHeight(96.0);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(96.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(152.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(96.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(151.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(118.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints2.setMaxHeight(90.0);
        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(90.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setColumnIndex(textField, 1);
        GridPane.setRowIndex(textField, 1);
        textField.setStyle("-fx-background-radius: 10px; -fx-background-color: black;");
        GridPane.setMargin(textField, new Insets(50.0, 40.0, 0.0, 0.0));
        textField.prefWidthProperty().bind(mystage.widthProperty().multiply(0.24));
        GridPane.setColumnIndex(textField0, 1);
        GridPane.setRowIndex(textField0, 2);
        textField0.setStyle("-fx-background-radius: 10px; -fx-background-color: black;");
        GridPane.setMargin(textField0, new Insets(50.0, 40.0, 25.0, 0.0));

        GridPane.setRowIndex(label, 1);
        label.setText("Ip");
        GridPane.setMargin(label, new Insets(50.0, 0.0, 0.0, 130.0));
        label.setFont(new Font("Old English Text MT", 34.0));

        GridPane.setRowIndex(label0, 2);
        label0.setPrefHeight(40.0);
        label0.setPrefWidth(105.0);
        label0.setStyle("-fx-background-radius: 10px;");
        label0.setText("Adress");
        label0.setOpaqueInsets(new Insets(0.0));
        GridPane.setMargin(label0, new Insets(50.0, 0.0, 25.0, 110.0));
        label0.setFont(new Font("Old English Text MT", 34.0));

        GridPane.setColumnIndex(hBox, 1);
        GridPane.setColumnSpan(hBox, 2);
        GridPane.setRowIndex(hBox, 3);
        hBox.setPrefHeight(100.0);
        hBox.setPrefWidth(200.0);

        button.setMnemonicParsing(false);
        button.setPrefHeight(31.0);
        button.setPrefWidth(75.0);
        button.setStyle("-fx-background-radius: 10px; -fx-background-color: green;");
        button.setText("Start");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        HBox.setMargin(button, new Insets(50.0, 0.0, 0.0, 0.0));

        button0.setMnemonicParsing(false);
        button0.setPrefHeight(31.0);
        button0.setPrefWidth(75.0);
        button0.setStyle("-fx-background-radius: 10px; -fx-background-color: red;");
        button0.setText("Stop");
        button0.setTextFill(javafx.scene.paint.Color.WHITE);
        HBox.setMargin(button0, new Insets(50.0, 0.0, 0.0, 150.0));

        categoryAxis.setSide(javafx.geometry.Side.BOTTOM);

        numberAxis.setSide(javafx.geometry.Side.LEFT);
        stackedBarChart.setPadding(new Insets(20.0, 50.0, 5.0, 30.0));
        GridPane.setMargin(stackedBarChart, new Insets(150.0, 100.0, 0.0, 10.0));
        GridPane.setColumnIndex(stackedBarChart, 2);
        GridPane.setRowIndex(stackedBarChart, 1);
        stackedBarChart.setStyle("-fx-background-radius: 10px;");
        gridPane.setOpaqueInsets(new Insets(0.0));

        getChildren().add(imageView);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getRowConstraints().add(rowConstraints2);
        gridPane.getChildren().add(textField);
        gridPane.getChildren().add(textField0);
        gridPane.getChildren().add(label);
        gridPane.getChildren().add(label0);
        hBox.getChildren().add(button);
        hBox.getChildren().add(button0);
        gridPane.getChildren().add(hBox);
        gridPane.getChildren().add(stackedBarChart);
        getChildren().add(gridPane);
        vBox.getChildren().add(gridPane);
        getChildren().add(vBox);

    }

    FXMLDocumentBase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

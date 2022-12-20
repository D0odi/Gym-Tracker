package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@SuppressWarnings("rawtypes")
public class CR_Stats implements Initializable {
    Data data = Data.getInstance();
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    private final Exercise selected = data.getTemp();
    @FXML
    private LineChart<?,?> progressChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(selected.getName());
        XYChart.Series series = new XYChart.Series();
        ArrayList<ArrayList<Double>> volumes = selected.getVolumes();
        for (int i = 0; i < volumes.size(); i++) {
            String xA = Integer.toString(i+1);
            double volume = volumes.get(i).get(0) * volumes.get(i).get(1);
            series.getData().add(new XYChart.Data(xA, volume));
        }
        progressChart.getData().addAll(series);
    }

    public void back(ActionEvent e) throws IOException {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("CR_ExDataCheck.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

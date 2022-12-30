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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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
    @FXML
    private Text avgResults = new Text("");
    @FXML
    private Text firstResults = new Text("");
    @FXML
    private Text mostResent = new Text("");
    @FXML
    private Text topResult = new Text("");
    @FXML
    private Text scene_name = new Text("");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            double weightsInfo = 0;
            double repsInfo = 0;
            DecimalFormat df = new DecimalFormat("#.0");
            progressChart.setTitle(null);
            scene_name.setText(selected.getName() + " Progression");
            XYChart.Series weight = new XYChart.Series();
            weight.setName("Weight");
            XYChart.Series reps = new XYChart.Series();
            reps.setName("Reps");
            ArrayList<ArrayList<Double>> volumes = selected.getVolumes();
            for (int i = 0; i < volumes.size(); i++) {
                String xA = Integer.toString(i + 1);
                double weights = volumes.get(i).get(0);
                double rep = volumes.get(i).get(1);
                weightsInfo += weights;
                repsInfo += rep;
                weight.getData().add(new XYChart.Data(xA, weights));
                reps.getData().add(new XYChart.Data(xA, rep));
            }
            weightsInfo /= volumes.size();
            repsInfo /= volumes.size();
            mostResent.setText(df.format(selected.getVolumes().get(selected.getVolLength() - 1).get(0)) + " / " + df.format(selected.getVolumes().get(selected.getVolLength() - 1).get(1)));
            firstResults.setText(df.format(selected.getVolumes().get(0).get(0)) + " / " + df.format(selected.getVolumes().get(0).get(1)));
            avgResults.setText(df.format(weightsInfo) + " / " + df.format(repsInfo));
            progressChart.getData().addAll(weight);
            progressChart.getData().addAll(reps);

            topResult.setText(data.getBestResult(selected).get(0) + " / " + data.getBestResult(selected).get(1));

        }
        catch (Exception ex) {
            try {
                data.loadError();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void back(ActionEvent e) throws IOException {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ExDataCheck.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("exCheckScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

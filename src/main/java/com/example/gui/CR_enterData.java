package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CR_enterData implements Initializable {

    Data data = Data.getInstance();
    @FXML
    private Stage stage ;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private TextField weight;
    @FXML
    private TextField rep;
    @FXML
    private Text exName;
    @FXML
    private Exercise savedTemp = data.getTemp();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exName.setText(savedTemp.getName());
    }
    public void OK(ActionEvent e) throws IOException {
        ArrayList<Double> day = new ArrayList<>();
        Double reps = Double.parseDouble(rep.getText());
        Double weights = Double.parseDouble(weight.getText());
        day.add(weights);
        day.add(reps);

        savedTemp.addVolume(weights, reps);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

}

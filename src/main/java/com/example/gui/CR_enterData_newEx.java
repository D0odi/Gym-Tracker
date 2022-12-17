package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CR_enterData_newEx {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private TextField input;
    @FXML
    private TextField weight;
    @FXML
    private TextField rep;
    Data data = Data.getInstance();
    public void OK(ActionEvent e) throws IOException {
        ArrayList<ArrayList<Double>> volumes = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> day = new ArrayList<>();
        Double reps = Double.parseDouble(rep.getText());
        Double weights = Double.parseDouble(weight.getText());
        day.add(weights);
        day.add(reps);
        volumes.add(day);
        String name = input.getText();

        data.addEx(name, volumes);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CR_enterData_newEx {

    Data data = Data.getInstance();
    @FXML
    private Stage stage;
    @FXML
    private TextField input;
    @FXML
    private TextField weight;
    @FXML
    private TextField rep;
    public void OK(ActionEvent e) throws IOException {
        ArrayList<ArrayList<Double>> volumes = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> day = new ArrayList<>();
        String nameInput = input.getText();
        try {
            Pattern checkName = Pattern.compile("[a-zA-Z0-9]+\\s?([a-zA-Z0-9]+)?\\s?([a-zA-Z0-9]+)?\\s?([a-zA-Z0-9]+)?\\s?([a-zA-Z0-9]+)?");
            Matcher check = checkName.matcher(nameInput);
            check.find();
            String name = check.group();
            if (!nameInput.equals(name)) {
                data.loadError();
                return;
            }
        } catch (Exception ex) {
            data.loadError();
            return;
        }
        try {
            if (!rep.getText().equals("") && !weight.getText().equals("")) {
                double reps = Double.parseDouble(rep.getText());
                double weights = Double.parseDouble(weight.getText());
                day.add(weights);
                day.add(reps);
                volumes.add(day);
                data.addEx(nameInput, volumes);
            } else {
                data.addEmptyEx(nameInput);
            }

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.close();
        }
        catch (Exception ex) {
            data.loadError();
        }
    }
}

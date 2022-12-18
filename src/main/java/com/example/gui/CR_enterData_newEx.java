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
    private Scene scene;
    @FXML
    private Parent root;
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
                loadError();
                return;
            }
        } catch (Exception ex) {
            loadError();
            return;
        }
        Double reps = Double.parseDouble(rep.getText());
        Double weights = Double.parseDouble(weight.getText());
        day.add(weights);
        day.add(reps);
        volumes.add(day);

        data.addEx(nameInput, volumes);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadError() throws IOException {
        Stage error = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ErrorMsgInvalidName.fxml"));
        Scene errorName = new Scene(root);
        error.setScene(errorName);
        error.show();
    }
}

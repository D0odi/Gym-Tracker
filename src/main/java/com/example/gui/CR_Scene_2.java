package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CR_Scene_2 implements Initializable {
    @FXML
    private Text text;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    Data data = Data.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        text.setText("Current file: " + data.getFileName());
    }
    public void WriteDown(ActionEvent e) {

    }
    public void CheckExercise(ActionEvent e) {

    }
    public void Quit(ActionEvent e) {

    }
}

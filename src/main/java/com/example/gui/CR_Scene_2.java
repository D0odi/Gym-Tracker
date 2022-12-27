package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CR_Scene_2 implements Initializable {
    @FXML
    private Text fileNameScene2;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    Data data = Data.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileNameScene2.setText("Current file: " + data.getFileName());
    }
    public void CheckExercise(ActionEvent e) throws IOException{
        stage = (Stage)((Node)e.getSource()).getScene().getWindow(); // not to create multiple stages
        root = FXMLLoader.load(getClass().getResource("ExDataCheck.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("exCheckScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void WriteDown(ActionEvent e) throws IOException {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow(); // not to create multiple stages
        root = FXMLLoader.load(getClass().getResource("StoreData.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void Save(ActionEvent e) throws IOException {
        data.reWrite();
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}

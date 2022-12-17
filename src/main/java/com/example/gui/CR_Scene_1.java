package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CR_Scene_1 {
    @FXML
    private File file;

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    Data data = Data.getInstance();

    @FXML
    public void toScene2(ActionEvent e) throws IOException {

        stage = (Stage)((Node)e.getSource()).getScene().getWindow(); // not to create multiple stages

        FileChooser.ExtensionFilter txt = new FileChooser.ExtensionFilter("Text Files", "*.txt");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(txt);
        data.setFile(fc.showOpenDialog(stage));


        root = FXMLLoader.load(getClass().getResource("Scene_2.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        data.read_data();
    }
}
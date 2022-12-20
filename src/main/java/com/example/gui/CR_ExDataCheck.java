package com.example.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CR_ExDataCheck implements Initializable {
    Data data = Data.getInstance();
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private ListView<String> list;

    @FXML
    private Text text;
    @FXML
    private Button see;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        see.setVisible(false);
        list.getItems().addAll(data.listEx());
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String choice = list.getSelectionModel().getSelectedItem();
                data.setTempEx(data.getExercise(choice));
                text.setText(data.getTemp().printInfo());
                see.setVisible(true);
            }
        });
    }
    public void seeMore(ActionEvent e) throws IOException {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Stats.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void back(ActionEvent e) throws IOException {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Scene_2.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

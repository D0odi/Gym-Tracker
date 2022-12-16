package com.example.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CR_ExDataCheck implements Initializable {
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

    private Scanner scnr = new Scanner(System.in);

    Data data = Data.getInstance();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.getItems().addAll(data.listEx());

        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String choice = list.getSelectionModel().getSelectedItem();
                Exercise selected = data.getExercise(choice);
                text.setText(selected.printInfo());
            }
        });
    }


}

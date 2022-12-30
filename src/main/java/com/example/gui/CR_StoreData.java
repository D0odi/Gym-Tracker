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
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.spi.InetAddressResolver;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CR_StoreData implements Initializable {
    Data data = Data.getInstance();

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list.getItems().addAll(data.listEx());
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String choice = list.getSelectionModel().getSelectedItem();
                data.setTempEx(data.getExercise(choice));


                Parent root1;
                try {
                    root1 = FXMLLoader.load(getClass().getResource("enterData.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage enterVolumes = new Stage();
                Scene scene1 = new Scene(root1);
                scene1.getStylesheets().add(getClass().getResource("write.css").toExternalForm());
                enterVolumes.setScene(scene1);
                enterVolumes.setResizable(false);
                enterVolumes.show();

            }
        });
    }
    public void newOne(ActionEvent e) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("enterData(NewEx).fxml"));
        Stage enterVolumes = new Stage();
        Scene scene1 = new Scene(root1);
        scene1.getStylesheets().add(getClass().getResource("write.css").toExternalForm());
        enterVolumes.setScene(scene1);
        enterVolumes.setResizable(false);
        enterVolumes.show();
    }
    public void back(ActionEvent e) throws IOException{
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Scene_2.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

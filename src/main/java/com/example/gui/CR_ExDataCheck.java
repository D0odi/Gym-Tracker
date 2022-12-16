package com.example.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private Label label;

    private Scanner scnr = new Scanner(System.in);

    Data data = Data.getInstance();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(data.listEx());
        data.getExInfo(scnr.nextInt());

  /*      if (scnr.next().charAt(0) == 'y') {
            choose_exercise();
        }
   */
    }

}

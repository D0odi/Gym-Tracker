package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CR_ErrorMsgInvalidName {

    Data data = Data.getInstance();
    @FXML
    private Stage stage;
    public void close(ActionEvent e) {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}

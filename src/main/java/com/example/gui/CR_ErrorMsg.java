package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CR_ErrorMsg {

    Data data = Data.getInstance();
    @FXML
    private Stage stage;
    public void close(ActionEvent e) {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}

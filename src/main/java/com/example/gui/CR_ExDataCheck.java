package com.example.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private TableView<Day> resultsList = new TableView<>();;
    @FXML
    private TableColumn<Day, Double> weightResult;
    @FXML
    private TableColumn<Day, Double> repsResult;
    @FXML
    private Button see;
    @FXML
    ObservableList<Day> days = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        see.setVisible(false);
        list.getItems().addAll(data.listEx());
        weightResult.setCellValueFactory(new PropertyValueFactory<>("weights"));
        repsResult.setCellValueFactory(new PropertyValueFactory<>("reps"));

        resultsList.setPlaceholder(new Label("Choose exercise \n     to see data"));
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                resultsList.getItems().clear();
                String choice = list.getSelectionModel().getSelectedItem();
                data.setTempEx(data.getExercise(choice));
                for (int i = data.getTemp().getVolLength() - 1; i >= 0; i--) {
                    days.add(new Day(data.getTemp().getVolumes().get(i).get(0), data.getTemp().getVolumes().get(i).get(1)));
                }
                resultsList.setItems(days);
                see.setVisible(true);
            }
        });
    }
    public void seeMore(ActionEvent e) throws IOException {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Stats.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("seeMore.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void back(ActionEvent e) throws IOException {
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Scene_2.fxml"));
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


}

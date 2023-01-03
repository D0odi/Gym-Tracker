package com.example.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Exercise {



    // Constructors
    public Exercise(String name, ArrayList<ArrayList<Double>> volume) {
        this.volumes = volume;
        this.name = name;
    }
    public Exercise(String name) {
        this.volumes = null;
        this.name = name;
    }



    // Variables
    private ArrayList<ArrayList<Double>> volumes;
    private String name;



    // Getters
    public ArrayList<ArrayList<Double>> getVolumes() {
        return volumes;
    }
    public ArrayList<Double> getSet(int place) {
        return volumes.get(place);
    }
    public String getName() {
        return name;
    }
    public int getVolLength() {
        return volumes.size();
    }



    // Methods
    public void deleteVolume(int num) {
        volumes.remove(num);
    }
    public void addVolume(double weight, double reps) {
      ArrayList<Double> newDay = new ArrayList<>();
      newDay.add(weight);
      newDay.add(reps);
      volumes.add(newDay);
    }
}

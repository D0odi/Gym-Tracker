package com.example.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Exercise {
    private ArrayList<ArrayList<Double>> volumes;
    private String name;

    public Exercise(String name, ArrayList<ArrayList<Double>> volume) {
        this.volumes = volume;
        this.name = name;
    }
    public Exercise(String name) {
      this.volumes = null;
      this.name = name;
    }

    public ArrayList<ArrayList<Double>> getVolumes() {
        return volumes;
    }

    public void setName(String name) {
      this.name = name;
    }
    public ArrayList<Double> getSet(int place) {
        return volumes.get(place);
    }

    public String getName() {
        return name;
    }
  
    public void addVolume(double weight, double reps) {
      ArrayList<Double> newDay = new ArrayList<>();
      newDay.add(weight);
      newDay.add(reps);
      volumes.add(newDay);
    }
  
    public void deleteVolume(int spot) {
      volumes.remove(spot);
    }

    public int getVolLength() {
      return volumes.size();
    }
    public Double weightsInfo(int day) {
        return volumes.get(day).get(0);
    }
    public Double repsInfo(int day) {
        return volumes.get(day).get(0);
    }
}

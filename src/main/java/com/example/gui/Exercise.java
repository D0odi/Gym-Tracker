package com.example.gui;
/* Exercise Class
  @author: Lev Natekin
  @author: Kevin Zhang

  the Exercise class controls the data of each indivdual exercise

  VARIABLES:
  private ArrayList<ArrayList<Double>> volumes;
  private String name;

  METHODS:
  public Exercise(String name, ArrayList<ArrayList<Double>> volume);
  public Exercise(String name);
  public void setName(String name);
  public ArrayList<Double> getSet(int place);
  public String getName();
  public void addVolume(double weight, double reps);
  public void deleteVolume(int spot);
  public int getVolLength();
  public void printInfo();
*/

import java.util.ArrayList;

public class Exercise {
    private ArrayList<ArrayList<Double>> volumes;
    private String name;

    public Exercise(String name, ArrayList<ArrayList<Double>> volume) {
        this.volumes = volume;
        this.name = name;
    }
    public Exercise(String name) {
      ArrayList<ArrayList<Double>> filler = new ArrayList<>();
      volumes = filler;
      this.name = name;
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
  
/*  public String getMaxVolume() {
      
    }
  }
*/
    public String printInfo() {
        StringBuilder info = new StringBuilder();
        int border = 0;
        for (int i = volumes.size() - 1; i >= 0; i--) {
            String temp = volumes.get(i).get(0) + "   /   " + volumes.get(i).get(1) +"\n";
          info.append(temp);
          border++;
          if(border==13){
              break;
          }
        }
        return info.toString();
    }
}

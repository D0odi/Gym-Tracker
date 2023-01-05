package com.example.gui;

/*
    Helper class to initialize list in ExDataCheck
 */

public class Day {
    public int dayNumber;
    public double weights;
    public double reps;
    public Day(int number, double weights, double reps) {
        this.dayNumber = number;
        this.reps = reps;
        this.weights = weights;
    }
    public int getDayNumber() {
        return dayNumber;
    }
    public double getReps() {
        return reps;
    }
    public double getWeights() {
        return weights;
    }
    public void setReps(double reps) {
        this.reps = reps;
    }
    public void setWeights(double weights) {
        this.weights = weights;
    }
}

package com.example.gui;

public class Day {
    public double weights;
    public double reps;
    public Day(double weights, double reps) {
        this.reps = reps;
        this.weights = weights;
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

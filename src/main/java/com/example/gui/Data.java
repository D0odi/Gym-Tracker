package com.example.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
    private static final Data data = new Data();
    private Data(){}
    private File file;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    public static Data getInstance() {
        return data;
    }

    public String getFileName() { return file.getName(); }

    public void setFile(File file) {
        this.file = file;
    }

    public void read_data() throws IOException {
        Scanner file_scan = new Scanner(file);

        Pattern ptVolume = Pattern.compile("\\d\\d?\\.?\\d?\\.?\\d?\\.?\\*?\\d?\\d?\\.?\\d?\\.?");
        Pattern exName = Pattern.compile("[a-zA-Z]+");
        Pattern toNums = Pattern.compile("\\d\\d?\\.?\\d?\\.?\\d?");
        Matcher for_file;

        while(file_scan.hasNext()) {
            ArrayList<ArrayList<Double>> volume = new ArrayList<>();
            String line = file_scan.nextLine();

            for_file = exName.matcher(line);
            for_file.find();
            String name = for_file.group();
            for_file = ptVolume.matcher(line);

            while (for_file.find()) {
                String data = for_file.group();
                ArrayList<Double> set = toVolumes(data, toNums);
                volume.add(set);
            }

            Exercise newEx = new Exercise(name, volume);
            exercises.add(newEx);
        }

        file_scan.close();
    }
    public ArrayList<Double> toVolumes(String data, Pattern pt) {
        ArrayList<Double> nums = new ArrayList<>();
        Matcher converter = pt.matcher(data);
        converter.find();
        double weights = Double.parseDouble(converter.group());
        converter.find();
        double reps = Double.parseDouble(converter.group());

        nums.add(weights);
        nums.add(reps);

        return nums;
    }
    public ArrayList<String> listEx() { //returns a string to print out
        ArrayList<String> names = new ArrayList<>();
        for (Exercise n : exercises) {
            names.add(n.getName());
        }
        return names;
    }
    public Exercise getExercise(String name) {
        for (Exercise n : exercises) {
            if (n.getName().equals(name)) {
                return n;
            }
        }
        return null;
    }
    public void getExInfo(int n) { //gets info from exercises
        exercises.get(n).printInfo();
    }
}

package com.example.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
    private Data() {
    }
    private static final Data data = new Data();
    public static Data getInstance() {
        return data;
    }

    private Exercise temp;
    private File file;
    private ArrayList<Exercise> exercises = new ArrayList<>();

    public void setFile(File file) {
        this.file = file;
    }
    public void setTempEx(Exercise choice) {
        this.temp = choice;
    }
    public String getFileName() {
        return file.getName();
    }
    public Exercise getExercise(String name) {
        for (Exercise n : exercises) {
            if (n.getName().equals(name)) {
                return n;
            }
        }
        return null;
    }
    public Exercise getTemp() {
        return temp;
    }

    public void read_data() throws IOException {
        Scanner file_scan = new Scanner(file);

        Pattern ptVolume = Pattern.compile("\\d\\d?\\.?\\d?\\.?\\d?\\.?\\*?\\d?\\d?\\.?\\d?\\.?");
        Pattern exName = Pattern.compile("[a-zA-Z0-9]+\\s?([a-zA-Z0-9]+)?\\s?([a-zA-Z0-9]+)?\\s?([a-zA-Z0-9]+)?\\s?([a-zA-Z0-9]+)?");
        Pattern toNums = Pattern.compile("\\d\\d?\\.?\\d?\\.?\\d?\\.?\\d?");
        Matcher for_file;

        while (file_scan.hasNext()) {
            ArrayList<ArrayList<Double>> volume = new ArrayList<>();
            String line = file_scan.nextLine();

            for_file = exName.matcher(line);
            for_file.find();
            String name = for_file.group();
            line = line.replaceFirst(name, "");
            name = name.substring(0, name.length() - 1);
            for_file = ptVolume.matcher(line);

            while (for_file.find()) {
                String input = for_file.group();
                ArrayList<Double> set = new ArrayList<>();
                try {
                    set = toVolumes(input, toNums);
                } catch (Exception ex) {
                    set.add(0.0);
                }
                volume.add(set);
            }
            Exercise newEx = new Exercise(name, volume);
            exercises.add(newEx);
        }

        file_scan.close();
    } // read and store data from txt file
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
    } // creates array of size 2 with weight and reps

    public ArrayList<String> listEx() {
        ArrayList<String> names = new ArrayList<>();
        for (Exercise n : exercises) {
            names.add(n.getName());
        }
        return names;
    } //returns a list of exercises names

    public void addEmptyEx(String name) {
        Exercise newOne = new Exercise(name);
        exercises.add(newOne);
    }

    public void addEx(String name, ArrayList<ArrayList<Double>> volumes) {
        exercises.add(new Exercise(name, volumes));
    }


    public void reWrite() throws IOException {
        FileWriter fw = new FileWriter(file);
        Locale.setDefault(Locale.US);
        DecimalFormat f = new DecimalFormat("0.#");
        StringBuilder ss = new StringBuilder("");
        for (Exercise ex : exercises) {
            ss.append(ex.getName());
            ss.append(' ');
            ss.append('-');
            ss.append(' ');

            int len = ex.getVolLength();

            for (int i = 0; i < len; i++) {
                ArrayList<Double> tempList = ex.getSet(i);
                ss.append(f.format(tempList.get(0)));
                ss.append('*');
                ss.append(f.format(tempList.get(1)));
                ss.append(';');
                ss.append(' ');
            }

            int ssLen = ss.length();
            ss.delete(ssLen - 2, ssLen);

            ss.append('\n');
        }
        String s = ss.toString();

        for (int i = 0; i < s.length(); i++)
            fw.write(s.charAt(i));

        fw.close();
    }
    public ArrayList<Double> getBestResult(Exercise ex) {
        ArrayList<Double> maxResults = ex.getVolumes().get(0);
        for (ArrayList<Double> day : ex.getVolumes()) {
            double result = day.get(0) * day.get(1);
            if (result > maxResults.get(0) * maxResults.get(1)) {
                maxResults = day;
            }
        }
        return maxResults;
    }


    public void loadError() throws IOException{
        Stage error = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ErrorMsg.fxml"));
        Scene errorName = new Scene(root);
        errorName.getStylesheets().add(getClass().getResource("error.css").toExternalForm());
        error.setScene(errorName);
        error.show();
    }
}

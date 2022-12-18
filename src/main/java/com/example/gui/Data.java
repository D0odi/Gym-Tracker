package com.example.gui;

import javafx.scene.control.ListView;

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
    private Exercise temp;
    private ArrayList<String> list;
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
        Pattern toNums = Pattern.compile("\\d\\d?\\.?\\d?\\.?\\d?\\.?\\d?");
        Matcher for_file;

        while(file_scan.hasNext()) {
            ArrayList<ArrayList<Double>> volume = new ArrayList<>();
            String line = file_scan.nextLine();

            for_file = exName.matcher(line);
            for_file.find();
            String name = for_file.group();
            for_file = ptVolume.matcher(line);

            while (for_file.find()) {
                String input = for_file.group();
                ArrayList<Double> set = toVolumes(input, toNums);
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
    public static void store_data() {
        double w, r;
        String name = null, wTemp = null, rTemp = null;

  /*      data.addEmptyEx("New one?");
        System.out.println(data.listEx());
       int spot = scnr.nextInt();

        boolean newExer = false;
        if (spot == acc.getListSize() - 1) {
            newExer = true;
            System.out.print("Enter name: ");
            name = scnr.next();
        }

        System.out.print("Enter weight: ");
        wTemp = scnr.next();
        System.out.print("Enter reps: ");
        rTemp = scnr.next();

        try {
            w = Double.parseDouble(wTemp);
            r = Double.parseDouble(rTemp);
        }
        catch (NumberFormatException e) {
            System.out.println("Wrong Input Format\n");
            return;
        }

        if (newExer) {
            acc.writeToNewEx(name, w, r);
        }
        else {
            acc.writeToExistingEx(spot, w, r);
            acc.deleteEx(acc.getListSize() - 1);
        }

   */
    }
    public void addEmptyEx(String name) {
        Exercise newOne = new Exercise(name);
        exercises.add(newOne);
    }

    public void addEx(String name, ArrayList<ArrayList<Double>> volumes) {
        exercises.add(new Exercise(name, volumes));
    }
    public void setTempEx(Exercise choice) {
        this.temp = choice;
    }
    public Exercise getTemp() {
        return temp;
    }
    public void reWrite() throws IOException {
        FileWriter fw = new FileWriter(file);
        Locale.setDefault(Locale.US);
        DecimalFormat f = new DecimalFormat("0.#");
        StringBuilder ss = new StringBuilder("");

        for (Exercise ex: exercises) {
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
            ss.delete(ssLen-2, ssLen);

            ss.append('\n');
        }

        ss.delete(ss.length()-1, ss.length());
        String s = ss.toString();

        for (int i = 0; i < s.length(); i++)
            fw.write(s.charAt(i));

        fw.close();
    }
}

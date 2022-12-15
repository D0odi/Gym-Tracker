package com.example.gui;

import java.io.File;

public class Data {
    private static final Data data = new Data();
    private Data(){}
    private File file;
    public static Data getInstance() {
        return data;
    }

    public String getFilePath() {
        return file.getAbsolutePath();
    }
    public String getFileName() { return file.getName(); }

    public void setFile(File file) {
        this.file = file;
    }
}

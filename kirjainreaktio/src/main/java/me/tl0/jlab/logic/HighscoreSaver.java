package me.tl0.jlab.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class HighscoreSaver {

    Writer writer;
    BufferedReader br;
    File file;

    public HighscoreSaver() {
        file = new File("hs.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
            }
        }
    }

    public void saveScore(int score) {
        if (getHScore() < score) {
            System.out.println("SAVE");
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                writer.write(String.valueOf(score));
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    public int getHScore() {
        int hs = 0;
        if (file.exists()) {
            System.out.println("READ");
            try {
                br = new BufferedReader(new FileReader(file));
                hs = Integer.valueOf(br.readLine());
                br.close();
            } catch (Exception ex) {
            }
        }
        return hs;
    }
}

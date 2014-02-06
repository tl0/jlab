package me.tl0.jlab.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Keeps your highscore saved and updated
 *
 * @author Teemu
 */
public class HighscoreSaver {

    Writer writer;
    BufferedReader br;
    File file;

    public HighscoreSaver() {
        file = new File("hs.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Saves given highscore to file
     *
     * @param score
     */
    public void saveScore(int score) {
        if (getHScore() < score) {
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
                writer.write(String.valueOf(score));
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Gets highscore from file and returns it
     *
     * @return highscore
     */
    public int getHScore() {
        int hs = 0;
        if (file.exists()) {
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

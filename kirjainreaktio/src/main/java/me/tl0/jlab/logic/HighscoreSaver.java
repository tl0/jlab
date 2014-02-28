package me.tl0.jlab.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Keeps your highscore saved and updated
 *
 * @author Teemu
 */
public class HighscoreSaver {

    Writer writer;
    BufferedReader br;
    File file;
    private final String seperator = ";";

    /**
     * If file doesn't exist, creates "dummy" -file with all scores on zero
     */
    public HighscoreSaver() {
        file = new File("highscores.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
                String lines[] = new String[3];
                int j = 0;
                for (Mode i : Mode.values()) {
                    lines[j++] = i + seperator + "0";
                }
                saveLines(lines);
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Saves given highscore to file
     *
     * @param mode Gamemode
     * @param score Highscore
     */
    public void saveScore(Mode mode, int score) {
        String[] lines = getLines();
        int j = 0;
        if (getHScore(mode) < score) {
            for (String line : lines) {
                if (line instanceof String) {
                    if (line.split(seperator)[0].equals(mode.toString())) {
                        lines[j] = mode.toString() + seperator + score;
                        break;
                    }
                }
                j++;
            }
            saveLines(lines);
        }
    }

    /**
     * Gets highscore from file and returns it
     *
     * @param mode Gamemode
     * @return highscore
     */
    public int getHScore(Mode mode) {
        for (String i : getLines()) {
            if (i instanceof String) {
                if (i.split(seperator)[0].equals(mode.toString())) {
                    return Integer.valueOf(i.split(seperator)[1]);
                }
            }
        }
        return 0;
    }

    /**
     * Writes lines to file
     *
     * @param lines
     */
    private void saveLines(String lines[]) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            String line = null;
            for (String i : lines) {
                if (i instanceof String) {
                    writer.write(i + System.getProperty("line.separator"));
                }
            }
            writer.close();
        } catch (Exception ex) {
            Logger.getLogger(HighscoreSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read lines from file
     *
     * @return
     */
    private String[] getLines() {
        String lines[] = new String[3];
        int j = 0;
        if (file.exists()) {
            try {
                br = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = br.readLine()) != null) {
                    lines[j++] = line;
                }
                br.close();
            } catch (Exception ex) {
            }
        }
        return lines;
    }
}

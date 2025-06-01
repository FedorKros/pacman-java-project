package common;

import scores.Score;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Tools {

    private static final String SAVE_FILE_PATH = "saves/scores.txt";

    public static void setGlobalFont(Font font) {

    }


    public static void saveScore(Score score) throws IOException {
        ArrayList<Score> scoreList = loadScores();
        scoreList.add(score);
        Collections.sort(scoreList);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_PATH))) {
            out.writeObject(scoreList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Score> loadScores() throws IOException {
        FileInputStream saveFile = new FileInputStream(SAVE_FILE_PATH);
        try (ObjectInputStream out = new ObjectInputStream(saveFile)) {
            return (ArrayList<Score>) out.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<>();
    }

}

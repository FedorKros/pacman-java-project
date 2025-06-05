package common;

import scores.Score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Tools {

    private static final String SAVE_FILE_PATH = "saves/scores.txt";

    public static void saveScore(Score score) throws IOException {
        ArrayList<Score> scoreList = loadScores();
        scoreList.add(score);

        try (ObjectOutputStream in = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_PATH))) {
            in.writeObject(scoreList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Score> loadScores() throws IOException {
        FileInputStream saveFile = new FileInputStream(SAVE_FILE_PATH);
        try (ObjectInputStream out = new ObjectInputStream(saveFile)) {
            ArrayList<Score> loaded = (ArrayList<Score>) out.readObject();
            Collections.sort(loaded);
            return loaded;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

}

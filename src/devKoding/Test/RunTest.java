package devKoding.Test;

import devKoding.Persistence.Conection;
import devKoding.logic.HaldingWords;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cristhian on 24/10/16.
 */
public class RunTest {

    public static void main(String[] args) {

        HaldingWords h = null;
        try {
            h = new HaldingWords();

            ArrayList<String> arrayWords= h.getWords();

            System.out.println("RUNTEST:" + arrayWords.size());


            for (String word: arrayWords) {
                 System.out.println("Palabra: \t"+word);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

}

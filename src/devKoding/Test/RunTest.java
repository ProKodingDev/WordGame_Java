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

            //System.out.println("RUNTEST:" + arrayWords.size());

            System.out.println(h.findWord("perro"));
            System.out.println(h.findWord("perroxsxsxsx"));
            System.out.println(h.findWord("negro"));
            System.out.println(h.findWord("hoja"));
            System.out.println(h.findWord("perró"));


            /*for (String word: arrayWords) {
                 System.out.println("Palabra: \t"+word);
            }*/


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

}

package devKoding.Test;

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

            System.out.println(h.findWord("arre"));
            System.out.println(h.findWord("era"));



            String randomWord = h.randomWord(4);
            System.out.println("Random WORD: \t" + randomWord);

            //Array con las palabras posibles a partir de una palabra aleatoria
            ArrayList<String> possiblesWords = h.permutationWord(randomWord);

            /*System.out.println("Palabras Posibles");
            for (String words:possiblesWords) {
                System.out.print("\t"+words);
            }*/

            ArrayList<String> trueWords = h.trueWords(possiblesWords);




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

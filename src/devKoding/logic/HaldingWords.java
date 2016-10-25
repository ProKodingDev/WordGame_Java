package devKoding.logic;

import devKoding.Persistence.Conection;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cristhian on 24/10/16.
 */
public class HaldingWords {

    private ArrayList<String> words;
    private Conection conection;


    public HaldingWords() throws SQLException, ClassNotFoundException {
        words = new ArrayList<>();
        conection = new Conection();
        loadWords();
    }


    public void loadWords() throws ClassNotFoundException, SQLException {
        String sql = "select * from palabras";

        try(Connection conn = conection.Connect();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql)){

            while (rs.next()){
                words.add(rs.getString("sin_acentos"));
            }
        }catch (SQLException e){
            System.out.printf("ERROR CONSULTA . " + e.getMessage());
        }

    }

    public boolean findWord(String wordSearch){
        for (String word:words)
            if(wordSearch.equals(word))
                return true;
       return false;
    }

    public ArrayList<String> permutationWord(String input){
        //Set<String> set = new HashSet<String>();
        ArrayList<String> set =  new ArrayList();
        if (input == "")
            return set;

        Character a = input.charAt(0);

        if (input.length() > 1)
        {
            input = input.substring(1);

            ArrayList<String> permSet = permutationWord(input);

            for (String x : permSet)
            {
                for (int i = 0; i <= x.length(); i++)
                {
                    set.add(x.substring(0, i) + a + x.substring(i));
                }
            }
        }
        else
        {
            set.add(a + "");
        }
        return set;
    }


    public String randomWord(int len){
        String AB = "aaaaaaabbbbccccddddeeeeeeeeeefghiiiiiiijklmnññooooooooopqrrrrrrrrstuuuuuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public ArrayList<String> trueWords(ArrayList<String> possiblesWords){
        ArrayList<String> trueWords = new ArrayList<String>();

        for(String word:possiblesWords){
            if (findWord(word)){
                System.out.println("Palabra existe!!");
            }
        }
        System.out.println(possiblesWords.size());
        return trueWords;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }
}

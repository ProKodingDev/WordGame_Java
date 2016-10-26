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
    private ArrayList<String> trueWords;
    private Conection conection;


    public HaldingWords() throws SQLException, ClassNotFoundException {
        words = new ArrayList<>();
        trueWords = new ArrayList<>();
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
            System.out.printf("ERROR CONSULTA" + e.getMessage());
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

    public void loadListTrueWords(String RWord) throws ClassNotFoundException, SQLException {
        String sql = listTrueWords(RWord);

        try(Connection conn = conection.Connect();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql)){

            while (rs.next()){
                trueWords.add(rs.getString("sin_acentos"));
            }
        }catch (SQLException e){
            System.out.printf("ERROR CONSULTA" + e.getMessage());
        }
    }

    public String listTrueWords(String palabra){
        ArrayList<String> letras = new ArrayList<>();
        String cadena = "";

        for (int i = 0; i < palabra.length(); i++){
            char letra = palabra.charAt(i);
            letras.add(String.valueOf(letra));
        }
        if (letras.size() == 1){
            cadena = cadena + cadenaUno(letras.get(0));
        }else if (letras.size() == 2){
            cadena = cadena + cadenaDos(letras.get(0),letras.get(1));
            //una letra
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(0));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(1));
        }else if (letras.size() == 3){
            cadena = cadena + cadenaTres(letras.get(0),letras.get(1),letras.get(2));
            //Dos letras
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(0),letras.get(1));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(0),letras.get(2));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(1),letras.get(2));
            //una letra
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(0));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(1));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(2));
        }else if (letras.size() == 4){
            cadena = cadena + cadenaCuatro(letras.get(0),letras.get(1),letras.get(2),letras.get(3));
            //Tres letras
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(0),letras.get(1),letras.get(2));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(0),letras.get(1),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(0),letras.get(2),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(1),letras.get(2),letras.get(3));
            //Dos letras
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(0),letras.get(1));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(0),letras.get(2));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(0),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(1),letras.get(2));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(1),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(2),letras.get(3));
            //una letra
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(0));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(1));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(2));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(3));
        }else if (letras.size() == 5){
            cadena = cadena + cadenaCinco(letras.get(0),letras.get(1),letras.get(2),letras.get(3),letras.get(4));
            //Cuatro letras
            cadena = cadena + "\nUNION " + cadenaCuatro(letras.get(0),letras.get(1),letras.get(2),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaCuatro(letras.get(0),letras.get(1),letras.get(2),letras.get(4));
            cadena = cadena + "\nUNION " + cadenaCuatro(letras.get(0),letras.get(1),letras.get(3),letras.get(4));
            cadena = cadena + "\nUNION " + cadenaCuatro(letras.get(0),letras.get(2),letras.get(3),letras.get(4));
            cadena = cadena + "\nUNION " + cadenaCuatro(letras.get(1),letras.get(2),letras.get(3),letras.get(4));

            //Tres letras
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(0),letras.get(1),letras.get(2));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(0),letras.get(1),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(0),letras.get(1),letras.get(4));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(0),letras.get(2),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(0),letras.get(2),letras.get(4));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(1),letras.get(2),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(1),letras.get(2),letras.get(4));
            cadena = cadena + "\nUNION " + cadenaTres(letras.get(1),letras.get(3),letras.get(4));
            //Dos letras
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(0),letras.get(1));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(0),letras.get(2));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(0),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(0),letras.get(4));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(1),letras.get(2));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(1),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(1),letras.get(4));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(2),letras.get(3));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(2),letras.get(4));
            cadena = cadena + "\nUNION " + cadenaDos(letras.get(3),letras.get(4));
            //una letra
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(0));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(1));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(2));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(3));
            cadena = cadena + "\nUNION " + cadenaUno(letras.get(4));
        }

        return cadena;
    }

    public String cadenaCinco(String uno, String dos, String tres,String cuatro, String cinco){
        return "SELECT sin_acentos FROM palabras WHERE sin_acentos like \"%"+uno+"%\" AND sin_acentos like \"%"+dos+"%\" AND sin_acentos like \"%"+tres+"%\" AND sin_acentos like \"%"+cuatro+"%\" AND sin_acentos like \"%" + cinco + "%\" AND LENGTH(sin_acentos) <= 5";
    }

    public String cadenaCuatro(String uno, String dos, String tres,String cuatro){
        return "SELECT sin_acentos FROM palabras WHERE sin_acentos like \"%"+uno+"%\" AND sin_acentos like \"%"+dos+"%\" AND sin_acentos like \"%"+tres+"%\" AND sin_acentos like \"%"+cuatro+"%\" AND LENGTH(sin_acentos) <= 4";
    }

    public String cadenaTres(String uno, String dos, String tres){
        return "SELECT sin_acentos FROM palabras WHERE sin_acentos like \"%"+uno+"%\" AND sin_acentos like \"%"+dos+"%\" AND sin_acentos like \"%"+tres+"%\" AND LENGTH(sin_acentos) <= 3";
    }

    public String cadenaDos(String uno, String dos){
        return "SELECT sin_acentos FROM palabras WHERE sin_acentos like \"%"+uno+"%\" AND sin_acentos like \"%"+dos+"%\" AND LENGTH(sin_acentos) <= 2";
    }

    public String cadenaUno(String uno){
        return "SELECT sin_acentos FROM palabras WHERE sin_acentos like \'%"+uno+"%\' AND LENGTH(sin_acentos) <= 1";
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public  ArrayList<String> getTrueWords() {return trueWords;}

    public void setTrueWords(ArrayList<String> trueWords) {
        this.trueWords = trueWords;
    }
}

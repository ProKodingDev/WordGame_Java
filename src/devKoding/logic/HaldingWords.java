package devKoding.logic;

import devKoding.Persistence.Conection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by cristhian on 24/10/16.
 */
public class HaldingWords {

    private ArrayList<String> words;
    private Conection conection;


    public HaldingWords() {
        words = new ArrayList<>();
        conection = new Conection();
    }


    public void loadWords() throws ClassNotFoundException, SQLException {
        String sql = "select sin_acentos from palabras";

        try(Connection conn = conection.Connect();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql)){

            while (rs.next()){
                System.out.println(rs.getString("sin_acentos"));
            }
        }catch (SQLException e){
            System.out.printf("ERROR CONSULTA . " + e.getMessage());
        }

    }
}

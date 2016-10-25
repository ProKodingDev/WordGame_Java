package devKoding.Test;

import devKoding.Persistence.Conection;
import devKoding.logic.HaldingWords;

import java.sql.SQLException;

/**
 * Created by cristhian on 24/10/16.
 */
public class RunTest {

    public static void main(String[] args) {

        HaldingWords h = new HaldingWords();

        try {
            h.loadWords();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

package devKoding.Test;

import devKoding.Persistence.Conection;

/**
 * Created by cristhian on 24/10/16.
 */
public class RunTest {

    public static void main(String[] args) {
        Conection con = new Conection();

        try {
            con.Connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

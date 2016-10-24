package devKoding.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by cristhian on 24/10/16.
 */
public class Conection {

    String url= ".\\Resources\\Db\\dictionary.db";

    Connection connect;

    public void Connect() throws ClassNotFoundException {

        try {
            Class.forName("org.sqlite.JDBC");
            connect =  DriverManager.getConnection("jdbc:sqlite:"+url);
            if (connect!=null)
                System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println("No se pudo conectar a la BD" + e.getMessage());
        }


    }
}

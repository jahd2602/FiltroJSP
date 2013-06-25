/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paw.honorio;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sistemas
 */
public class Conexion {
        public static final String STR_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String STR_CONNECTION = "jdbc:derby://localhost:1527/jahd";
    public static final String STR_USER = "app";
    public static final String STR_PWD = "app";
    private static Conexion instance;
    private Connection connection;
    private boolean error;

    private Conexion() {
        try {
            Class.forName(STR_DRIVER);
            connection = DriverManager.getConnection(STR_CONNECTION, STR_USER, STR_PWD);
            error = false;
        } catch (Exception e) {
            connection = null;
            e.printStackTrace();
            error = true;
        }
    }

    public static Conexion getInstance() throws Exception {
        if (instance == null ||instance.error) {
            instance = new Conexion();
        }
        if (instance.error) {
            throw new Exception("No se pudo conectar a la Base de Datos");
        } else {
            return instance;
        }

    }

    public Connection getConnection() {
        return connection;
    }
    
    
}

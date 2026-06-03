package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static Connection connection = null;
    private static final String url =  "jdbc:mysql://localhost:3306/";
    private static final String base = "gestion_aulas";
    private static final String usuario = "root";
    private static final String password = "Papitas.02";

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(url, usuario, password);
            connection.createStatement().execute("CREATE DATABASE IF NOT EXISTS "+ base);
            connection.createStatement().execute("USE "+ base);
        } catch (Exception e) {
            System.err.println("Error al crear el BD: "+e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Cerrando la BD: "+connection);
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la BD: "+e.getMessage());
        }
    }
}


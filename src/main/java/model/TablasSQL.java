package model;

import java.sql.Connection;

public class TablasSQL {

    //Esto lo hice solo para probar si funcionaba el metodo y el comboBox poniendo algunas aulas por defecto
    private static String generarCodigoAleatorio() {
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            int letraRandom = (int) (Math.random() * letras.length());
            codigo.append(letras.charAt(letraRandom));
        }
        int numeroAleatorio = (int) (Math.random() * 9)+1;
        codigo.append(numeroAleatorio);
        return codigo.toString();
    }

    public static void createTable() {

        String tablaAulas = "CREATE TABLE IF NOT EXISTS aulas (" +
                "Cod_aula CHAR(4) PRIMARY KEY, " +
                "Nombre_aula VARCHAR(50) NOT NULL, " +
                "Ubicacion VARCHAR(50), " +
                "Capacidad_Alumnos INT)";

        String tablaEquipos = "CREATE TABLE IF NOT EXISTS equipos (" +
                "Cod_equipo CHAR(5) PRIMARY KEY, " +
                "Marca_Modelo VARCHAR(50), " +
                "Procesador VARCHAR(50), " +
                "Direccion_IP CHAR(15), " +
                "Sistema_Operativo VARCHAR(30), " +
                "RAM INT, " +
                "Alamcenamiento DOUBLE, " +
                "Cod_aula CHAR(4), " +
                "FOREIGN KEY (Cod_aula) REFERENCES aulas(Cod_aula) ON DELETE CASCADE)";

        try (Connection connection = Conexion.getConnection();
             java.sql.Statement stmt = connection.createStatement()) {


            stmt.execute(tablaAulas);
            stmt.execute(tablaEquipos);

            String insAulas =  "INSERT INTO aulas (Cod_aula, Nombre_aula, Ubicacion, Capacidad_Alumnos) VALUES " +
                    "('A001', 'Aula_1', 'Planta 1', 25), " +
                    "('A002', 'Aula_2', 'Planta 2', 30), " +
                    "('A003', 'Aula_3', 'Planta 1', 20)";
            stmt.execute(insAulas);

            System.out.println("Base de datos e inserciones iniciales creadas con éxito.");

        } catch (Exception e) {
            System.err.println("Error al crear la base de datos: " + e.getMessage());
        }
    }
}

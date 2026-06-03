package model;

import java.sql.*;
import java.util.ArrayList;

public class AulasService {

    // Ahora el método de los codigos aleatorios del punto 5
    public static  String generarCodigo(String nombreAula) {
        // Me aseguro que todo quede limpito
        String codigo = nombreAula.trim().replaceAll("\\s+", "").toUpperCase();

        // Ahora se extrae exactamente 3 letras del String que se recibe
        if (codigo.length() >= 3) {
            codigo = codigo.substring(0, 3);
        } else {
            while (codigo.length() < 3) {
                codigo += "X";
            }
        }
        // Ahora se cuenta cuantas aulas cuentan con esa misma secuencia de letras para calcular el indice
        String sql = "SELECT COUNT(*) FROM aulas WHERE Cod_aula LIKE ?";
        int coincidencias = 0;

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, codigo + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    coincidencias = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al calcular secuencia: "+e.getMessage());
        }
        // Se supone debe de devolver las 3 letras y el número sumado con la coincidencia para que no se repita
        return codigo + (coincidencias + 1);
    }

    // Metodo para introducir nueva aula
    public static boolean insertarAulas(Aulas aula) {
        String sql = "INSERT INTO aulas (Cod_aula, Nombre_aula, Ubicacion, Capacidad_Alumnos) VALUES (?,?,?,?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, aula.getCod_aula());
            pstmt.setString(2, aula.getNombre_aula());
            pstmt.setString(3, aula.getUbicacion());
            pstmt.setInt(4, aula.getCapacidad_Alumnos());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar aula: "+e.getMessage());
        }
        return false;
    }

    // Método para rellenar ComboBox

    public static ArrayList<Aulas> obtenerAulas() {
        ArrayList<Aulas> aulas = new ArrayList<>();
        String sql = "SELECT * FROM aulas";

        try (Connection connection = Conexion.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                aulas.add(new Aulas(
                        rs.getString("Cod_aula"),
                        rs.getString("Nombre_aula"),
                        rs.getString("Ubicacion"),
                        rs.getInt("Capacidad_Alumnos")
                ));
            }
        } catch (SQLException es) {
            System.err.println("Error al obtener aulas: "+es.getMessage());
        }
        return aulas;
    }

    /* Clausurar aulas (ejercicio 4)
    A menos que haya una RAM con una capacidad mayor a 32, en ese caso se cancela el borrado
     */
    public static String clausurarAula(String codAula) {
        String sqlCheck = "SELECT COUNT(*) FROM equipos WHERE Cod_aula = ? AND RAM >= 32";
        String sqlDelete = "DELETE FROM aulas WHERE Cod_aula = ?";
        String sqlDeleteEquipos = "DELETE FROM equipos WHERE Cod_aula = ?";

        try (Connection connection = Conexion.getConnection()) {
            // Verifico la RAM primero
            try (PreparedStatement pstmtCheck = connection.prepareStatement(sqlCheck)) {
                pstmtCheck.setString(1, codAula);
                try (ResultSet rs = pstmtCheck.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        return "No se puede clausurar esta aula";
                    }
                }
            }
            try (PreparedStatement pstmtDeleteEquipos = connection.prepareStatement(sqlDelete)) {
                pstmtDeleteEquipos.setString(1, codAula);
                pstmtDeleteEquipos.executeUpdate();
            }
            // Lo de arriba es puro filtro, si lo pasa, se clausura el aula
            try (PreparedStatement pstmtDelete = connection.prepareStatement(sqlDelete)) {
                pstmtDelete.setString(1,codAula);
                if(pstmtDelete.executeUpdate() > 0) {
                    return "Borrado de aula completado con éxito";
                }
            }
        } catch (SQLException es) {
            System.err.println("Error al clausurar aula: "+es.getMessage());
        }
        return "Error imprevisto";
    }
}


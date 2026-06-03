package model;

import java.sql.*;
import java.util.ArrayList;

public class EquiposService {

    //Metodo para generar codigos random en los equipos:
    public static String generarCodigoAleatorio() {
        String letras = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int letraRandom = (int) (Math.random() * letras.length());
            codigo.append(letras.charAt(letraRandom));
        }
        int numeroAleatorio = (int) (Math.random() * 9)+1;
        codigo.append(numeroAleatorio);
        return codigo.toString();
    }

    //Obtener equipos por aula y llenar JList
    public static ArrayList<Equipos> obtenerEquipos(String codAula) {
        ArrayList<Equipos> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos WHERE Cod_aula = ?";

        try (Connection connec = Conexion.getConnection();
             PreparedStatement pstmt = connec.prepareStatement(sql)) {

            pstmt.setString(1, codAula);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    equipos.add(new Equipos(
                            rs.getString("Cod_equipo"),
                            rs.getString("Marca_Modelo"),
                            rs.getString("Procesador"),
                            rs.getString("Direccion_IP"),
                            rs.getString("Sistema_Operativo"),
                            rs.getInt("RAM"),
                            rs.getDouble("Alamcenamiento"), // Respetamos tu nombre de columna 'Alamcenamiento'
                            rs.getString("Cod_aula")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener equipos: " + e.getMessage());
        }
        return equipos;
    }

    // Nota: Falta el metodo de modificar RAM y el metodo para insertar equipos (continúo mañana)
    public static boolean modificarRAM(String codEquipo, int RAM) {
        String sql = "UPDATE equipos SET RAM = ? WHERE Cod_equipo = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, RAM);
            pstmt.setString(2, codEquipo);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar RAM: " + e.getMessage());
            return false;
        }
    }

    public static String insertarEquipo(Equipos equipos) {
        String sqlContar = "SELECT COUNT(*) FROM equipos WHERE Cod_aula = ?";
        String sqlInsert = "INSERT INTO equipos (Cod_equipo, Marca_Modelo, Procesador, Direccion_IP, Sistema_Operativo, RAM, Alamcenamiento, Cod_aula) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection con = Conexion.getConnection()) {
            try (PreparedStatement pstmtContar = con.prepareStatement(sqlContar)) {
                pstmtContar.setString(1, equipos.getCod_aula());
                try (ResultSet rs = pstmtContar.executeQuery()) {
                    if (rs.next() && rs.getInt(1) >= 31) {
                        return "Limite alcanzado";
                    }
                }
            }
            try (PreparedStatement pstmtInsert = con.prepareStatement(sqlInsert)) {
                pstmtInsert.setString(1, equipos.getCod_equipo());
                pstmtInsert.setString(2, equipos.getMarca_Modelo());
                pstmtInsert.setString(3, equipos.getProcesador());
                pstmtInsert.setString(4, equipos.getDireccion_IP());
                pstmtInsert.setString(5, equipos.getSistema_Operativo());
                pstmtInsert.setInt(6, equipos.getRAM());
                pstmtInsert.setDouble(7, equipos.getAlmacenamiento());
                pstmtInsert.setString(8, equipos.getCod_aula());

                if (pstmtInsert.executeUpdate() > 0) {
                    return "Update";
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar equipo: " + e.getMessage());
        }
        return "Error";
    }
}


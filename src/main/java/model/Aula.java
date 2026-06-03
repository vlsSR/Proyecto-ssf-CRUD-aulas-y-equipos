package model;

public class Aula {
    private String Cod_aula;
    private String Nombre_aula;
    private String Ubicacion;
    private int Capacidad_Alumnos;

    public Aula(String cod_aula, String nombre_aula, String ubicacion, int capacidad_Alumnos) {
        this.Capacidad_Alumnos = capacidad_Alumnos;
        this.Cod_aula = cod_aula;
        this.Nombre_aula = nombre_aula;
        this.Ubicacion = ubicacion;
    }

    public int getCapacidad_Alumnos() {
        return Capacidad_Alumnos;
    }

    public void setCapacidad_Alumnos(int capacidad_Alumnos) {
        Capacidad_Alumnos = capacidad_Alumnos;
    }

    public String getCod_aula() {
        return Cod_aula;
    }

    public void setCod_aula(String cod_aula) {
        Cod_aula = cod_aula;
    }

    public String getNombre_aula() {
        return Nombre_aula;
    }

    public void setNombre_aula(String nombre_aula) {
        Nombre_aula = nombre_aula;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return Nombre_aula;
    }
}


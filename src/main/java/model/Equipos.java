package model;

public class Equipos {
    private String Cod_equipo;
    private String Marca_Modelo;
    private String Procesador;
    private String Direccion_IP;
    private String Sistema_Operativo;
    private int RAM;
    private double Almacenamiento;
    private String Cod_aula;

    public Equipos(String cod_equipo, String marca_Modelo, String procesador, String direccion_IP, String sistema_Operativo, int RAM, double almacenamiento, String cod_aula) {
        this.Almacenamiento = almacenamiento;
        this.Cod_aula = cod_aula;
        this.Cod_equipo = cod_equipo;
        this.Direccion_IP = direccion_IP;
        this.Marca_Modelo = marca_Modelo;
        this.Procesador = procesador;
        this.RAM = RAM;
        this.Sistema_Operativo = sistema_Operativo;
    }

    public double getAlmacenamiento() {
        return Almacenamiento;
    }

    public void setAlmacenamiento(double almacenamiento) {
        Almacenamiento = almacenamiento;
    }

    public String getCod_aula() {
        return Cod_aula;
    }

    public void setCod_aula(String cod_aula) {
        Cod_aula = cod_aula;
    }

    public String getCod_equipo() {
        return Cod_equipo;
    }

    public void setCod_equipo(String cod_equipo) {
        Cod_equipo = cod_equipo;
    }

    public String getDireccion_IP() {
        return Direccion_IP;
    }

    public void setDireccion_IP(String direccion_IP) {
        Direccion_IP = direccion_IP;
    }

    public String getMarca_Modelo() {
        return Marca_Modelo;
    }

    public void setMarca_Modelo(String marca_Modelo) {
        Marca_Modelo = marca_Modelo;
    }

    public String getProcesador() {
        return Procesador;
    }

    public void setProcesador(String procesador) {
        Procesador = procesador;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public String getSistema_Operativo() {
        return Sistema_Operativo;
    }

    public void setSistema_Operativo(String sistema_Operativo) {
        Sistema_Operativo = sistema_Operativo;
    }

    @Override
    public String toString() {
        return Cod_equipo + " - " + Marca_Modelo + " (" + RAM + "GB)";
    }
}

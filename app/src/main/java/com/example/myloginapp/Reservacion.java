package com.example.myloginapp;

public class Reservacion {
    private String codSalon;
    private String tiempoInicio;
    private String tiempoFinal;

    public Reservacion(String codSalon, String tiempoInicio, String tiempoFinal) {
        this.codSalon = codSalon;
        this.tiempoInicio = tiempoInicio;
        this.tiempoFinal = tiempoFinal;
    }

    public String getCodSalon() {
        return codSalon;
    }

    public String getTiempoInicio() {
        return tiempoInicio;
    }

    public String getTiempoFinal() {
        return tiempoFinal;
    }
}

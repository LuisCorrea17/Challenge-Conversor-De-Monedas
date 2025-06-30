package com.aluracursos.challengeconversor.model;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Historial {

    private LocalDateTime fecha;
    private String monedaOrigen;
    private String monedaDestino;
    private double cantidad;
    private double resultado;

    public Historial(LocalDateTime fecha, String monedaOrigen, String monedaDestino, double cantidad, double resultado) {
        this.fecha = fecha;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.cantidad = cantidad;
        this.resultado = resultado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fecha.format(dtf)
         + " - " + cantidad + " " + monedaOrigen
         + " a " + df.format(resultado) + " " + monedaDestino;
    }

    

}

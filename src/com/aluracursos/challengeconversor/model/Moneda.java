package com.aluracursos.challengeconversor.model;

public class Moneda {
    
    private String currencyCode;
    private String currencyName;
    private Double MXN;
    private Double USD;
    private Double ARS;
    private Double BRL;
    private Double COP;

    public Moneda(MonedaOmdb monedaOmdb, String currencyName) {
        this.currencyCode = monedaOmdb.base_code();
        this.MXN = monedaOmdb.conversion_rates().get("MXN");
        this.USD = monedaOmdb.conversion_rates().get("USD");
        this.ARS = monedaOmdb.conversion_rates().get("ARS");
        this.BRL = monedaOmdb.conversion_rates().get("BRL");
        this.COP = monedaOmdb.conversion_rates().get("COP");
        this.currencyName = currencyName;
    }

    public double convertirMoneda(int monedaDestino, double cantidad) {
        switch (monedaDestino) {
            case 1: // Dólar estadounidense
                return cantidad * USD;
            case 2: // Real brasileño
                return cantidad * BRL;
            case 3: // Peso argentino
                return cantidad * ARS;
            case 4: // Peso mexicano
                return cantidad * MXN;
            case 5: // Peso colombiano
                return cantidad * COP;
            default:
                throw new IllegalArgumentException("Moneda no válida");
        }
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public Double getMXN() {
        return MXN;
    }

    public Double getUSD() {
        return USD;
    }

    public Double getARS() {
        return ARS;
    }

    public Double getBRL() {
        return BRL;
    }

    public Double getCOP() {
        return COP;
    }
}

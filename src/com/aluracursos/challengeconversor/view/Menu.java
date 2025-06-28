package com.aluracursos.challengeconversor.view;

import java.util.Scanner;
import com.google.gson.Gson;

import com.aluracursos.challengeconversor.model.Moneda;
import com.aluracursos.challengeconversor.model.MonedaOmdb;
import com.aluracursos.challengeconversor.services.ConsumoAPI;

public class Menu {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Gson gson = new Gson();
    private Moneda moneda;
    private final String URL_BASE = "https://v6.exchangerate-api.com/v6/";
    private final String API_KEY = "6ad1b42e2136fa76bf49cf0d/latest/";

    public void muestraMenu() {
        boolean menu = true;
        while (menu) {

            System.out.println("******************************************");
            System.out.println("Bienvenido al conversor de monedas");
            System.out.println("******************************************");
            System.out.println("\n1. Dólar estadounidense (USD)");
            System.out.println("2. Real brasileño (BRL)");
            System.out.println("3. Peso argentino (ARS)");
            System.out.println("4. Peso mexicano (MXN)");
            System.out.println("5. Peso colombiano (COP)");
            System.out.println("6. Salir");
            System.out.println("\nPor favor selecciona la moneda con la que deseas trabajar:");
            int monedaPrincipal = teclado.nextInt();

            String json = "";
            String nombreMoneda = "";

            switch (monedaPrincipal) {
                case 1:
                    json = consumoAPI.obtenerDatos(URL_BASE + API_KEY + "USD");
                    nombreMoneda = "Dólar estadounidense";
                    break;
                case 2:
                    json = consumoAPI.obtenerDatos(URL_BASE + API_KEY + "BRL");
                    nombreMoneda = "Real brasileño";
                    break;
                case 3:
                    json = consumoAPI.obtenerDatos(URL_BASE + API_KEY + "ARS");
                    nombreMoneda = "Peso argentino";
                    break;
                case 4:
                    json = consumoAPI.obtenerDatos(URL_BASE + API_KEY + "MXN");
                    nombreMoneda = "Peso mexicano";
                    break;
                case 5:
                    json = consumoAPI.obtenerDatos(URL_BASE + API_KEY + "COP");
                    nombreMoneda = "Peso colombiano";
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    menu = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una moneda válida.");
                    break;
            }

            if (!json.isEmpty() && !nombreMoneda.isEmpty()) {
                MonedaOmdb monedaOmdb = gson.fromJson(json, MonedaOmdb.class);
                moneda = new Moneda(monedaOmdb, nombreMoneda);
                System.out.println(
                        "\nMoneda seleccionada: " + moneda.getCurrencyName() + " (" + moneda.getCurrencyCode() + ")\n");
                System.out.println("1. Dólar estadounidense (USD)");
                System.out.println("2. Real brasileño (BRL)");
                System.out.println("3. Peso argentino (ARS)");
                System.out.println("4. Peso mexicano (MXN)");
                System.out.println("5. Peso colombiano (COP)");
                var submenu = true;
                while (submenu) {
                    System.out.println("\nSelecciona la moneda a la que deseas convertir:");
                    int monedaDestino = teclado.nextInt();

                    System.out.println("\nIntroduce la cantidad que deseas convertir:");
                    double cantidad = teclado.nextDouble();

                    double conversion = moneda.convertirMoneda(monedaDestino, cantidad);

                    System.out.println("\n" + cantidad + " " + moneda.getCurrencyCode() + " equivale a " + conversion + " " +
                            switch (monedaDestino) {
                                case 1 -> "USD";
                                case 2 -> "BRL";
                                case 3 -> "ARS";
                                case 4 -> "MXN";
                                case 5 -> "COP";
                                default -> "moneda no válida";
                            });

                    System.out.println("\n¿Deseas realizar otra conversión?");
                    System.out.println("1. Sí");
                    System.out.println("2. No\n");
                    int opcion = teclado.nextInt();
                    if (opcion == 2) {
                        submenu = false;
                    } else if (opcion != 1) {
                        System.out.println("Opción no válida.");
                    }
                }
            }
        }
    }
}

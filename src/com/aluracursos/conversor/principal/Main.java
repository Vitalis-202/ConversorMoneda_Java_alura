package com.aluracursos.conversor.principal;

import com.aluracursos.conversor.moneda.Monedas;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        //Variables
        double opcion_moneda = 0;
        int opcion = 0;
        double resultado = 0;
        Scanner teclado = new Scanner(System.in);

        //API
        String direccion = "https://v6.exchangerate-api.com/v6/c4a2acfcbc52bc197bada9ef/latest/USD";

        //Request al servidor - Arquitectura Cliente Servidor.
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        //Recibir los datos
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);

        //Gson importado
        Gson gson = new Gson();
        Monedas miMoneda = gson.fromJson(json, Monedas.class);
        System.out.println(miMoneda.getMoneda());
        System.out.println(miMoneda.getConversion_rates().get("ARS"));
        System.out.println(miMoneda.getConversion_rates());

        //Menu
        String menu = """
                **********************************************
                *** Escriba el número de la opción deseada ***
                1 - Dolar(USD) ==> Peso Argentino(ARS).
                2 - Peso Argentino(ARS) ==> Dolar(USD).
                3 - Dolar(USD) ==> Real Brasileño(BRL).
                4 - Real Brasileño(BRL) ==> Dolar(USD).
                5 - Dolar(USD) ==> Peso Colombiano(COP).
                6 - Peso Colombiano(COP) ==> Dolar(USD).
                7 - Salir
                **********************************************
                Elija una opción valida:
                """;
        String menuInterno = """
                **********************************************
                Elija el monto que desea convertir
                """;
        //Bucle de la opcion
        try {

            while (opcion != 7) {
                System.out.println(menu);
                opcion = teclado.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println(menuInterno);
                        opcion_moneda = teclado.nextDouble();
                        resultado = miMoneda.transformacionUSD(opcion_moneda, "ARS");
                        System.out.println("La conversión de " + opcion_moneda + " usd es de " + resultado + " ARS.");
                        break;
                    case 2:
                        System.out.println(menuInterno);
                        opcion_moneda = teclado.nextDouble();
                        resultado = miMoneda.transformacionMoneda(opcion_moneda, "ARS");
                        System.out.println("La conversión de " + opcion_moneda + " ARS es de " + resultado + " USD.");
                        break;
                    case 3:
                        System.out.println(menuInterno);
                        opcion_moneda = teclado.nextDouble();
                        resultado = miMoneda.transformacionUSD(opcion_moneda, "BRL");
                        System.out.println("La conversión de " + opcion_moneda + " usd es de " + resultado + " BRL.");
                        break;
                    case 4:
                        System.out.println(menuInterno);
                        opcion_moneda = teclado.nextDouble();
                        resultado = miMoneda.transformacionMoneda(opcion_moneda, "BRL");
                        System.out.println("La conversión de " + opcion_moneda + " BRL es de " + resultado + " USD.");
                        break;
                    case 5:
                        System.out.println(menuInterno);
                        opcion_moneda = teclado.nextDouble();
                        resultado = miMoneda.transformacionUSD(opcion_moneda, "COP");
                        System.out.println("La conversión de " + opcion_moneda + " usd es de " + resultado + " COP.");
                        break;
                    case 6:
                        System.out.println(menuInterno);
                        opcion_moneda = teclado.nextDouble();
                        resultado = miMoneda.transformacionMoneda(opcion_moneda, "COP");
                        System.out.println("La conversión de " + opcion_moneda + " COP es de " + resultado + " USD.");
                        break;
                    case 7:
                        System.out.println("Saliendo del programa.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error de formato de dato. Favor corregir.");
        } catch (NumberFormatException e) {
            System.out.println("Ocurrio un error: ");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrio un error: ");
            System.out.println(e.getMessage());
        }
    }
}
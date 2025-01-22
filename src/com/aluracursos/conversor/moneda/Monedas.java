package com.aluracursos.conversor.moneda;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Monedas {

    //Variables
    @SerializedName("base_code")
    private String moneda;
    @SerializedName("conversion_rates")
    Map<String, Double> conversion_rates;
    //Getter and Setter

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }
    //toString



    //Metodos Custom
    public double transformacionUSD(double usd,String moneda ){
        return  Math.round((usd* getConversion_rates().get(moneda)) * 1000.0) / 1000.0;
    }
    public double transformacionMoneda(double monedaConvertir,String moneda ){
        return  Math.round((monedaConvertir/ getConversion_rates().get(moneda)) * 1000.0) / 1000.0;
    }

}

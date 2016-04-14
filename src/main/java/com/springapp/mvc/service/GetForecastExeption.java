package com.springapp.mvc.service;

/**
 * Created by aneskladnyi on 07.04.2016.
 */
public class GetForecastExeption extends Exception {
    private static String message="Exeption in GetForecasrt implementetion";
    public GetForecastExeption(String message){
        super(message);
    }
    public GetForecastExeption(){
        super(message);
    }
}

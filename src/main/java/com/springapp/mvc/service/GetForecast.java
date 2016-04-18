package com.springapp.mvc.service;

import com.springapp.mvc.model.City;
import com.springapp.mvc.model.Forecast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by home on 05.04.2016.
 */
public interface GetForecast {
    Forecast GetCurentForecast(String cityId);
    List<Forecast> GetForecast(String cityId, Date BorderForeCastDate) throws GetForecastExeption;
//
}

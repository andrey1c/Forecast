package com.springapp.mvc.dao;

import com.springapp.mvc.model.Forecast;

import java.util.List;

/**
 * Created by aneskladnyi on 06.04.2016.
 */
public interface ForecastDAO {
    public void save(Forecast p);

    public List<Forecast> list();

}

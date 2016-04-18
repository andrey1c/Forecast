package com.springapp.mvc.model;

import javax.persistence.*;

/**
 * Created by home on 14.04.2016.
 */
@Entity
@Table(name = "Cities")
public class City {
    private int version;
    private int cityId;
    private String cityName;
    //private Forecast forecast;

    public City() {
    }

    public City(int cityId) {
        this.cityId = cityId;
        this.cityName = "Kiyv";
    }

    @Id
    @Column(name = "CITY_ID")
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    //
    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "CITY_NAME")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

  //  @OneToOne(mappedBy = "city")
//    public Forecast getForecast() {
//        return forecast;
//    }
//
//    public void setForecast(Forecast forecast) {
//        this.forecast = forecast;
//    }

    @Override
    public String toString() {
        return "City{" +
                ", cityId=" + cityId +
                ", version=" + version +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}

package com.springapp.mvc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by home on 05.04.2016.
  */
@Entity
@Table(name="FORECAST")
public class Forecast {
    private int id;
    private int version;
    private String cityId;
    private String sourceName;
    private Date requestDate;
    private Date forecastDate;
    private float temperature;
    private float pressure;
    private float humidity;
    private float wind;

    //
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //
    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    //

    @Column(name="CITY_ID")
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    //
    @Column(name="SOURCE_NAME")
    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
    //
    @Column(name="REQUEST_DATE")
    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    //
    @Column(name="FORECAST_DATE")
    public Date getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(Date forecastDate) {
        this.forecastDate = forecastDate;
    }
    //
    @Column(name="TEMPERATURE")
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
    //
    @Column(name="PRESSURE")
    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
    //
    @Column(name="HUMIDITY")
    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
    //
    @Column(name="WING_SPEED")
    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "id=" + id +
                ", version=" + version +
                ", city='" + cityId + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", requestDate=" + requestDate +
                ", forecastDate=" + forecastDate +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", wind=" + wind +
                '}';
    }
}


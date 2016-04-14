package com.springapp.mvc.service;

import com.springapp.mvc.model.Forecast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by aneskladnyi on 06.04.2016.
 */
@Component
public class GetForecastOpenWeather implements GetForecast {

    @Override
    public Forecast GetCurentForecast(String cityName) {
        String link = "http://api.openweathermap.org/data/2.5/weather?q=Kiev&appid=62da61a0a9c3a34050f0ce16be658201";
        String JsonString = getJsonString(link);
        JSONObject dataJsonObj = null;
        String secondName = "";
        String temp="";
        String pressure="";
        String humidity="";
        try {
            dataJsonObj = new JSONObject(JsonString);
            JSONObject main=dataJsonObj.getJSONObject("main");
            temp = main.getString("temp");
            pressure = main.getString("pressure");
            humidity = main.getString("humidity");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Forecast forecast=new Forecast();
        forecast.setCity(cityName);
        forecast.setSourceName("openweathermap");
        forecast.setRequestDate(new Date());
        forecast.setForecastDate(new Date());

        forecast.setTemperature(Float.parseFloat(temp));
        return forecast;
    }

    @Override
    public List<Forecast> GetForecast(String cityId, Date foreCastDate) throws GetForecastExeption {
        //нужно будет каким то образом получать id городов
        //проверка на дату большу чем 16, выкинуть исключение
        GregorianCalendar calforeCastDate = new GregorianCalendar();
        calforeCastDate.setTime(foreCastDate);
        calforeCastDate.set(Calendar.HOUR_OF_DAY,0);
        calforeCastDate.set(Calendar.MINUTE,0);
        calforeCastDate.set(Calendar.MILLISECOND,0);
        GregorianCalendar currentTime = new GregorianCalendar(); //current time
        currentTime.set(Calendar.HOUR_OF_DAY,0);
        currentTime.set(Calendar.MINUTE,0);
        currentTime.set(Calendar.MILLISECOND,0);
        int numOfDays=0;
        while(currentTime.compareTo(calforeCastDate)<=0){
            numOfDays++;
            currentTime.set(Calendar.DAY_OF_MONTH,currentTime.get(Calendar.DAY_OF_MONTH)+1);
        }
        if(numOfDays>16){
            throw  new GetForecastExeption("max days for forecast is 16");
        }
        //http://api.openweathermap.org/data/2.5/forecast/daily?id=703448&cnt=7&appid=62da61a0a9c3a34050f0ce16be658201
        String link = "http://api.openweathermap.org/data/2.5/forecast/daily?id="+cityId+"&cnt="+Integer.toString(numOfDays)+"&appid=62da61a0a9c3a34050f0ce16be658201";
        String JsonString = getJsonString(link);
        JSONObject dataJsonObj = null;
        String temp="";
        String pressure="";
        String humidity="";
        List<Forecast> listForecast=new ArrayList<Forecast>();
        try {
            dataJsonObj = new JSONObject(JsonString);
            JSONArray listObj = dataJsonObj.getJSONArray("list");
            for (int i = 0; i < listObj.length(); i++) {
                JSONObject DayForecast = listObj.getJSONObject(i);
                String dt=DayForecast.getString("dt");
                Date day = new Date(Long.parseLong(dt)*1000L);
                JSONObject TempObj=DayForecast.getJSONObject("temp");
                temp = TempObj.getString("day");
                pressure = DayForecast.getString("pressure");
                humidity = DayForecast.getString("humidity");
                //
                Forecast forecast=new Forecast();
                forecast.setCity("Kyiv");
                forecast.setSourceName("openweathermap");
                forecast.setTemperature(Float.parseFloat(temp));
                forecast.setPressure(Float.parseFloat(pressure));
                forecast.setHumidity(Float.parseFloat(humidity));
                forecast.setRequestDate(new Date());
                forecast.setForecastDate(day);
                listForecast.add(forecast);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e) { //вопрос а нужно ли ?
            e.printStackTrace();
        }

        return listForecast;
    }

    private static String getJsonString(String link) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";
        try {
           // link="https://www.google.com.ua";
            URL url = new URL(link);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJson;
    }
}

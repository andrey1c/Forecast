package com.springapp.mvc;

import com.springapp.mvc.dao.ForecastDAO;
import com.springapp.mvc.model.Forecast;
import com.springapp.mvc.service.GetForecastExeption;
import com.springapp.mvc.service.GetForecastOpenWeather;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by aneskladnyi on 06.04.2016.
 */
@Controller
@RequestMapping("/StartObserver")

public class TaskGetForecast {
    @Autowired
    GetForecastOpenWeather CurForecast;
    @Autowired
    ForecastDAO forecastDAO;
    protected final Log logger = LogFactory.getLog(getClass());
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        Forecast test=CurForecast.GetCurentForecast("sss");
        //GetListForecat();
      model.addAttribute("message", "GetListForecat");
      return "startTaskGetForecast";
    }
    private void GetListForecat(){
        GregorianCalendar calendar = new GregorianCalendar();
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        day=day+6;
        calendar.set(Calendar.DAY_OF_MONTH,day);
        List<Forecast> listForecast=new ArrayList<Forecast>();
        try {
            listForecast = CurForecast.GetForecast("703448",calendar.getTime());
        }catch (GetForecastExeption e){

        }
        for(Forecast curForecast:listForecast){
            forecastDAO.save(curForecast);
        }


        List<Forecast> forecastList=forecastDAO.list();
        for(Forecast curForecast:forecastList){
            logger.info(curForecast.toString());
        }
    }
   //@Scheduled(fixedDelay=9900000)
    public void doRequest() {
       try {
           GetListForecat();
       }catch (Exception e){
           logger.info("Error in Scheduled");
       }

    }
}

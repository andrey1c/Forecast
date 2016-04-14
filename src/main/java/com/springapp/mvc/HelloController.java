package com.springapp.mvc;

import com.springapp.mvc.dao.ForecastDAO;
import com.springapp.mvc.model.Forecast;
import com.springapp.mvc.service.GetForecast;
import com.springapp.mvc.service.GetForecastOpenWeather;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//
@Controller
@RequestMapping("/1")
public class HelloController {
	@Autowired
	GetForecastOpenWeather CurForecast;
	@Autowired
	ForecastDAO forecastDAO;
	protected final Log logger = LogFactory.getLog(getClass());
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		//
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mvc-dispatcher-servlet.xml");
		//ctx.refresh();

		//GetForecastOpenWeather CurForecast = ctx.getBean("GetForecastOpenWeather", GetForecastOpenWeather.class);
		Forecast forecast = CurForecast.GetCurentForecast("Kiev");
		//ForecastDAO forecastDAO = ctx.getBean("ForecastDAO", ForecastDAO.class);
		forecastDAO.save(forecast);

		List<Forecast> forecastList=forecastDAO.list();
		for(Forecast curForecast:forecastList){
			logger.info(curForecast.toString());
		}

		/*
		String link="http://api.openweathermap.org/data/2.5/weather?q=Kiev&appid=62da61a0a9c3a34050f0ce16be658201";
		String JsonString=getJsonString(link);
		model.addAttribute("textJson", JsonString);
		logger.info(JsonString);
		*/

		/*try {
			doc = Jsoup.connect("http://api.openweathermap.org/data/2.5/weather?q=Kiev&appid=62da61a0a9c3a34050f0ce16be658201").get();
			model.addAttribute("textJsondoc", doc.text());
			logger.info(doc.text());
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	/*	float data1;
		//Elements links = doc.select("a[href]");
		Elements links=doc.getElementsByClass("link_post");
//		Elements media = doc.select("[src]");
//		Elements imports = doc.select("link[href]");
		ArrayList<String> linkArray=new ArrayList<String>();
		for (Element link : links) {
			if(!linkArray.contains(link.attr("abs:href"))) {
				linkArray.add(link.attr("abs:href"));
				logger.info(link.attr("abs:href") + link.text());
			}
		}
		Document curDoc = null;
		for(String link : linkArray){
			try {
				curDoc = Jsoup.connect(link).get();
				Elements arrayOfContacts=curDoc.getElementsByClass("post-contacts");
				if(!arrayOfContacts.isEmpty()){
					logger.info("text ad "+arrayOfContacts.get(0).parent().text());
					logger.info("post-contacts"+" "+arrayOfContacts.get(0).text());
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		//
		return "hello";
	}
	/*static String getJsonString(String link){
		HttpURLConnection urlConnection = null;
		BufferedReader reader = null;
		String resultJson = "";
		try {
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
	}*/
	//
	/*protected void onPostExecute(String strJson) {


		JSONObject dataJsonObj = null;
		String secondName = "";

		try {
			dataJsonObj = new JSONObject(strJson);
			JSONArray friends = dataJsonObj.getJSONArray("friends");

			// 1. ������� ���� � ������ ����� - ������ 1
			JSONObject secondFriend = friends.getJSONObject(1);
			secondName = secondFriend.getString("name");


			// 2. ���������� � ������� �������� ������� �����
			for (int i = 0; i < friends.length(); i++) {
				JSONObject friend = friends.getJSONObject(i);

				JSONObject contacts = friend.getJSONObject("contacts");

				String phone = contacts.getString("mobile");
				String email = contacts.getString("email");
				String skype = contacts.getString("skype");


			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	} */
}
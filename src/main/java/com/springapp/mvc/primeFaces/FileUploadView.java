package com.springapp.mvc.primeFaces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.Source;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@ManagedBean(name="FileUploadView")
@RequestScoped
public class FileUploadView {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    @RequestMapping(method = RequestMethod.POST)
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            String jsonString = new String(file.getContents());
            GetListCity(jsonString);
        }
    }
    private  void GetListCity(String jsonString) {
        JSONObject dataJsonObj = null;

        try {
            dataJsonObj = new JSONObject(jsonString);
            JSONArray cityArrays=dataJsonObj.getJSONArray("city");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
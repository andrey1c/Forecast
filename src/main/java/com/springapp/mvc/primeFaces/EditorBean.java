package com.springapp.mvc.primeFaces;

/**
 * Created by home on 15.04.2016.
 */
@javax.faces.bean.ManagedBean(name = "editor")
public class EditorBean {

    private String value = "This editor is provided by PrimeFaces";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

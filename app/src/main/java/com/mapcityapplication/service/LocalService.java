package com.mapcityapplication.service;

import com.mapcityapplication.domain.Local;
import com.mapcityapplication.domain.LocalCollection;
import com.mapcityapplication.persistence.LocalDAO;

import java.util.ArrayList;

/**
 * Created by Demis e Lucas on 21/06/2015.
 */
public class LocalService {
    private LocalDAO localDAO=new LocalDAO();

    public ArrayList<Local> getLocals(String url){
        ArrayList<Local> locals;
        locals=localDAO.getLocals(url);
        if(locals!=null){
            for(Local local : locals){
                local.getWeather().get(0).setMain(convertWeatherMain(local.getWeather().get(0).getMain()));
                local.getWeather().get(0).setDescription(convertWeatherDescription(local.getWeather().get(0).getDescription()));
            }
            LocalCollection.setLocals(locals);
            return locals;
        }
        return null;
    }

    private String convertWeatherMain(String mainWeather){
        if(mainWeather.equals("Rain")){
            return "Chuva";
        }
        if(mainWeather.equals("Clouds")){
            return "Nublado";
        }
        return null;
    }

    private String convertWeatherDescription(String weatherDescription){
        if(weatherDescription.equals("broken clouds")){
            return "nuvens esparsas";
        }
        if(weatherDescription.equals("moderate rain")){
            return "chuva moderada";
        }
        if(weatherDescription.equals("light rain")){
            return "chuva leve";
        }

        return null;
    }
}

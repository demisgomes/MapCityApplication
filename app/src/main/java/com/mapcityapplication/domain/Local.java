package com.mapcityapplication.domain;

import java.util.ArrayList;

/**
 * Created by Demis e Lucas on 21/06/2015.
 */
public class Local {
    private String name;
    private Coordinate coordinate;
    private MainInfo mainInfo;
    private Wind wind;
    private Clouds clouds;
    private ArrayList<Weather> weather;

    public Local(){};

    public Local(String name, Coordinate coordinate, MainInfo mainInfo, Wind wind, Clouds clouds, ArrayList<Weather> weather) {
        this.name = name;
        this.coordinate = coordinate;
        this.mainInfo = mainInfo;
        this.wind = wind;
        this.clouds = clouds;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public MainInfo getMainInfo() {
        return mainInfo;
    }

    public void setMainInfo(MainInfo mainInfo) {
        this.mainInfo = mainInfo;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }
}

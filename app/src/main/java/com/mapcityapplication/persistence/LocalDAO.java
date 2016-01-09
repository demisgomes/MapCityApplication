package com.mapcityapplication.persistence;

import android.util.Log;

import com.mapcityapplication.domain.Clouds;
import com.mapcityapplication.domain.Coordinate;
import com.mapcityapplication.domain.Local;
import com.mapcityapplication.domain.MainInfo;
import com.mapcityapplication.domain.Weather;
import com.mapcityapplication.domain.Wind;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Demis e Lucas on 21/06/2015.
 */
public class LocalDAO {

    //método que retorna a String do JSON
    private  String getJSON(String url){
        InputStream inputStream = null;
        String result = "";
        try {
            // cria HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // dá o GET
            String openKey=URLEncoder.encode("{", "UTF-8");
            String closeKey=URLEncoder.encode("}", "UTF-8");
            String link=url;
            link=link.replace("{", openKey);
            link=link.replace("}", closeKey);
            HttpResponse httpResponse = httpclient.execute(new HttpGet(link));
            // recebe resposta no inputStream
            inputStream = httpResponse.getEntity().getContent();
            // converte inputStream para string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Not worked";

        } catch (Exception e) {
            Log.d("InputStream", e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public ArrayList<Local> getLocals(String url){
        ArrayList<Local> locals=new ArrayList<Local>();
        try {
            JSONObject jsonObject=new JSONObject(getJSON(url));
            JSONArray jsonArray=jsonObject.getJSONArray("list");
            for (int i=0; i<jsonArray.length();i++){
                Local local=getLocal(jsonArray.getJSONObject(i));
                locals.add(local);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locals;
    }

    private Local getLocal(JSONObject jsonObject) {
        Local local=new Local();
        Clouds clouds=new Clouds();
        Coordinate coordinate=new Coordinate();
        MainInfo mainInfo= new MainInfo();
        Weather weather= new Weather();
        Wind wind=new Wind();

        ArrayList<Weather> weathers=new ArrayList<Weather>();

        try {
            //seta o all dos clouds
            clouds.setAll(jsonObject.getJSONObject("clouds").getInt("all"));

            //seta as coordenadas
            coordinate.setLatitude(jsonObject.getJSONObject("coord").getDouble("lat"));
            coordinate.setLongitude(jsonObject.getJSONObject("coord").getDouble("lon"));

            //seta o vento
            wind.setDegree(jsonObject.getJSONObject("wind").getDouble("deg"));
            wind.setSpeed(jsonObject.getJSONObject("wind").getDouble("speed"));

            //seta as informações principais
            mainInfo.setGroundLevel(jsonObject.getJSONObject("main").getDouble("grnd_level"));
            mainInfo.setTemperatureMin(jsonObject.getJSONObject("main").getDouble("temp_min") - 273);
            mainInfo.setTemperature(jsonObject.getJSONObject("main").getDouble("temp") - 273);
            mainInfo.setTemperatureMax(jsonObject.getJSONObject("main").getDouble("temp_max") - 273);
            mainInfo.setHumidity(jsonObject.getJSONObject("main").getDouble("humidity"));
            mainInfo.setPressure(jsonObject.getJSONObject("main").getDouble("pressure"));
            mainInfo.setSeaLevel(jsonObject.getJSONObject("main").getDouble("sea_level"));

            //seta o tempo
            weather.setDescription(jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
            weather.setIcon(jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon"));
            weather.setMain(jsonObject.getJSONArray("weather").getJSONObject(0).getString("main"));
            weathers.add(weather);

            //seta o nome
            local.setName(jsonObject.getString("name"));

            local.setClouds(clouds);
            local.setCoordinate(coordinate);
            local.setMainInfo(mainInfo);
            local.setWeather(weathers);
            local.setWind(wind);
            return local;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

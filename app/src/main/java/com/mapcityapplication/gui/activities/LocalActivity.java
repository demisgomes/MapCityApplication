package com.mapcityapplication.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mapcityapplication.R;
import com.mapcityapplication.domain.Local;
import com.mapcityapplication.domain.LocalCollection;

public class LocalActivity extends ActionBarActivity {

    TextView textViewName, textViewTemperature,textViewTemperatureMin, textViewTemperatureMax,textViewHumidity, textViewSeaLevel, textViewGroundLevel, textViewPressure, textViewWindSpeed, textViewWindDegree, textViewLatitude, textViewLongitude,textViewTempo;
    Local local;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        textViewName=(TextView) findViewById(R.id.textViewName);
        textViewTemperature=(TextView) findViewById(R.id.textViewTemperature);
        textViewTemperatureMax=(TextView) findViewById(R.id.textViewTemperatureMax);
        textViewTemperatureMin=(TextView) findViewById(R.id.textViewTemperatureMin);
        textViewHumidity=(TextView) findViewById(R.id.textViewHumidity);
        textViewPressure=(TextView) findViewById(R.id.textViewPressure);
        textViewSeaLevel=(TextView) findViewById(R.id.textViewSeaLevel);
        textViewGroundLevel=(TextView) findViewById(R.id.textViewGroundLevel);
        textViewWindSpeed=(TextView) findViewById(R.id.textViewWindSpeed);
        textViewWindDegree=(TextView) findViewById(R.id.textViewWindDegree);
        textViewLatitude=(TextView) findViewById(R.id.textViewLatitude);
        textViewLongitude=(TextView) findViewById(R.id.textViewLongitude);
        textViewTempo=(TextView) findViewById(R.id.textViewTempo);


        Intent intent=getIntent();
        int position=intent.getIntExtra("position", 0);
        local= LocalCollection.getLocals().get(position);
        setTexts();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_local, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTexts(){
        textViewName.setText(local.getName());
        textViewTemperature.setText("Temperatura: "+local.getMainInfo().getTemperature()+"°C");
        textViewTemperatureMax.setText("Temperatura Máxima: "+local.getMainInfo().getTemperatureMax()+"°C");
        textViewTemperatureMin.setText("Temperatura Mínima: "+local.getMainInfo().getTemperatureMin()+"°C");
        textViewHumidity.setText("Umidade: "+local.getMainInfo().getHumidity()+"%");
        textViewPressure.setText("Pressão Atmosférica: "+local.getMainInfo().getPressure()+"");
        textViewSeaLevel.setText("Nível do mar: "+local.getMainInfo().getSeaLevel()+"m");
        textViewGroundLevel.setText("Nível do solo: "+local.getMainInfo().getGroundLevel()+"m");
        textViewWindSpeed.setText("Velocidade do vento: "+local.getWind().getSpeed()+"m/s");
        textViewWindDegree.setText("Direção do vento: "+local.getWind().getDegree()+" graus");
        textViewLatitude.setText("Latitude: "+local.getCoordinate().getLatitude());
        textViewLongitude.setText("Longitude: "+local.getCoordinate().getLongitude());
        textViewTempo.setText(local.getWeather().get(0).getMain()+", "+local.getWeather().get(0).getDescription());
    }
}

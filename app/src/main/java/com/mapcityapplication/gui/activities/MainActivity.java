package com.mapcityapplication.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mapcityapplication.R;


public class MainActivity extends ActionBarActivity {
    GoogleMap googleMap;
    Marker marker;
    Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relaciona o fragmento do mapa na variável o tipo GoogleMap
        googleMap= ((MapFragment) getFragmentManager().findFragmentById(
                R.id.map)).getMap();

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                googleMap.clear();
                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);
                marker= googleMap.addMarker(markerOptions);
            }
        });

        buttonSearch=(Button) findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se o marcador estiver na tela, ele passa para a tela de listas
                if(marker!=null){
                    Intent intent=new Intent(MainActivity.this, ListLocalsActivity.class);
                    startActivity(intent);
                }

                //caso ele não clicou no marcador, aparecerá um Toast indicando que ele deve clicar em qualquer lugar da tela
                else{
                    Toast.makeText(getBaseContext(),"Você ainda não clicou em algum local do mapa", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

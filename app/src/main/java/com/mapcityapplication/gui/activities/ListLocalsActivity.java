package com.mapcityapplication.gui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mapcityapplication.R;
import com.mapcityapplication.domain.Local;
import com.mapcityapplication.gui.adapters.ListLocalsAdapter;
import com.mapcityapplication.service.LocalService;

import java.util.ArrayList;


public class ListLocalsActivity extends ActionBarActivity {
    ListView listViewLocals;
    ArrayList<Local> listLocals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_locals);

        listViewLocals = (ListView) findViewById(R.id.listViewLocals);
        new LocalAsyncTask().execute("http://api.openweathermap.org/data/2.5/find?lat={LAT}&lon={LON}&cnt=15");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_cities, menu);
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

    private class LocalAsyncTask extends AsyncTask<String, Integer, ArrayList<Local>>{

        @Override
        protected ArrayList<Local> doInBackground(String... strings) {
            LocalService localService=new LocalService();
            return localService.getLocals(strings[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Local> locals) {
            if(locals!=null){
                listLocals=locals;
                ArrayAdapter arrayAdapter = new ListLocalsAdapter(getBaseContext(), R.layout.list_locals_adapter, listLocals);
                listViewLocals.setAdapter(arrayAdapter);
                listViewLocals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent= new Intent(ListLocalsActivity.this, LocalActivity.class);
                        intent.putExtra("position", i);
                        startActivity(intent);
                    }
                });
            }

            else{
                Toast.makeText(getBaseContext(),"Não foi possível se comunciar com o servidor",Toast.LENGTH_LONG).show();
            }
        }
    }
}

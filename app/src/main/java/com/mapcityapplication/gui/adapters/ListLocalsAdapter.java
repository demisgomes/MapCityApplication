package com.mapcityapplication.gui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mapcityapplication.R;
import com.mapcityapplication.domain.Local;

import java.util.List;

/**
 * Created by Demis e Lucas on 21/06/2015.
 */
public class ListLocalsAdapter extends ArrayAdapter<Local>{

    private final LayoutInflater inflater;
    private int resourceId;

    public ListLocalsAdapter(Context context, int resource, List<Local> locals) {
        super(context, resource, locals);
        this.inflater=LayoutInflater.from(context);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(resourceId, parent, false);

        //relaciona com o xml
        TextView textView=(TextView) convertView.findViewById(R.id.textViewLocal);
        Local local=getItem(position);

        //seta os campos
        textView.setText(local.getName());
        return convertView;
    }

}

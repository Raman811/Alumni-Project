package com.example.alumini;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Arrayadapter_new extends ArrayAdapter<Dataholder> {
Context context;
    List<Dataholder> dataholders;

    public Arrayadapter_new(Context context, List<Dataholder> dataholders) {
        super(context, 0,dataholders);
        this.dataholders = dataholders;
        this.context=context;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getView(position,convertView,parent);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
       view=LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_layout,parent,false);
        TextView textView=view.findViewById(R.id.spinner_textview);
        textView.setText(dataholders.get(position).getName());
        return view;
    }
}

package com.tanapakorlaop.shoppingmama2;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tanapakor.laop on 31/8/2558.
 */
public class MonthAdapter extends ArrayAdapter<SaveMonth> {
    private Context context;
    private int resource;
    private ArrayList<SaveMonth> saveMonth = null;

    public MonthAdapter(Context context, int resource, ArrayList<SaveMonth> saveMonth) {
        super(context, resource, saveMonth);
        this.context = context;
        this.resource = resource;
        this.saveMonth = saveMonth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View month = inflater.inflate(resource, parent, false);

        ImageView monthImage = (ImageView)month.findViewById(R.id.monthImage);
        TextView sumPrice = (TextView)month.findViewById(R.id.sumPriceText);
        TextView date = (TextView)month.findViewById(R.id.dateText);
        TextView numList = (TextView)month.findViewById(R.id.listCountText);

        SaveMonth monthly = saveMonth.get(position);
        sumPrice.setText(monthly.getSumPrice());
        date.setText(monthly.getDate());
        numList.setText(monthly.getListed());
        Log.i("hello", String.valueOf(monthly.getSumPrice()));

        return month;
    }
}

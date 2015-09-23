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
public class MonthAdapter extends ArrayAdapter<ShoppingMamaDB> {
    private Context context;
    private int resource;
    private ArrayList<ShoppingMamaDB> shoppingMamaDB = null;
    private int sumPrice = 0;

    public MonthAdapter(Context context, int resource, ArrayList<ShoppingMamaDB> shoppingMamaDB) {
        super(context, resource, shoppingMamaDB);
        this.context = context;
        this.resource = resource;
        this.shoppingMamaDB = shoppingMamaDB;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View month = inflater.inflate(resource, parent, false);

        ImageView monthImage = (ImageView)month.findViewById(R.id.monthImage);
        TextView tableName = (TextView)month.findViewById(R.id.sumPriceText);
        TextView sumPrice = (TextView)month.findViewById(R.id.dateText);
        TextView numList = (TextView)month.findViewById(R.id.listCountText);

        ShoppingMamaDB monthly = shoppingMamaDB.get(position);
        tableName.setText(monthly.getTableName());
        sumPrice.setText(monthly.getSumPrice()+" Baht");
        //sumPrice.setText("sumPrice");
        numList.setText(monthly.getListed()+" Listed");
        //numList.setText("0 Listed");
        return month;
    }


}

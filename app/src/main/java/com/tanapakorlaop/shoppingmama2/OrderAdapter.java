package com.tanapakorlaop.shoppingmama2;

import android.app.Activity;
import android.content.Context;
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
public class OrderAdapter extends ArrayAdapter<ShoppingMamaDB> {
    private Context context;
    private int resource;
    private ArrayList<ShoppingMamaDB> orderDetail = null;

    public OrderAdapter(Context context, int resource, ArrayList<ShoppingMamaDB> shoppingMamaDBs) {
        super(context, resource, shoppingMamaDBs);
        this.context = context;
        this.resource = resource;
        this.orderDetail = shoppingMamaDBs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View mOrders = inflater.inflate(resource, parent, false);

        ImageView orderImage = (ImageView)mOrders.findViewById(R.id.orderImage);
        TextView orderPrice = (TextView)mOrders.findViewById(R.id.orderPrice);
        TextView orderName = (TextView)mOrders.findViewById(R.id.orderName);

        ShoppingMamaDB orders = orderDetail.get(position);
        orderPrice.setText(orders.getOrderPrice());
        orderName.setText(orders.getOrderName());

        return mOrders;
    }
}
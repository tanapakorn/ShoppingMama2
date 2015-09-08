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
public class OrderAdapter extends ArrayAdapter<OrderDetail> {
    private Context context;
    private int resource;
    private ArrayList<OrderDetail> orderDetail = null;

    public OrderAdapter(Context context, int resource, ArrayList<OrderDetail> orderDetail) {
        super(context, resource, orderDetail);
        this.context = context;
        this.resource = resource;
        this.orderDetail = orderDetail;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View mOrders = inflater.inflate(resource, parent, false);

        ImageView orderImage = (ImageView)mOrders.findViewById(R.id.orderImage);
        TextView orderPrice = (TextView)mOrders.findViewById(R.id.orderPrice);
        TextView orderName = (TextView)mOrders.findViewById(R.id.orderName);

        OrderDetail orders = orderDetail.get(position);
        orderPrice.setText(orders.getOrderPrice());
        orderName.setText(orders.getOrderName());

        return mOrders;
    }
}
package com.tanapakorlaop.shoppingmama2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by tanapakor.laop on 4/9/2558.
 */
public class MyDialog extends DialogFragment {
    Button pickBtn,dropBtn;
    EditText title,price;
    private ArrayList<ShoppingMamaDB> dialogList = new ArrayList<>();
    DBHelper mHelper;
    int position = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.custom_dialog_layout,null);

        mHelper = new DBHelper(getActivity(),ItemsAll.DB_NAME,null,ItemsAll.DB_VERSION);
        pullDialogList();
        final String month = ((CreateListActivity)getActivity()).table;
        title = (EditText)view.findViewById(R.id.titleText);
        price = (EditText)view.findViewById(R.id.messageText);
        pickBtn = (Button)view.findViewById(R.id.pickBtn);
        if(dialogList.size() > 1) {
            title.setText(dialogList.get(position).getOrderName());
            price.setText(dialogList.get(position).getOrderPrice());
        }else{
            title.setText(null);
            title.setHint("Create New Order");
            price.setText(null);
            price.setHint("Price");
            title.setEnabled(true);
            price.setEnabled(true);
        }


        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** add new order to database */
                ShoppingMamaDB orderDetail = new ShoppingMamaDB(title.getText().toString(),price.getText().toString());
                orderDetail.set_id(dialogList.get(position).get_id());

                /** If it's normal order */
                if(position < dialogList.size()-2) {
                    position++;
                    String nextTitle = dialogList.get(position).getOrderName();
                    String nextPrice = dialogList.get(position).getOrderPrice();
                    Toast.makeText(getActivity(),"Added \""+title.getText()+"\" to the list",Toast.LENGTH_LONG).show();
                    title.setText(nextTitle);
                    price.setText(nextPrice);
                /** If it's Last order " create new " */
                }else if(position < dialogList.size()-1) {
                    position++;
                    //String nextTitle = dialogList.get(position).getOrderName();
                    //String nextPrice = dialogList.get(position).getOrderPrice();
                    Toast.makeText(getActivity(),"Added \""+title.getText()+"\" to the list", Toast.LENGTH_LONG).show();
                    title.setText(null);
                    title.setHint("Create New Order");
                    price.setText(null);
                    price.setHint("Price");
                    title.setEnabled(true);
                    price.setEnabled(true);
                }else{
                    mHelper.addNewOrder(title.getText().toString(), price.getText().toString());
                    Toast.makeText(getActivity(),"Added \""+title.getText()+"\" to the list", Toast.LENGTH_LONG).show();
                    if(dialogList.get(position).get_id()==0){
                        Log.e("hello","yes");
                        int newId = mHelper.getNewOrderId(title.getText().toString());
                        orderDetail.set_id(newId);
                        Log.e("hello", String.valueOf(newId));
                    }
                    title.setText(null);
                    price.setText(null);
                }
                ((CreateListActivity) getActivity()).addOrders(month, orderDetail);
            }
        });
        dropBtn = (Button)view.findViewById(R.id.dropBtn);
        dropBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < dialogList.size() - 2) {
                    position++;
                    String nextTitle = dialogList.get(position).getOrderName();
                    String nextPrice = dialogList.get(position).getOrderPrice();
                    title.setText(nextTitle);
                    price.setText(nextPrice);
                    /** If it's Last order " create new " */
                } else if (position < dialogList.size() - 1) {
                    position++;
                    String nextTitle = dialogList.get(position).getOrderName();
                    String nextPrice = dialogList.get(position).getOrderPrice();
                    title.setText(nextTitle);
                    price.setText(nextPrice);
                    title.setEnabled(true);
                    price.setEnabled(true);
                } else {
                    dismiss();
                }
            }
        });
        return view;
    }
    private void pullDialogList(){
        dialogList = mHelper.getDialogList();
        ArrayList<ShoppingMamaDB> orders = ((CreateListActivity)getActivity()).orders;
        for(int l = 0 ; l < orders.size()-1 ; l++) {
            for(int i = 0 ; i < dialogList.size() ; i++){
                if (dialogList.get(i).get_id() == orders.get(l).getOrder_id()) {
                    dialogList.remove(i);
                }
            }
        }
        ShoppingMamaDB newOrder = new ShoppingMamaDB("Create New Order","Price");
        dialogList.add(dialogList.size(), newOrder);

    }

}

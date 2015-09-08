package com.tanapakorlaop.shoppingmama2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tanapakor.laop on 4/9/2558.
 */
public class MyDialog extends DialogFragment {
    Button pickBtn,dropBtn;
    TextView title,price;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.custom_dialog_layout,null);
        title = (TextView)view.findViewById(R.id.titleText);
        price = (TextView)view.findViewById(R.id.messageText);
        pickBtn = (Button)view.findViewById(R.id.pickBtn);
        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence nextTitle = title.getText()+"2";
                CharSequence nextPrice = title.getText()+"2";
                Toast.makeText(getActivity(),"Added \"title\" to the list",Toast.LENGTH_LONG).show();
                title.setText(nextTitle);
                price.setText(nextPrice);
                OrderDetail newOrder = new OrderDetail(nextPrice.toString(),nextTitle.toString(),android.R.drawable.ic_input_add);
                ((CreateListActivity) getActivity()).addOrders(newOrder);
            }
        });
        dropBtn = (Button)view.findViewById(R.id.dropBtn);
        dropBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }
}

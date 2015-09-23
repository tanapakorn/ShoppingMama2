package com.tanapakorlaop.shoppingmama2;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class CreateListActivity extends FragmentActivity implements MainFragment.OnFragmentInteractionListener
        ,OrderListFragment.OnFragmentInteractionListener{

    public  ArrayList<ShoppingMamaDB> monthly = new ArrayList<>();

    ArrayList<ShoppingMamaDB> orders = new ArrayList<>();
    private ArrayList<OrderDetail> dialogOrders = new ArrayList<>();
    FrameLayout frameLayout;
    OrderListFragment orderListFragment;
    DBHelper mHelper;
    String table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);
        Intent i = getIntent();
        table = i.getStringExtra("table");
        mHelper = new DBHelper(this,ItemsAll.DB_NAME,null,ItemsAll.DB_VERSION);

        pullOrders();

        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        Button button = (Button)findViewById(R.id.addBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDialog();
            }
        });

        orderListFragment = new OrderListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, orderListFragment);
        fragmentTransaction.commit();
    }

    private void pullOrders(){
        orders = mHelper.getOrdersList(table);
        for(int i = 0 ; i<orders.size()-1 ; i++){
            Log.i("aaa", String.valueOf(orders.get(i).getOrder_id())+" name = " +String.valueOf(orders.get(i).getOrderName()));
        }
    }
    public ArrayList<ShoppingMamaDB> getOrders() {
        return orders;
    }
    public ArrayList<OrderDetail> getDialogOrders() {
        return dialogOrders;
    }
    /*public void addOrders(OrderDetail newOrder) {
        orders.add(0, newOrder);
        orderListFragment.orderAdapter.notifyDataSetChanged();
        mHelper.insertOrder(newOrder.getOrderName(),newOrder.getOrderPrice());
    }*/
    public void addOrders(String month,ShoppingMamaDB newOrder) {
        orders.add(0, newOrder);
        orderListFragment.orderAdapter.notifyDataSetChanged();
        mHelper.insertOrder(month,newOrder.get_id(),newOrder.getOrderName(),newOrder.getOrderPrice());
    }
    public void removeOrders(String month,String name) {
        mHelper.deleteOrder(month, name);
        orderListFragment.orderAdapter.notifyDataSetChanged();
    }
    public void makeDialog(){
        final MyDialog myDialog = new MyDialog();
        myDialog.show(getSupportFragmentManager(),"MyList");
    }

    public ArrayList<ShoppingMamaDB> getMonthly() {
        return monthly;
    }

    public void setMonthly(ArrayList<ShoppingMamaDB> monthly) {
        this.monthly = monthly;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_list, menu);
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

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

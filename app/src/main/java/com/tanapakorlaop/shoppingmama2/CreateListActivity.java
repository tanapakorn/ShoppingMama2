package com.tanapakorlaop.shoppingmama2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.tanapakorlaop.shoppingmama2.dummy.DummyContent;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CreateListActivity extends FragmentActivity implements MainFragment.OnFragmentInteractionListener
        ,OrderListFragment.OnFragmentInteractionListener{

    private ArrayList<OrderDetail> orders = new ArrayList<>();
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

        final MyDialog myDialog = new MyDialog();
        pullOrders();

        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);
        Button button = (Button)findViewById(R.id.addBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.show(getSupportFragmentManager(),"MyList");
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
    }
    public ArrayList<OrderDetail> getOrders() {
        return orders;
    }
    public void addOrders(OrderDetail newOrder) {
        orders.add(0, newOrder);
        orderListFragment.orderAdapter.notifyDataSetChanged();
        mHelper.insertOrder(newOrder.getOrderName(),newOrder.getOrderPrice());
    }
    public void removeOrders(String name) {
        mHelper.deleteOrder(name);
        orderListFragment.orderAdapter.notifyDataSetChanged();
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

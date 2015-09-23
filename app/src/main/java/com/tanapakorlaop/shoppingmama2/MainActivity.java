package com.tanapakorlaop.shoppingmama2;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends FragmentActivity implements MonthListFragment.OnFragmentInteractionListener,
        OrderListFragment.OnFragmentInteractionListener {

    private static final int CREATE_LIST = 0;

    public ArrayList<ShoppingMamaDB> shoppingMamaDBs = new ArrayList<>();
    public ArrayList<ShoppingMamaDB> monthly = new ArrayList<>();

    private String[] monthTable = {"january","february",
            "march", "april","may","june","july","august",
            "september","october","november","december"};
    MonthListFragment monthListFragment;
    DBHelper mHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DBHelper(this,ItemsAll.DB_NAME,null,ItemsAll.DB_VERSION);
        //pullDB();
        pullMonthly();
        Button createBtn = (Button)findViewById(R.id.create_btn);

        monthListFragment = new MonthListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, monthListFragment);
        fragmentTransaction.commit();

        createBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createNewList();
            }
        });

    }
    public void pullDB(){
        shoppingMamaDBs = mHelper.getAllData();
    }
    public void pullMonthly(){
        monthly = mHelper.getMonthList();
    }

    public void createNewList(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat mmFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        String table = getCurrentDate(dateFormat.format(date));

        if(!table.equals(monthly.get(0).getTableName())) {
            ShoppingMamaDB newMonth = new ShoppingMamaDB(table);
            monthly.add(0, newMonth);
            monthListFragment.monthAdapter.notifyDataSetChanged();
        }
        //String table = getCurrentMonth(mmFormat.format(date));
        //String month = table + String.valueOf(monthly.size());
        //String table = getCurrentMonth(mmFormat.format(date))+String.valueOf(monthly.size());
        /*mHelper.createNewTable(table);
        ShoppingMamaDB newMonth = new ShoppingMamaDB(month,dateFormat.format(date),"0 Listed",android.R.drawable.ic_input_add);
        newMonth.setMonth(month);
        monthly.add(0, newMonth);
        monthListFragment.monthAdapter.notifyDataSetChanged();
        mHelper.insertNewMonth(dateFormat.format(date), month);*/
        //mHelper.insertNewMonth(dateFormat.format(date),table);
        //editList(table);
        editList(table);
    }
    public void editList(String table){

        Intent intent = new  Intent(getApplicationContext(),CreateListActivity.class);
        //intent.putExtra("table",table);
        intent.putExtra("table", table);
        startActivity(intent);
    }
    public ArrayList<ShoppingMamaDB> getMonthly() {
        return monthly;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public String getCurrentMonth(String mm){
        if(mm.length() >2){
            mm.substring(3,5);
        }
        switch(mm){
            case "01":
                return monthTable[0];

            case "02":
                return monthTable[1];
            case "03":
                return monthTable[2];
            case "04":
                return monthTable[3];
            case "05":
                return monthTable[4];
            case "06":
                return monthTable[5];
            case "07":
                return monthTable[6];
            case "08":
                return monthTable[7];
            case "09":
                return monthTable[8];
            case "10":
                return monthTable[9];
            case "11":
                return monthTable[10];
            case "12":
                return monthTable[11];
            default:
                return "unknown";
        }
    }
    public String getCurrentDate(String dmy){
        String dmyMonth = dmy.substring(3,5);
        String newFormat;
        switch(dmyMonth){
            case "01":
                newFormat = dmy.substring(0,2)+" January "+dmy.substring(6,10);
                return newFormat;
            case "02":
                newFormat = dmy.substring(0,2)+" February "+dmy.substring(6,10);
                return newFormat;
            case "03":
                newFormat = dmy.substring(0,2)+" March "+dmy.substring(6,10);
                return newFormat;
            case "04":
                newFormat = dmy.substring(0,2)+" April "+dmy.substring(6,10);
                return newFormat;
            case "05":
                newFormat = dmy.substring(0,2)+" May "+dmy.substring(6,10);
                return newFormat;
            case "06":
                newFormat = dmy.substring(0,2)+" June "+dmy.substring(6,10);
                return newFormat;
            case "07":
                newFormat = dmy.substring(0,2)+" July "+dmy.substring(6,10);
                return newFormat;
            case "08":
                newFormat = dmy.substring(0,2)+" August "+dmy.substring(6,10);
                return newFormat;
            case "09":
                newFormat = dmy.substring(0,2)+" September "+dmy.substring(6,10);
                return newFormat;
            case "10":
                newFormat = dmy.substring(0,2)+" October "+dmy.substring(6,10);
                return newFormat;
            case "11":
                newFormat = dmy.substring(0,2)+" November "+dmy.substring(6,10);
                return newFormat;
            case "12":
                newFormat = dmy.substring(0,2)+" December "+dmy.substring(6,10);
                return newFormat;
            default:
                return "unknown";
        }
    }

}

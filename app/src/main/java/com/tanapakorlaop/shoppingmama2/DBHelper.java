package com.tanapakorlaop.shoppingmama2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tanapakor.laop on 4/9/2558.
 */
public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String CREATE_ALL_ITEMS_TABLE = "CREATE TABLE all_items ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "first_name TEXT, last_name TEXT, tel TEXT, email TEXT, description TEXT)";*/

        String CREATE_ALL_ITEMS_TABLE = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s TEXT, %s TEXT, %s INTEGER)",
                ItemsAll.TABLE,
                ItemsAll.Column.ID,
                ItemsAll.Column.ORDER_NAME,
                ItemsAll.Column.ORDER_PRICE,
                ItemsAll.Column.ORDER_IMAGE);

        String CREATE_ALL_MONTH_TABLE = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT,%s TEXT,%s TEXT,%s TEXT,%s INTEGER)",
                "all_month",
                "_id",
                "month",
                "date",
                "price",
                "listed",
                "image");
        db.execSQL(CREATE_ALL_ITEMS_TABLE);
        db.execSQL(CREATE_ALL_MONTH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_ALL_ITEMS_TABLE = "DROP TABLE IF EXISTS " + ItemsAll.TABLE;
        db.execSQL(DROP_ALL_ITEMS_TABLE);
        onCreate(db);
    }
    public ArrayList<SaveMonth> getMonthList(){
        SaveMonth saveMonths;
        ArrayList<SaveMonth> newList = new ArrayList<>();
        newList.add(new SaveMonth("Create New List", "dd/mm/yyyy", " 0 Listed", android.R.drawable.ic_input_add));


        sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM `all_month`  ORDER BY `_id` ASC;";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            saveMonths = new SaveMonth(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(4),
                    android.R.drawable.ic_input_add);
            saveMonths.set_id(cursor.getInt(0));
            saveMonths.setMonth(cursor.getString(1));
            newList.add(0, saveMonths);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();

        return newList;
    }
    public ArrayList<OrderDetail> getOrdersList(String table){
        ArrayList<OrderDetail> newList = new ArrayList<>();
        OrderDetail orderDetail;

        sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM `"+table+"`  ORDER BY `_id` ASC;";


        newList.add(new OrderDetail("orderName","orderPrice",android.R.drawable.ic_input_add));
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()) {
            orderDetail = new OrderDetail(
                    cursor.getString(1),
                    cursor.getString(2),
                    android.R.drawable.ic_input_add
            );
            newList.add(0, orderDetail);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();

        return newList;
    }

    public void insertOrder(String name,String price){
        sqLiteDatabase = this.getWritableDatabase();
        String sql = "INSERT  INTO `all_items`(`name`,`price`) VALUES ('"+
                name+"','"+
                price+"');";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }
    public void deleteOrder(String name){
        sqLiteDatabase = this.getWritableDatabase();
        String sql = "DELETE FROM all_items WHERE name = \""+name+"\";";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }
    public void createNewTable(String month){
        sqLiteDatabase = this.getWritableDatabase();
        ArrayList<String> counter = new ArrayList<>();
        //String counter;
        String sqlDuplicate = "SELECT _id FROM all_month WHERE month >= \""+month+"\" ORDER BY _id DESC;";
        Cursor cursorName = sqLiteDatabase.rawQuery(sqlDuplicate,null);
        if (cursorName != null) {
            cursorName.moveToFirst();
        }
        //counter = String.valueOf(cursorName.getLong(1));
        while(!cursorName.isAfterLast()) {
            counter.add(String.valueOf(cursorName.getLong(0)));
            cursorName.moveToNext();
        }
        if(!isTableExists(month+"1",sqLiteDatabase.isOpen())) {
            //String CREATE_NEW_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    String CREATE_NEW_TABLE = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%s TEXT, %s TEXT, %s INTEGER)",
                    month+"1",
                    ItemsAll.Column.ID,
                    ItemsAll.Column.ORDER_NAME,
                    ItemsAll.Column.ORDER_PRICE,
                    ItemsAll.Column.ORDER_IMAGE);

            sqLiteDatabase.execSQL(CREATE_NEW_TABLE);
        }else{
            String CREATE_NEW_TABLE = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%s TEXT, %s TEXT, %s INTEGER)",
                    month+String.valueOf(Integer.parseInt(counter.get(0))+1),
                    ItemsAll.Column.ID,
                    ItemsAll.Column.ORDER_NAME,
                    ItemsAll.Column.ORDER_PRICE,
                    ItemsAll.Column.ORDER_IMAGE);
            sqLiteDatabase.execSQL(CREATE_NEW_TABLE);
        }
        sqLiteDatabase.close();
    }
    public void insertNewMonth(String date,String month){
        sqLiteDatabase = this.getWritableDatabase();

        String price = "0 baht";
        String listed = "0 listed";
        String sql = "INSERT  INTO `all_month`(`month`,`date`,`price`,`listed`) VALUES ('"+
                month+"','"+
                date+"','"+
                price+"','"+
                listed+"');";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }
    public boolean isTableExists(String tableName, boolean openDb) {
        if(openDb) {
            if(sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
                sqLiteDatabase = getReadableDatabase();
            }

            if(!sqLiteDatabase.isReadOnly()) {
                sqLiteDatabase.close();
                sqLiteDatabase = getReadableDatabase();
            }
        }

        Cursor cursor = sqLiteDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }
}

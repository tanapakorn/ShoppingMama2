package com.tanapakorlaop.shoppingmama2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
            "%s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s INTEGER)",
                ItemsAll.TABLE,
                ItemsAll.Column.ID,
                ItemsAll.Column.TABLE_NAME,
                "order_id",
                ItemsAll.Column.ORDER_NAME,
                ItemsAll.Column.ORDER_PRICE,
                ItemsAll.Column.ORDER_IMAGE);

        String CREATE_ORDERS_TABLE = String.format("CREATE TABLE orders ( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, %s TEXT)",
                "order_id",
                "name",
                "price");

        db.execSQL(CREATE_ALL_ITEMS_TABLE);
        db.execSQL(CREATE_ORDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_ALL_ITEMS_TABLE = "DROP TABLE IF EXISTS " + ItemsAll.TABLE;
        db.execSQL(DROP_ALL_ITEMS_TABLE);
        onCreate(db);
    }
    public ArrayList<ShoppingMamaDB> getAllData(){
        ShoppingMamaDB saveMonths;
        ArrayList<ShoppingMamaDB> newList = new ArrayList<>();
        //newList.add(new ShoppingMamaDB("Create New List"));

        sqLiteDatabase = this.getWritableDatabase();

        String sql = "SELECT * FROM all_items" ;

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            saveMonths = new ShoppingMamaDB(
                    cursor.getString(1),
                    cursor.getString(2));
            saveMonths.setOrderPrice(cursor.getString(3));
            saveMonths.set_id(cursor.getInt(0));
            //saveMonths.set_id(cursor.getInt(1));
            //saveMonths.setOrder_id(cursor.getInt(2));
            newList.add(0, saveMonths);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();

        return newList;
    }
    /*public ArrayList<ShoppingMamaDB> getMonthList(){
        ShoppingMamaDB saveMonths;
        ArrayList<ShoppingMamaDB> newList = new ArrayList<>();
        newList.add(new ShoppingMamaDB("Create New List"));

        sqLiteDatabase = this.getWritableDatabase();

        String sql = "SELECT distinct month FROM all_items" ;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            saveMonths = new ShoppingMamaDB(
                    cursor.getString(0));
            //saveMonths.set_id(cursor.getInt(1));
            //saveMonths.setOrder_id(cursor.getInt(2));
            newList.add(0, saveMonths);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();

        return newList;
    }*/
    public ArrayList<ShoppingMamaDB> getMonthList(){
        ShoppingMamaDB saveMonths;
        int sumPrice = 0;
        int listed = 0;
        ArrayList<String> tableList = new ArrayList<>();
        ArrayList<ShoppingMamaDB> newList = new ArrayList<>();
        newList.add(new ShoppingMamaDB("Create New List"));

        sqLiteDatabase = this.getWritableDatabase();

        String sql = "SELECT distinct month FROM all_items" ;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            tableList.add(0,cursor.getString(0));
            cursor.moveToNext();
        }
        for(int i = 0; i < tableList.size() ; i++){
            String sql2 = "SELECT price FROM all_items WHERE month = \""+tableList.get(i)+"\"" ;
            listed = 0;
            sumPrice = 0;
            cursor = sqLiteDatabase.rawQuery(sql2,null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            while (!cursor.isAfterLast()) {
                sumPrice += Integer.parseInt(cursor.getString(0));
                listed++;
                cursor.moveToNext();
            }
            saveMonths = new ShoppingMamaDB(tableList.get(i));
            saveMonths.setSumPrice(String.valueOf(sumPrice));
            saveMonths.setListed(String.valueOf(listed));
            newList.add(0,saveMonths);
        }
        sqLiteDatabase.close();

        return newList;
    }
    public ArrayList<ShoppingMamaDB> getOrdersList(String table){
        ArrayList<ShoppingMamaDB> newList = new ArrayList<>();
        ShoppingMamaDB orderDetail;

        //ArrayList<ShoppingMamaDB> monthsList = new ArrayList<>();
        ArrayList<Integer> order_id = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
       // String sql = "SELECT * FROM all_month WHERE `"+table+"`  ORDER BY `_id` ASC;";
        String sql = "SELECT order_id,name,price FROM all_items WHERE month = \""+table+"\"";
        //String sql2 = "SELECT * FROM all_items";

        newList.add(new ShoppingMamaDB("orderName", "orderPrice"));
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()) {
            orderDetail = new ShoppingMamaDB(
                    cursor.getString(1),
                    cursor.getString(2)
            );
            orderDetail.setOrder_id(cursor.getInt(0));
            newList.add(0, orderDetail);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();

        return newList;
    }
    public ArrayList<ShoppingMamaDB> getDialogList(){
        ArrayList<ShoppingMamaDB> newList = new ArrayList<>();
        ShoppingMamaDB newOrder;
        sqLiteDatabase = this.getWritableDatabase();

        String sql = "SELECT * FROM orders";
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while(!cursor.isAfterLast()) {
            newOrder = new ShoppingMamaDB(
                    cursor.getString(1),
                    cursor.getString(2)
            );
            newOrder.set_id(cursor.getInt(0));
            newList.add(0, newOrder);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return newList;
    }
    public void insertOrder(String month,int orderId,String name,String price){
        sqLiteDatabase = this.getWritableDatabase();
        String sql = "INSERT  INTO `all_items`(`month`,`order_id`,`name`,`price`) VALUES ('"+
                month+"','"+
                orderId+"','"+
                name+"','"+
                price+"');";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }
    public void deleteOrder(String month,String name){
        sqLiteDatabase = this.getWritableDatabase();
        String sql = "DELETE FROM all_items WHERE name = \""+name+"\" AND month = \""+month+"\";";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }
    public void addNewOrder(String orderName,String orderPrice){
        sqLiteDatabase = this.getWritableDatabase();
        String sql = "INSERT INTO orders (name,price) VALUES (\""+orderName+"\",\""+orderPrice+"\");";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
    }
    public Integer getNewOrderId(String orderName){
        int order_id = 0;

        sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT order_id FROM orders WHERE name = \""+orderName+"\"";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            order_id = cursor.getInt(0);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return order_id;
    }
    /*public void createNewTable(String month){
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
    }*/
    /*public void insertNewMonth(String date,String month){
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
    }*/
    public void insertNewMonth(String month,String date){
        sqLiteDatabase = this.getWritableDatabase();
        int order_id = 0;
        String sql = "INSERT  INTO `all_month`(`month`,`order_id`) VALUES ('"+
                month+"','"+
                order_id+"');";
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

package com.tanapakorlaop.shoppingmama2;

/**
 * Created by tanapakor.laop on 31/8/2558.
 */
public class ShoppingMamaDB {
    private int _id,order_id;
    private String tableName;
    private String orderName;
    private String orderPrice,sumPrice,listed;
    private int monthPic = android.R.drawable.ic_input_add;

    /*public ShoppingMamaDB(String tableName, String orderName) {
        this.tableName = tableName;
        this.orderName = orderName;
    }*/
    public ShoppingMamaDB(String tableName) {
        this.tableName = tableName;
    }
    public ShoppingMamaDB(String orderName,String orderPrice) {
        this.orderName = orderName;
        this.orderPrice = orderPrice;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getMonthPic() {
        return monthPic;
    }

    public void setMonthPic(int monthPic) {
        this.monthPic = monthPic;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getListed() {
        return listed;
    }

    public void setListed(String listed) {
        this.listed = listed;
    }
}

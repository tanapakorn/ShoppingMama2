package com.tanapakorlaop.shoppingmama2;

/**
 * Created by tanapakor.laop on 31/8/2558.
 */
public class OrderDetail {
    private int _id;
    private String orderName;
    private String orderPrice;
    private int orderPic;

    public OrderDetail(String orderName,String orderPrice, int orderPic) {
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderPic = orderPic;
    }
    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getOrderPic() {
        return orderPic;
    }

    public void setOrderPic(int orderPic) {
        this.orderPic = orderPic;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
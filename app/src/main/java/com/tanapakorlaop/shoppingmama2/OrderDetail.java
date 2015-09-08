package com.tanapakorlaop.shoppingmama2;

/**
 * Created by tanapakor.laop on 31/8/2558.
 */
public class OrderDetail {

    private String orderPrice;
    private String orderName;
    private int orderPic;
    private int _id;
    public OrderDetail(String orderName,String orderPrice, int orderPic) {
        this.orderPrice = orderPrice;
        this.orderName = orderName;
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
package com.tanapakorlaop.shoppingmama2;

/**
 * Created by tanapakor.laop on 31/8/2558.
 */
public class SaveMonth {
    private int _id;
    private String month;
    private String sumPrice;
    private String date;
    private String listed;
    private int monthPic;

    public SaveMonth(String sumPrice, String date, String listed, int monthPic) {
        //this._id = _id;
        this.sumPrice = sumPrice;
        this.date = date;
        this.listed = listed;
        this.monthPic = monthPic;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getListed() {
        return listed;
    }

    public void setListed(String listed) {
        this.listed = listed;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(String sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMonthPic() {
        return monthPic;
    }

    public void setMonthPic(int monthPic) {
        this.monthPic = monthPic;
    }


}

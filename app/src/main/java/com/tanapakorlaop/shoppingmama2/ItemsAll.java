package com.tanapakorlaop.shoppingmama2;

import android.provider.BaseColumns;

/**
 * Created by tanapakor.laop on 4/9/2558.
 */
public class ItemsAll {
    public static final String DB_NAME = "shopping_mama2DB";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "all_items";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String ORDER_NAME = "name";
        public static final String ORDER_PRICE = "price";
        public static final String ORDER_IMAGE = "image";

    }
}

package com.example.liujishun.mysamples.test.Entity;

/**
 * Created by liujishun on 16/3/28.
 */
public  class GoodsListEntity {
    private int id;
    private String img_url;
    private String price_type;
    private int goods_price;
    private double store_price;
    private int userId;
    private int goods_inventory;
    private String goods_name;
    private int goods_salenum;
    private int recommend_index;
    private String discount;

    public void setId(int id) {
        this.id = id;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setPrice_type(String price_type) {
        this.price_type = price_type;
    }

    public void setGoods_price(int goods_price) {
        this.goods_price = goods_price;
    }

    public void setStore_price(double store_price) {
        this.store_price = store_price;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGoods_inventory(int goods_inventory) {
        this.goods_inventory = goods_inventory;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setGoods_salenum(int goods_salenum) {
        this.goods_salenum = goods_salenum;
    }

    public void setRecommend_index(int recommend_index) {
        this.recommend_index = recommend_index;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getPrice_type() {
        return price_type;
    }

    public int getGoods_price() {
        return goods_price;
    }

    public double getStore_price() {
        return store_price;
    }

    public int getUserId() {
        return userId;
    }

    public int getGoods_inventory() {
        return goods_inventory;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public int getGoods_salenum() {
        return goods_salenum;
    }

    public int getRecommend_index() {
        return recommend_index;
    }

    public String getDiscount() {
        return discount;
    }
}
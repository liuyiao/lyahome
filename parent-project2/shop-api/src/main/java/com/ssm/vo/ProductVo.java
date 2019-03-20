package com.ssm.vo;

import com.ssm.pojo.Product;

public class ProductVo extends Product {
    private String brandName;//品牌名字
    private String picUrl;//商品图片url

    public ProductVo() {
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "brandName='" + brandName + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}

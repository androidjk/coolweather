package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by asus1 on 2017/11/1.
 */

public class City extends DataSupport {
    private int id;
    private String cityName;
    private int cityCode;
    private int provinceId;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getCityName(){
        return cityName;
    }
    public void setCityName(String name){
        this.cityName=name;
    }
    public int getCityCode(){
        return cityCode;
    }
    public void setCityCode(int code){
        this.cityCode=code;
    }
    public int getProvinceId(){
        return provinceId;
    }
    public void setProvinceId(int provinceId){
        this.provinceId=provinceId;
    }
}

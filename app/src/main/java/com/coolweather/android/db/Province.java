package com.coolweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * Created by asus1 on 2017/11/1.
 */

public class Province extends DataSupport {
    private int id;
    private String provinceName;
    private int provinceCode;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getProvinceName(){
        return provinceName;
    }
    public void setProvinceName(String name){
        this.provinceName=name;
    }
    public int getProvinceCode(){
        return provinceCode;
    }
    public void setProvinceCode(int code){
        this.provinceCode=code;
    }
}

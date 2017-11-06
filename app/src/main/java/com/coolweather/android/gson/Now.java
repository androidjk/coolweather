package com.coolweather.android.gson;

import android.test.MoreAsserts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asus1 on 2017/11/3.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}

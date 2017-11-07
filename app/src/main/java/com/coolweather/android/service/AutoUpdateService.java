package com.coolweather.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.coolweather.android.gson.Weather;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }
    public int onStartCommand(Intent intent,int flags,int startId){
        updateWeather();
        updateBingPic();
        AlarmManager manager=(AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour=8*60*60*1000;//这是8小时的毫秒数
        long triggerAttime= SystemClock.elapsedRealtime()+anHour;
        Intent i=new Intent(this,AutoUpdateService.class);
        PendingIntent pi=PendingIntent.getService(this,0,i,0);
        manager.cancel(pi);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAttime,pi);
        return super.onStartCommand(intent,flags,startId);
    }
    /**
     * 更新天气信息
     */
    private void updateWeather(){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString=prefs.getString("weather",null);
        if (weatherString!=null){
            //有缓存时直接解析天气数据
            Weather weather= Utility.handleWeatherResponse(weatherString);
            String weatherId=weather.basic.weatherId;
            String weatherUrl="http://guolin.tech/api/weather?citied="+weatherId+
                    "&key=73305b767400473592e52b9dae46fab2";
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String resonseText=response.body().string();
                    Weather weather=Utility.handleWeatherResponse(resonseText);
                    if (weather!=null&&"ok".equals(weather.status)){
                        SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(
                                AutoUpdateService.this
                        ).edit();
                    }
                }
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }


            });
        }
    }
    private void updateBingPic(){
        String requestBingPic="http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingpic=response.body().string();
                SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this)
                        .edit();
                editor.putString("bing_pic",bingpic);
                editor.apply();
            }
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }


        });
    }
}

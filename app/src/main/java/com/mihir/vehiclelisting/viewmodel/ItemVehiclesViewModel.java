package com.mihir.vehiclelisting.viewmodel;

import android.content.Context;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import android.view.View;

import com.mihir.vehiclelisting.model.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ItemVehiclesViewModel extends BaseObservable {

    private Weather weather;
    private Context context;
    SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM d yyyy");

    public ItemVehiclesViewModel(Weather weather, Context context) {
        this.weather = weather;
        this.context = context;
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
    }

    public String getTemp() {
        return weather.getTemp() + "°C";
    }

    public String getRain() {
        return weather.getRain() + "%";
    }

    public String getTime() {
        Date date = new java.util.Date(weather.getTime() * 1000L);
        return sdf.format(date) + "";
    }

    public String getWind() {
        return weather.getWind() + " km/h";
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
        notifyChange();
    }

    public void onItemClick(View view) {
    }
}

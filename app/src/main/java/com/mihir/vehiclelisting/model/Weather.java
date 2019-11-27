
package com.mihir.vehiclelisting.model;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather implements Serializable, Parcelable {

    @SerializedName("temp")
    @Expose
    private Integer temp;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("rain")
    @Expose
    private Integer rain;
    @SerializedName("wind")
    @Expose
    private Integer wind;
    public final static Creator<Weather> CREATOR = new Creator<Weather>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        public Weather[] newArray(int size) {
            return (new Weather[size]);
        }

    };
    private final static long serialVersionUID = 8707722448946715554L;

    protected Weather(Parcel in) {
        this.temp = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.time = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.rain = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.wind = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Weather() {
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getRain() {
        return rain;
    }

    public void setRain(Integer rain) {
        this.rain = rain;
    }

    public Integer getWind() {
        return wind;
    }

    public void setWind(Integer wind) {
        this.wind = wind;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(temp);
        dest.writeValue(time);
        dest.writeValue(rain);
        dest.writeValue(wind);
    }

    public int describeContents() {
        return 0;
    }

}

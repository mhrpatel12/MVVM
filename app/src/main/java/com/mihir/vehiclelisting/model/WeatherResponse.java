
package com.mihir.vehiclelisting.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse implements Serializable, Parcelable {

    @SerializedName("data")
    @Expose
    private List<Weather> data = new ArrayList<Weather>();
    public final static Creator<WeatherResponse> CREATOR = new Creator<WeatherResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public WeatherResponse createFromParcel(Parcel in) {
            return new WeatherResponse(in);
        }

        public WeatherResponse[] newArray(int size) {
            return (new WeatherResponse[size]);
        }

    };
    private final static long serialVersionUID = 8614994643475979089L;

    protected WeatherResponse(Parcel in) {
        in.readList(this.data, (Weather.class.getClassLoader()));
    }

    public WeatherResponse() {
    }

    public List<Weather> getData() {
        return data;
    }

    public void setData(List<Weather> data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}

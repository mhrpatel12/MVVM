package com.mihir.vehiclelisting.data;

import com.mihir.vehiclelisting.model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface VehicleService {

  @GET Observable<WeatherResponse> fetchVehicles(@Url String url);

}

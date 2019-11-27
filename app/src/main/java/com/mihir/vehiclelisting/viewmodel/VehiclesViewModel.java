package com.mihir.vehiclelisting.viewmodel;

import android.content.Context;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.annotation.NonNull;
import android.view.View;

import com.mihir.vehiclelisting.R;
import com.mihir.vehiclelisting.VehicleListingApplication;
import com.mihir.vehiclelisting.data.VehicleService;
import com.mihir.vehiclelisting.model.Weather;
import com.mihir.vehiclelisting.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class VehiclesViewModel extends Observable {

    public ObservableInt vehicleProgress;
    public ObservableInt vehicleRecycler;
    public ObservableInt vehicleLabel;
    public ObservableField<String> messageLabel;
    public ObservableField<String> latLong;

    private List<Weather> vehicleList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    Timer timer = new Timer();
    private VehicleListingApplication vehicleListingApplication;

    public VehiclesViewModel(VehicleListingApplication vehicleListingApplication ) {

        this.vehicleList = new ArrayList<>();
        vehicleProgress = new ObservableInt(View.GONE);
        vehicleRecycler = new ObservableInt(View.GONE);
        vehicleLabel = new ObservableInt(View.VISIBLE);
        this.vehicleListingApplication = vehicleListingApplication;
        initializeViews();
    }

    public void initializeViews() {
        vehicleLabel.set(View.GONE);
        vehicleRecycler.set(View.GONE);
        vehicleProgress.set(View.VISIBLE);
        fetchVehiclesList();
        timer.schedule(new RefreshVehicleList(), 0, 30000);
    }

    class RefreshVehicleList extends TimerTask {
        public void run() {
            vehicleLabel.set(View.GONE);
            vehicleRecycler.set(View.GONE);
            vehicleProgress.set(View.VISIBLE);
            fetchVehiclesList();
        }
    }

    private void fetchVehiclesList() {

//        VehicleListingApplication vehicleListingApplication = VehicleListingApplication.create(context);
        VehicleService vehicleService = vehicleListingApplication.getVehicleService();

        Disposable disposable = vehicleService.fetchVehicles("5d3a99ed2f0000bac16ec13a")
                .subscribeOn(vehicleListingApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResponse>() {
                    @Override
                    public void accept(WeatherResponse vehicleResponse) throws Exception {
                        changeVehicleDataSet(vehicleResponse.getData());
                        vehicleProgress.set(View.GONE);
                        vehicleLabel.set(View.GONE);
                        vehicleRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        messageLabel.set("Error");
                        vehicleProgress.set(View.GONE);
                        vehicleLabel.set(View.VISIBLE);
                        vehicleRecycler.set(View.GONE);
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void changeVehicleDataSet(List<Weather> vehicles) {
        vehicleList.clear();
        vehicleList.addAll(vehicles);
        setChanged();
        notifyObservers();
    }

    public List<Weather> getVehicleList() {
        return vehicleList;
    }

}

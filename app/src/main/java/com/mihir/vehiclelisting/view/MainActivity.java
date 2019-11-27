package com.mihir.vehiclelisting.view;

import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.mihir.vehiclelisting.R;
import com.mihir.vehiclelisting.VehicleListingApplication;
import com.mihir.vehiclelisting.databinding.ActivityHomeBinding;
import com.mihir.vehiclelisting.viewmodel.VehiclesViewModel;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private ActivityHomeBinding activityHomeBinding;
    private VehiclesViewModel vehiclesViewModel;
    private FusedLocationProviderClient fusedLocationClient;
    VehicleListingApplication vehicleListingApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vehicleListingApplication = VehicleListingApplication.create(getApplicationContext());

        initDataBinding();

        setSupportActionBar(activityHomeBinding.toolbar);
        setupListVehicleView(activityHomeBinding.listVehicles);
        setupObserver(vehiclesViewModel);
    }

    private void initDataBinding() {
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        vehiclesViewModel = new VehiclesViewModel(vehicleListingApplication);
        activityHomeBinding.setMainViewModel(vehiclesViewModel);
    }

    private void setupListVehicleView(RecyclerView listVehicles) {
        VehicleAdapter adapter = new VehicleAdapter(this);
        listVehicles.setAdapter(adapter);
        listVehicles.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //vehiclesViewModel.reset();
    }

    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof VehiclesViewModel) {
            VehicleAdapter vehicleAdapter = (VehicleAdapter) activityHomeBinding.listVehicles.getAdapter();
            VehiclesViewModel vehiclesViewModel = (VehiclesViewModel) observable;
            vehicleAdapter.setVehicleList(vehiclesViewModel.getVehicleList());
        }
    }
}

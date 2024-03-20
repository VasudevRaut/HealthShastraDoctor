package com.example.healthshastradr;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Upcommings extends Fragment {

    double longi;
    double lati;
    String token;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    RecyclerView upcomingrecycler;

    List<UpcommingListModel> data_list;
    private UpcommingListAdapter upcommingListAdapter;
    LinearLayoutManager layoutManager;

    final String sharedPreferencesFileTitle = "health";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_upcommings, container, false);

        getCurrentLocation1();
        upcomingrecycler = view.findViewById(R.id.upcommings);
        layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(RecyclerView.VERTICAL);
        upcomingrecycler.setLayoutManager(layoutManager);


        data_list = new ArrayList<>();





        SharedPreferences sharedPreferences = getContext().getSharedPreferences(sharedPreferencesFileTitle, getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String number  = sharedPreferences.getString("number","");
//        editor.putString("pass", emp_pass);
//        editor.putString("gender", data);


//        Toast.makeText(getContext(), "-"+number+"-", Toast.LENGTH_SHORT).show();

//        if(number==null)
//        {
//            number = "123456";
//        }
        Call<List<UpcommingListModel>> fetch_call= ApiControlller
                .getInstance()
                .fetchProfileApi()
                .getappointmentupcommingData(number);

//        Toast.makeText(getContext(), number+"", Toast.LENGTH_SHORT).show();


        fetch_call.enqueue(new Callback<List<UpcommingListModel>>() {
            @Override
            public void onResponse(Call<List<UpcommingListModel>> call, Response<List<UpcommingListModel>> response) {

                List<UpcommingListModel> data = response.body();

//                data_list = new ArrayList<>();
                if(data!=null) {

//                    for(UpcommingListModel models : data)
//                    {
//
//                        Toast.makeText(getContext(), "----"+models.getApp_id(), Toast.LENGTH_SHORT).show();
//
////                        data_list.add(models.app_id,models.patient_number,models.doctor_number,models.date,models.time,models.payment_status,models.appointment_status,models.prescription,models.name,
////                                models.surname,models.number,models.email,models.pass,models.age,models.token,models.lati,models.longi,);
//
//                    }


                    data_list.addAll(data);
//                    Toast.makeText(getContext(), ""+data_list.size(), Toast.LENGTH_SHORT).show();
                    upcommingListAdapter = new UpcommingListAdapter(data_list, getContext());
                    upcomingrecycler.setAdapter(upcommingListAdapter);
//                    Toast.makeText(SignUp.this, data, Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(getApplicationContext(), "Data Not available !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UpcommingListModel>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Something went wrong ! Please try again later !", Toast.LENGTH_SHORT).show();
            }
        });






//
//        data_list.add(new UpcommingListModel());
//        data_list.add(new UpcommingListModel());
//        data_list.add(new UpcommingListModel());
//        data_list.add(new UpcommingListModel());
//        data_list.add(new UpcommingListModel());
//        data_list.add(new UpcommingListModel());
//        data_list.add(new UpcommingListModel());






        upcommingListAdapter = new UpcommingListAdapter(data_list, getContext());
        upcomingrecycler.setAdapter(upcommingListAdapter);



    return view;

    }









    public void getCurrentLocation1()
    {


//        if (ContextCompat.checkSelfPermission(getContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(Upcommings.this,
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    LOCATION_PERMISSION_REQUEST_CODE);
//        } else {
//            Toast.makeText(this, "Vasudev", Toast.LENGTH_SHORT).show();
            getCurrentLocation();
//        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(getContext(), "Permission is denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation() {
//        progressBar.setVisibility(View.VISIBLE);
//        Toast.makeText(this, "getcurrentLocation", Toast.LENGTH_SHORT).show();

        LocationRequest locationRequest = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            locationRequest = new LocationRequest();
        }
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }


        LocationServices.getFusedLocationProviderClient(getContext())
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(getContext())
                                .removeLocationUpdates(this);
//                        Toast.makeText(MainActivity.this, "when set locatin", Toast.LENGTH_SHORT).show();
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestlocIndex = locationResult.getLocations().size() - 1;
                            lati = locationResult.getLocations().get(latestlocIndex).getLatitude();
                            longi = locationResult.getLocations().get(latestlocIndex).getLongitude();
                            LatLng latLng2 = new LatLng(lati,longi);

                            Toast.makeText(getContext(), "Location get", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences(sharedPreferencesFileTitle, getContext().MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("lati", lati+"");
                            editor.putString("longi", longi+"");


                            // Rest of your code

                            editor.apply();


//                            Toast.makeText(SignUp.this, lati+""+longi, Toast.LENGTH_SHORT).show();
//                            getAllFieldData();





                            //here perfoform you opration

                        } else {
//                            progressBar.setVisibility(View.GONE);

                        }
                    }
                }, Looper.getMainLooper());

    }




}
package com.example.healthshastradr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthshastradr.MapIntegration.GetLocation;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    double longi;
    double lati;
    String token;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    private EditText firstNameEditText, lastNameEditText, emailEditText, numberEditText, ageEditText,
            passwordEditText, confirmPasswordEditText, educationEditText, bioEditText, professionEditText,
            chargesEditText, hospitalNameEditText, addressEditText;
    private CheckBox agreeCheckBox;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        getToken();

        firstNameEditText = findViewById(R.id.first_name);
        lastNameEditText = findViewById(R.id.last_name);
        emailEditText = findViewById(R.id.email);
        numberEditText = findViewById(R.id.number);
        ageEditText = findViewById(R.id.age);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        educationEditText = findViewById(R.id.education);
        bioEditText = findViewById(R.id.bio);
        professionEditText = findViewById(R.id.profession);
        chargesEditText = findViewById(R.id.charges);
        hospitalNameEditText = findViewById(R.id.hospital_name);
        addressEditText = findViewById(R.id.address);
        agreeCheckBox = findViewById(R.id.agreeCheckBox);
        createAccountButton = findViewById(R.id.create_account);


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation1();
            }
        });


    }

    private String getToken() {
        String tokens;
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                         token = task.getResult();





















                        // Save or send the token as needed
                        //textView.setText(token);
                        Log.d("FCM Token", token);
                        //Toast.makeText(this, ""+token, Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle token retrieval error
                    }
                });

        return token;
    }

    private void getAllFieldData() {


        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String number = numberEditText.getText().toString();
        String age = ageEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        String education = educationEditText.getText().toString();
        String bio = bioEditText.getText().toString();
        String profession = professionEditText.getText().toString();
        String charges = chargesEditText.getText().toString();
        String hospitalName = hospitalNameEditText.getText().toString();
        String address = addressEditText.getText().toString();
        boolean agreed = agreeCheckBox.isChecked();



        final String sharedPreferencesFileTitle = "health";


        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("number", number);
        editor.apply();


        Call<String> fetch_call= ApiControlller
                .getInstance()
                .fetchProfileApi()
                .addnewDr(firstName, lastName, email, number, age, password, confirmPassword, education, bio, profession, charges, hospitalName, address,
        agreed+"",token,lati+"",longi+"");

        fetch_call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String data = response.body();
//                data_list = new ArrayList<>();

                if(data!=null) {

                    Toast.makeText(SignUp.this, data, Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(getApplicationContext(), "Data Not available !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Something went wrong ! Please try again later !", Toast.LENGTH_SHORT).show();
            }
        });






        // now append this data to in the database






    }





    public void getCurrentLocation1()
    {


        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SignUp.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
//            Toast.makeText(this, "Vasudev", Toast.LENGTH_SHORT).show();
            getCurrentLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, "Permission is denied!", Toast.LENGTH_SHORT).show();
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

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }


        LocationServices.getFusedLocationProviderClient(getApplicationContext())
                .requestLocationUpdates(locationRequest, new LocationCallback() {

                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(getApplicationContext())
                                .removeLocationUpdates(this);
//                        Toast.makeText(MainActivity.this, "when set locatin", Toast.LENGTH_SHORT).show();
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestlocIndex = locationResult.getLocations().size() - 1;
                            lati = locationResult.getLocations().get(latestlocIndex).getLatitude();
                            longi = locationResult.getLocations().get(latestlocIndex).getLongitude();
                            LatLng latLng2 = new LatLng(lati,longi);

//                            Toast.makeText(SignUp.this, lati+""+longi, Toast.LENGTH_SHORT).show();
                                getAllFieldData();

                            //here perfoform you opration

                        } else {
//                            progressBar.setVisibility(View.GONE);

                        }
                    }
                }, Looper.getMainLooper());

    }

    private class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);


        }


    }



}
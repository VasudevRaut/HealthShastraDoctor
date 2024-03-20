package com.example.healthshastradr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveClose extends AppCompatActivity {

    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_close);

        Switch s = findViewById(R.id.mess_open_close_status);

        final String sharedPreferencesFileTitle = "health";

        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);

        number = sharedPreferences.getString("number","");




        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
//                    Toast.makeText(ActiveClose.this, "Checked", Toast.LENGTH_SHORT).show();
                    Call<String> call = ApiControlller
                            .getInstance()
                            .fetchProfileApi()
                            .getOrderData5(number,"on");

                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String data = response.body();

                            if (data != null) {
                                //String arr[] = data.split(",");
                                if (data.equals("success")) {



                                } else {
                                    Toast.makeText(ActiveClose.this, "Credentials are invalid..!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

//                        Toast.makeText(getApplicationContext(), "Something went wrong please try again later!", Toast.LENGTH_LONG).show();
                        }
                    });


                }
                else {

                    Call<String> call = ApiControlller
                            .getInstance()
                            .fetchProfileApi()
                            .getOrderData5(number,"off");

                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String data = response.body();

                            if (data != null) {
                                //String arr[] = data.split(",");
                                if (data.equals("success")) {



                                } else {
                                    Toast.makeText(ActiveClose.this, "Credentials are invalid..!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

//                        Toast.makeText(getApplicationContext(), "Something went wrong please try again later!", Toast.LENGTH_LONG).show();
                        }
                    });

//                    Toast.makeText(ActiveClose.this, "Uncheked", Toast.LENGTH_SHORT).show();
                }


            }
        });




    }
}
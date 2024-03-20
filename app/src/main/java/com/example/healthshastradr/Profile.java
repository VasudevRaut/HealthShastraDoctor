package com.example.healthshastradr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthshastradr.AllModelClass.DrModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    TextView name,email,appintment,earned;
    List<DrModel> data;
    int price = 0;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);




        name = findViewById(R.id.name);
        email = findViewById(R.id.mail);
        appintment = findViewById(R.id.appointment);
        earned = findViewById(R.id.earned);



        fetchdataandSet();













    }

    private void fetchdataandSet() {



        final String sharedPreferencesFileTitle = "health";

                SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);

                 number = sharedPreferences.getString("number","");

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("number", "123456");
//        editor.apply();

//        Toast.makeText(getContext(), ""+number, Toast.LENGTH_SHORT).show();

        Call<List<DrModel>> fetch_call= ApiControlller
                .getInstance()
                .fetchProfileApi()
                .getdrData(number);

        fetch_call.enqueue(new Callback<List<DrModel>>() {
            @Override
            public void onResponse(Call<List<DrModel>> call, Response<List<DrModel>> response) {


                 data = response.body();
//                Toast.makeText(Profile.this, ""+data, Toast.LENGTH_SHORT).show();
//                data_list = new ArrayList<>();
                if(data!=null) {
                    
                   for(DrModel drModel : data)
                   {
//                       Toast.makeText(Profile.this, ""+drModel.getCharges(), Toast.LENGTH_SHORT).show();
                       name.setText(drModel.getName());
                       email.setText(drModel.getEmail());
                       if(drModel!=null)
                       price = Integer.parseInt(drModel.getCharges());


                        setappointment();

                   }
//                    Toast.makeText(SignUp.this, data, Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(getApplicationContext(), "Data Not available !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DrModel>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Something went wrong ! Please try again later !", Toast.LENGTH_SHORT).show();
            }
        });










    }

    public void setappointment( )
    {
//        Toast.makeText(this, ""+number, Toast.LENGTH_SHORT).show();
        Call<String> fetch_call1= ApiControlller
                .getInstance()
                .fetchProfileApi()
                .getdrData1(number);

        fetch_call1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String datas = response.body();


//                data_list = new ArrayList<>();
                if(datas!=null) {

                    appintment.setText(datas);
//                    Toast.makeText(Profile.this, ""+price, Toast.LENGTH_SHORT).show();
                    earned.setText(price*Integer.parseInt(datas)+" Rs");
//                    Toast.makeText(SignUp.this, data, Toast.LENGTH_SHORT).show();
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

    }

}
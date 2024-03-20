package com.example.healthshastradr;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Completed extends Fragment {

    RecyclerView upcomingrecycler;

    List<CompleteListModel> data_list;
    private CompletListAdapter upcommingListAdapter;
    LinearLayoutManager layoutManager;
    final String sharedPreferencesFileTitle = "health";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_completed, container, false);

        upcomingrecycler = view.findViewById(R.id.upcommings);
        layoutManager = new LinearLayoutManager(getContext());

        layoutManager.setOrientation(RecyclerView.VERTICAL);
        upcomingrecycler.setLayoutManager(layoutManager);


        data_list = new ArrayList<>();


        SharedPreferences sharedPreferences = getContext().getSharedPreferences(sharedPreferencesFileTitle, getContext().MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
        String number  = sharedPreferences.getString("number","");
//        editor.putString("pass", emp_pass);
//        editor.putString("gender", data);




//        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("number", "123456");
//        editor.apply();

//        Toast.makeText(getContext(), ""+number, Toast.LENGTH_SHORT).show();
        if(number==null)
        {
            number = "123456";
        }
        Call<List<CompleteListModel>> fetch_call= ApiControlller
                .getInstance()
                .fetchProfileApi()
                .getappointmentData(number);

        fetch_call.enqueue(new Callback<List<CompleteListModel>>() {
            @Override
            public void onResponse(Call<List<CompleteListModel>> call, Response<List<CompleteListModel>> response) {

                List<CompleteListModel> data = response.body();
//                data_list = new ArrayList<>();
                if(data!=null) {

                    data_list.addAll(data);
//                    Toast.makeText(getContext(), ""+data_list.size(), Toast.LENGTH_SHORT).show();
                    upcommingListAdapter = new CompletListAdapter(data_list, getContext());
                    upcomingrecycler.setAdapter(upcommingListAdapter);
//                    Toast.makeText(SignUp.this, data, Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(getApplicationContext(), "Data Not available !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CompleteListModel>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Something went wrong ! Please try again later !", Toast.LENGTH_SHORT).show();
            }
        });













//        data_list.add(new CompleteListModel());
//        data_list.add(new CompleteListModel());
//        data_list.add(new CompleteListModel());
//        data_list.add(new CompleteListModel());
//        data_list.add(new CompleteListModel());
//        data_list.add(new CompleteListModel());
//        data_list.add(new CompleteListModel());
//
//







        return view;
    }
}
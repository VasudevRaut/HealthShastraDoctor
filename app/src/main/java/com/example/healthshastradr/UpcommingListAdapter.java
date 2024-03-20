package com.example.healthshastradr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcommingListAdapter extends RecyclerView.Adapter<UpcommingListAdapter.LeadData>{

    List<UpcommingListModel> dataholder2;


    double longi;
    double lati;
    String token;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    AlertDialog.Builder builder ;


//    List<EVStation> evStations = new ArrayList<>();
//    private FirebaseFirestore firebaseFirestore;
    Context context;
    final String sharedPreferencesFileTitle = "ecoview";
    public UpcommingListAdapter(List<UpcommingListModel> dataholder2, Context context) {
        this.dataholder2 = dataholder2;
        this.context = context;

    }

//    private void init(){
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseFirestore = FirebaseFirestore.getInstance();
//
//    }

    public void setFilteredList(List<UpcommingListModel> filteredList) {
        Log.println(Log.DEBUG,"debug", "Finally"+filteredList);
        this.dataholder2 = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public LeadData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        init();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shedulecard,parent,false);
        return new LeadData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadData holder, int position) {




        holder.med_name.setText(dataholder2.get(position).getName());
        holder.datetime.setText(dataholder2.get(position).getDate()+" : "+dataholder2.get(position).getTime());



        holder.paltform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String callID = "996";
                String userID = "738";

                Intent i = new Intent(context, PreVideo.class);
//                i.putExtra("callID",callID);
//                i.putExtra("userID",userID);
                context.startActivity(i);
            }
        });


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {







//                Toast.makeText(context, "----"+dataholder2.get(position).getPatient_number(), Toast.LENGTH_SHORT).show();


                final BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(
                        context,R.style.BottomSheetDialogTheme
                );

                View bottomSheetView = LayoutInflater.from(context)
                        .inflate(
                                R.layout.bottomsheetnew, holder.bottomsheet
                        );



                bottomSheetDialog1.setContentView(bottomSheetView);
                bottomSheetDialog1.show();


                ImageView call = bottomSheetView.findViewById(R.id.call);

                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(Intent.ACTION_CALL);

                        intent.setData(Uri.parse("tel:"+dataholder2.get(position).getPatient_number()));
                        context.startActivity(intent);



                    }
                });




                    TextView name = bottomSheetView.findViewById(R.id.name);
                    name.setText(dataholder2.get(position).getName());

                    TextView timedate = bottomSheetView.findViewById(R.id.datetime);
                    timedate.setText(dataholder2.get(position).getDate()+" : "+dataholder2.get(position).getTime());

                TextView dire = bottomSheetView.findViewById(R.id.direc);

                dire.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // write direction code here


                        final String sharedPreferencesFileTitle = "health";


                        SharedPreferences sharedPreferences = context.getSharedPreferences(sharedPreferencesFileTitle, context.MODE_PRIVATE);
                        String lati = sharedPreferences.getString("lati","");
                        String longi = sharedPreferences.getString("loti","");



                        String origin =lati+","+longi; // New York coordinates
                        String destination = dataholder2.get(position).getLati()+","+dataholder2.get(position).getLongi(); // Los Angeles coordinates



                        Uri gmmIntentUri = Uri.parse("http://maps.google.com/maps?saddr=" + origin + "&daddr=" + destination);

                        // Create an Intent with the Uri
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                        // Set the package to Google Maps
                        mapIntent.setPackage("com.google.android.apps.maps");

                        // Check if there's an app to handle this intent
                        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                            // Start Google Maps with the intent
                            context.startActivity(mapIntent);
                        }










                    }
                });




//                TextView stationname = bottomSheetView.findViewById(R.id.stationname);
//                TextView price,remainingEnergy,address;
//                price = bottomSheetView.findViewById(R.id.price);
//                remainingEnergy = bottomSheetView.findViewById(R.id.remainingenergy);
//                address = bottomSheetView.findViewById(R.id.address);
//                price.setText(dataholder2.get(position).getPrice()+"");
//
//                TextView avg = bottomSheetView.findViewById(R.id.avgr);
//
//                avg.setText(dataholder2.get(position).getAvg_rating()+"");
//
//
//
//
//                address.setText(getAddress(dataholder2.get(position).getOwner_location().getLatitude(),dataholder2.get(position).getOwner_location().getLongitude()));
//                stationname.setText(dataholder2.get(position).getEv_station_name());
//
//                TextView giverating = bottomSheetView.findViewById(R.id.giveRating);
//                giverating.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = new Intent(context, GiveRatingActivity.class);
//                        intent.putExtra("owner_id",dataholder2.get(position).getOwner_id());
//                        intent.putExtra("owner_email",dataholder2.get(position).getOwner_email());
//                        context.startActivity(intent);
//
//
//                    }
//                });
//
//
//
                TextView direction= bottomSheetView.findViewById(R.id.book);
                TextView book = bottomSheetView.findViewById(R.id.direction);
//
                direction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(position);
                    }
                });

                book.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Call<String> call = ApiControlller
                                .getInstance()
                                .fetchProfileApi()
                                .getOrderData4(dataholder2.get(position).getPatient_number(), dataholder2.get(position).getDoctor_number(),dataholder2.get(position).getApp_id(),dataholder2.get(position).getToken());

                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String data = response.body();

                                if (data != null) {
                                    //String arr[] = data.split(",");
                                    if (data.equals("success")) {



                                    } else {
                                        Toast.makeText(context, "Credentials are invalid..!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

//                        Toast.makeText(getApplicationContext(), "Something went wrong please try again later!", Toast.LENGTH_LONG).show();
                            }
                        });





                    }
                });










            }
        });






    }







    private void showDialog(int position) {
        // Inflate the dialog layout
        View dialogView = LayoutInflater.from(context).inflate(R.layout.cusomdilagforpr, null);


        TextView send = dialogView.findViewById(R.id.send);





        LinearLayout editTextLayout = dialogView.findViewById(R.id.editTextLayout);








        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Sended", Toast.LENGTH_SHORT).show();


                int childCount = editTextLayout.getChildCount();
                String ss = "";
                // Iterate through each LinearLayout containing TextInputLayout
                for (int i = 0; i < childCount; i++) {
                    LinearLayout verticalLayout = (LinearLayout) editTextLayout.getChildAt(i);
                    int childEditTextCount = verticalLayout.getChildCount();

//                    Toast.makeText(context, ""+childEditTextCount, Toast.LENGTH_SHORT).show();
                        String dd = "";
                    // Iterate through each TextInputLayout to retrieve TextInputEditText
                    for (int j = 0; j < childEditTextCount; j++) {
                        TextInputLayout textInputLayout = (TextInputLayout) verticalLayout.getChildAt(j);
                        TextInputEditText editText = (TextInputEditText) textInputLayout.getEditText();



                        if (editText != null) {
                            String value = editText.getText().toString();
//                            Toast.makeText(context, "---"+value, Toast.LENGTH_SHORT).show();
                            dd+=value+"-";
                            // Do something with the value, for example, log it
                            Log.d("EditTextData", "Value from EditText: " + value);
                        }
                    }
                    ss+=dd+",";
                }
//                Toast.makeText(context, ""+ss, Toast.LENGTH_SHORT).show();


//                Toast.makeText(context, ""+dataholder2.get(position).getPatient_number(), Toast.LENGTH_SHORT).show();
                Call<String> call = ApiControlller
                        .getInstance()
                        .fetchProfileApi()
                        .getOrderData3(dataholder2.get(position).getPatient_number(), dataholder2.get(position).getDoctor_number(),dataholder2.get(position).getApp_id(),dataholder2.get(position).getToken(),ss);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String data = response.body();

                        if (data != null) {
                            //String arr[] = data.split(",");
                            if (data.equals("success")) {

                            } else {
                                Toast.makeText(context, "Credentials are invalid..!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

//                        Toast.makeText(getApplicationContext(), "Something went wrong please try again later!", Toast.LENGTH_LONG).show();
                    }
                });








            }
        });

        // Initialize views
//        EditText textField = dialogView.findViewById(R.id.textField);
//        LinearLayout editTextLayout = dialogView.findViewById(R.id.editTextLayout);
        ImageView addImage = dialogView.findViewById(R.id.addImage);

        // Build the dialog
         builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();

        // Handle click on the image to add a pair of edit texts
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditTextPair(editTextLayout);
            }
        });

        // Show the dialog
        dialog.show();
    }

    private void addEditTextPair(LinearLayout parentLayout) {
        // Inflate the edit text pair layout
        View pairLayout = LayoutInflater.from(context).inflate(R.layout.edit_text_pair_layout, null);

        // Initialize views
        EditText editText1 = pairLayout.findViewById(R.id.editText1);
        EditText editText2 = pairLayout.findViewById(R.id.editText2);

        // Add the edit text pair to the parent layout
        parentLayout.addView(pairLayout);
    }

    private String getAddress(double lati,double longi) {

        String add = "";

        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());

            addresses = geocoder.getFromLocation(lati, longi, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            add = addresses.get(0).getAddressLine(0);
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return add;
    }

    @Override
    public int getItemCount() {
        return dataholder2.size();
    }


    class LeadData extends RecyclerView.ViewHolder
    {
        TextView stationname,address;
        LinearLayout card;

//        LinearLayout doctorlistcard;
        TextView types;
        LinearLayout bottomsheet;
        ImageView paltform;


        TextView med_name,datetime;


        TextView avg;
        public LeadData(@NonNull View itemView)
        {
            super(itemView);
            bottomsheet = itemView.findViewById(R.id.bottomsheetcontainer);
            card = itemView.findViewById(R.id.card);
            paltform = itemView.findViewById(R.id.platform);
            med_name = itemView.findViewById(R.id.med_name);
            datetime = itemView.findViewById(R.id.datetime);





//            stationname = itemView.findViewById(R.id.station_name);
//            address = itemView.findViewById(R.id.distance);
//            types = itemView.findViewById(R.id.types);
//            avg = itemView.findViewById(R.id.avgr);
//        bottomsheet = itemView.findViewById(R.id.bottomsheetcontainer);
//            doctorlistcard  = itemView.findViewById(R.id.doctorlistcard);

        }
    }
    public boolean isNumeric(String str) {
        return str.matches("\\d+");
    }


//    public void showBottomSheetDialog(String tag)
//    {
//
//        final BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(
//                context,R.style.BottomSheetDialogTheme
//        );
//
//
//        View bottomSheetView = LayoutInflater.from(context)
//                .inflate(
//                        R.layout.layout_bottom_sheet, (LinearLayout)findViewById(R.id.bottomsheetcontainer)
//                );
////        Owner allowner = mp.get(tag);
//
//
////
//        TextView stationname = bottomSheetView.findViewById(R.id.stationname);
//        TextView price,remainingEnergy,address;
////        Toast.makeText(this, ""+allowner.getPrice(), Toast.LENGTH_SHORT).show();
//        price = bottomSheetView.findViewById(R.id.price);
//        remainingEnergy = bottomSheetView.findViewById(R.id.remainingenergy);
//        address = bottomSheetView.findViewById(R.id.address);
////        Toast.makeText(this, ""+ Integer.toHexString(System.identityHashCode(allowner.getOwner_location())), Toast.LENGTH_SHORT).show();
////        price.setText(allowner.getPrice()+"");
////        remainingEnergy.setText(allowner.get);
////        address.setText(getAddress());
////        stationname.setText(allowner.getOwner_name());
//
//
//
//
//
//        Button direction= bottomSheetView.findViewById(R.id.btnUpdate);
//        Button book = bottomSheetView.findViewById(R.id.book);
//
//
//
//
//        bottomSheetDialog1.setContentView(bottomSheetView);
//        bottomSheetDialog1.show();
//    }




}

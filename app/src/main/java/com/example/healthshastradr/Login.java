package com.example.healthshastradr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;
import java.util.Random;

import javax.activation.DataHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {


    String data;
    final String sharedPreferencesFileTitle = "health";
    TextView account, forg;

    EditText userLogin, userPassword;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        account = findViewById(R.id.new_account);
//        forgot_pass = findViewById(R.id.forgot);
        login_btn = findViewById(R.id.login_button);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Successful login ";
            String description = "Healthशास्त्र wishes you a healthy life ";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


        Button login_button = findViewById(R.id.login_button);
        forg = findViewById(R.id.forgot);
        forg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Login.this,EnterOTP.class);
//                startActivity(intent);
            }
        });

//        eye = findViewById(R.id.eye);
        userLogin = findViewById(R.id.userLogin);
        userPassword = findViewById(R.id.userPassword);
        //TextView signup = findViewById(R.id.signup);

        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);

        if (sharedPreferences.contains("user_phone") && sharedPreferences.contains("user_pass")) {
            String mobile = sharedPreferences.getString("user_phone", "");
            String pass = sharedPreferences.getString("user_pass", "");


            if (!mobile.equals("") && !pass.equals("")) {
                Intent intent = new Intent(Login.this, Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

        }


        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);

            }
        });


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String userlogins = userLogin.getText().toString();
                String userPasswords = userPassword.getText().toString();

                userValidation(userlogins, userPasswords);
            }
        });
    }

    private void userValidation(String userlogins, String userPasswords) {


        String emp_contact = userlogins, emp_pass = userPasswords;
        Call<String> call = ApiControlller
                .getInstance()
                .fetchProfileApi()
                .getOrderData2(emp_contact, emp_pass);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                data = response.body();

                if (data != null) {
                    //String arr[] = data.split(",");
                    if (data.equals("success")) {
                        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFileTitle, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user_phone", emp_contact);
                        editor.putString("number", emp_contact);
                        editor.putString("user_pass", emp_pass);


                        showNotification("Login Successful", "You have successfully logged in Healthशास्त्र \uD83C\uDF89");
                        // Rest of your code

                        editor.apply();
                        getDeviceToken(emp_contact);

                        Intent intent = new Intent(Login.this, Dashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Credentials are invalid..!", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong please try again later!", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void getDeviceToken(String emp_contact) {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        String token = task.getResult();


                        Call<String> call = ApiControlller
                                .getInstance()
                                .fetchProfileApi()
                                .updateDeviceTokenss(emp_contact, token);


                        //Toast.makeText(this, "vasudev", Toast.LENGTH_SHORT).show();

                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                data = response.body();


                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });


                        // Save or send the token as needed
                        //textView.setText(token);
                        Log.d("FCM Token", token);
                        //Toast.makeText(this, ""+token, Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle token retrieval error
                    }
                });
    }

    // Define CHANNEL_ID
    private static final String CHANNEL_ID = "my_notification_channel";

    // Method to generate a unique notificationId
    private int generateNotificationId() {
        return new Random().nextInt(100000);
    }

    private void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo1)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        int notificationId = generateNotificationId();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(notificationId, builder.build());
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
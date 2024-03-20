package com.example.healthshastradr.AllAPI;

import com.example.healthshastradr.AllModelClass.DrModel;
import com.example.healthshastradr.CancelListModel;
import com.example.healthshastradr.CompleteListModel;
import com.example.healthshastradr.UpcommingListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FetchProfileApi {

    @FormUrlEncoded

    // register new user
        @POST("add_new_user_api.php")
    Call<String> addnewDr(
            @Field("firstName")
            String firstName,

            @Field("lastName")
            String lastName,

            @Field("email")
            String email,

            @Field("number")
            String number,

            @Field("age")
            String age,

            @Field("password")
            String password,

            @Field("confirmPassword")
            String confirmPassword,

            @Field("education")
            String education,

            @Field("bio")
            String bio,

            @Field("profession")
            String profession,

            @Field("charges")
            String charges,

            @Field("hospitalName")
            String hospitalName,

            @Field("address")
            String address,

            @Field("agreed")
            String agreed,

            @Field("token") String token,
            @Field("lati")
            String lati,

            @Field("longi")
            String longi


    );


    @FormUrlEncoded
    @POST("fetchCompleteAppointmentStatus.php")
    Call<List<CompleteListModel>> getappointmentData(

            @Field("number") String number
    );


    @FormUrlEncoded
    @POST("fetchDrInfo.php")
    Call<List<DrModel>> getdrData(

            @Field("number") String number
    );


    @FormUrlEncoded
    @POST("fetchcount.php")
    Call<String> getdrData1(

            @Field("number") String number
    );



    @FormUrlEncoded
    @POST("fetchCanceledAppointmentStatus.php")
    Call<List<CancelListModel>> getappointmentcancelData(

            @Field("number") String number
    );

    @FormUrlEncoded
    @POST("fetchUpcommingAppointmentStatus.php")
    Call<List<UpcommingListModel>> getappointmentupcommingData(
            @Field("number") String number
    );

    @FormUrlEncoded
    @POST("user_login.php")
    Call<String>getOrderData2(
            @Field("emp_contact") String emp_contact,
            @Field("emp_pass") String emp_pass
    );

    @FormUrlEncoded
    @POST("updateappintment.php")
    Call<String>getOrderData3(
            @Field("pnumber") String pnumber,
            @Field("dnumber") String dnumber,
            @Field("appid") String appid,
            @Field("token") String token,
            @Field("preciption") String preciption
    );

    @FormUrlEncoded
    @POST("updateappintmentcancel.php")
    Call<String>getOrderData4(
            @Field("pnumber") String pnumber,
            @Field("dnumber") String dnumber,
            @Field("appid") String appid,
            @Field("token") String token

    );


    @FormUrlEncoded
    @POST("updatestatus.php")
    Call<String>getOrderData5(
            @Field("pnumber") String number,
            @Field("status") String status



    );


    @FormUrlEncoded
    // register new offer
    @POST("updateDeviceToken.php")
    Call <String>  updateDeviceTokenss (
            @Field("manager_mobile") String manager_mobile,@Field ("device_token") String device_token
    );





}

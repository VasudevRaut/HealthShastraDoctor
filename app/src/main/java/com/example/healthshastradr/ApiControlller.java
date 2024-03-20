package com.example.healthshastradr;




import com.example.healthshastradr.AllAPI.FetchProfileApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiControlller {

    // give url of your api folder
        static final String url = "https://capcun.com/Health_Shastra/Dr/";

    private static ApiControlller clientObject;

    // Create object of retrofit
    private static Retrofit retrofit;

    ApiControlller()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ApiControlller getInstance()
    {
        if(clientObject == null)
            clientObject = new ApiControlller();
        return clientObject;
    }

    // for verifying employee's credentials
//    VerifyLoginDataApi getVerifyLoginDataApi() {return retrofit.create(VerifyLoginDataApi.class);}

    // for fetching current orders for employee


//    LeadHistoryFilterAPi getLeadStatusFilterApiSet() {return retrofit.create(Pr.class);}


//    public ChangeRMleadApi changeRMLeadApis(){return  retrofit.create(ChangeRMleadApi.class);}

    public FetchProfileApi fetchProfileApi(){return  retrofit.create(FetchProfileApi.class);}
//    FetchProfileApi getFetchCurrentOrdersApi() {return retrofit.create(FetchProfileApi.class);}

//    public ProjectLocationGetApi projectLocationGetApi(){return  retrofit.create(ProjectLocationGetApi.class);}
   // public ProjectLocationGetApi proojectLocationSetApi(){return  retrofit.create(ProjectLocationGetApi.class);}

//    public AddNewLeadThroughCSVApi addNewLeadInDatabase(){return  retrofit.create(AddNewLeadThroughCSVApi.class);}

//    public AutoTriggerForFacebookAds autoTriggerForFacebookAds(){return  retrofit.create(AutoTriggerForFacebookAds.class);}

//    public updateDeviceToken updateDeviceTokens(){return  retrofit.create(updateDeviceToken.class);}






}

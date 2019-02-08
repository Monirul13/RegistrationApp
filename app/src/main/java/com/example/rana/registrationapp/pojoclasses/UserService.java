package com.example.rana.registrationapp.pojoclasses;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Rana on 2/7/2019.
 */

public interface UserService {

    @GET("api/user/getusers")
    Call<UserResponse> getUserResponse();

    @GET("api/user/getusers")
    Call<UserResponse> getUserResponseById(@Query("ID") String ID);


    @POST("api/user/CreateNew")
    Call<UserResponse> setUserResponse(@Body UserResponse userResponse);

}

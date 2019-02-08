package com.example.rana.registrationapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.rana.registrationapp.pojoclasses.Datum;
import com.example.rana.registrationapp.pojoclasses.UserResponse;
import com.example.rana.registrationapp.pojoclasses.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String USER_BASE_URL="http://syntecinternapi.azurewebsites.net/";
    private RecyclerView userRecyclerView;
    private List<Datum> datumList=new ArrayList<>();
    private UserAdapter userAdapter;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton=findViewById(R.id.fab_addUser_btn);
        userRecyclerView=findViewById(R.id.user_RecyclerView);
        layoutManager=new LinearLayoutManager(MainActivity.this);
        userRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this,
                DividerItemDecoration.VERTICAL));

        //userAdapter=new UserAdapter(MainActivity.this,datumList);
        userRecyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(USER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService=retrofit.create(UserService.class);

        userService.getUserResponse()
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                        if(response.isSuccessful())
                        {
                            UserResponse userResponse=response.body();
                            datumList=userResponse.getData();
                            userAdapter=new UserAdapter(MainActivity.this,datumList);
                            Toast.makeText(MainActivity.this,"Total number of user found : "
                                    +datumList.size(),Toast.LENGTH_LONG).show();
                            userRecyclerView.setAdapter(userAdapter);


                            /*Toast.makeText(MainActivity.this,"Total number of user found : "
                                    +datumList.size(),Toast.LENGTH_LONG).show();*/
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddUserActivity.class));
            }
        });

    }
}

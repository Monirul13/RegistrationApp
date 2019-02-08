package com.example.rana.registrationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AddUserActivity extends AppCompatActivity {

    public static final String USER_BASE_URL="http://syntecinternapi.azurewebsites.net/";
    private EditText nameET,emailET,phoneET,addressET,genderET;
    private Button userSaveButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        nameET=findViewById(R.id.name_ET);
        emailET=findViewById(R.id.email_ET);
        phoneET=findViewById(R.id.phone_ET);
        addressET=findViewById(R.id.address_ET);
        genderET=findViewById(R.id.gender_ET);

        userSaveButton=findViewById(R.id.user_save_Btn);

        userSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=nameET.getText().toString().trim();
                String email=emailET.getText().toString().trim();
                String phone=phoneET.getText().toString().trim();
                String address=addressET.getText().toString().trim();
                String gender=genderET.getText().toString().trim();

                Datum datum=new Datum();

                datum.setName(name);
                datum.setEmail(email);
                datum.setPhone(phone);
                datum.setAddress(address);
                datum.setGender(gender);

                List<Datum> dlist=new ArrayList<>();
                dlist.add(datum);

                UserResponse userResponse=new UserResponse();
                userResponse.setMessage("Create");
                userResponse.setStatus(true);
                userResponse.setData(dlist);

                saveUserResponse(userResponse);
                //Toast.makeText(getApplicationContext(),"Info"+name+email+phone+address+gender,Toast.LENGTH_LONG).show();

            }
        });

    }

    private void saveUserResponse(UserResponse userResponse) {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(USER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService=retrofit.create(UserService.class);

        userService.setUserResponse(userResponse).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(AddUserActivity.this,"User is created",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

                Toast.makeText(AddUserActivity.this,"Something went wrong : "+t,Toast.LENGTH_LONG).show();
            }
        });
    }


}

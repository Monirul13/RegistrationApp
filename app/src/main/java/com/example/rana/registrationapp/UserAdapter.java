package com.example.rana.registrationapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rana.registrationapp.pojoclasses.Datum;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Rana on 2/8/2019.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private Context context;
    private List<Datum> datumList;

    public UserAdapter(Context context, List<Datum> datumList) {
        this.context = context;
        this.datumList = datumList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_row,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, final int position) {

        holder.nameTv.setText(datumList.get(position).getName());
        holder.emailTV.setText(datumList.get(position).getEmail());
        holder.addressTV.setText(datumList.get(position).getAddress());
        holder.phoneTV.setText(datumList.get(position).getPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Name : "+datumList.get(position).getName(),Toast.LENGTH_LONG).show();
                Datum datum=datumList.get(position);
                showDetails(datum);
            }
        });
    }

    private void showDetails(Datum datum) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.alertdialog_layout,null,false);

        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("User Details");
        builder.setView(view);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok",null);
        AlertDialog alertDialog=builder.show();

        TextView nameTV=alertDialog.findViewById(R.id.name_TV);
        TextView emailTV=alertDialog.findViewById(R.id.email_TV);
        TextView phoneTV=alertDialog.findViewById(R.id.phone_TV);
        TextView addressTV=alertDialog.findViewById(R.id.address_TV);
        TextView genderTV=alertDialog.findViewById(R.id.gender_TV);

        nameTV.setText(datum.getName());
        emailTV.setText(datum.getEmail());
        phoneTV.setText(datum.getPhone());
        addressTV.setText(datum.getAddress());
        genderTV.setText(datum.getGender());
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv,emailTV,addressTV,phoneTV;

        public UserViewHolder(View itemView) {
            super(itemView);

            nameTv=itemView.findViewById(R.id.user_name_TV);
            emailTV=itemView.findViewById(R.id.user_email_TV);
            addressTV=itemView.findViewById(R.id.user_add_TV);
            phoneTV=itemView.findViewById(R.id.user_phone_TV);
        }
    }
}

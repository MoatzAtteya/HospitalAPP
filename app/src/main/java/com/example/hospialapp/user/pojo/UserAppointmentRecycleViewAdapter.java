package com.example.hospialapp.user.pojo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospialapp.R;
import com.example.hospialapp.appointments.Appointment;
import com.example.hospialapp.user.ui.UserAppointmentDetailFragment;

import java.util.List;

public class UserAppointmentRecycleViewAdapter extends RecyclerView.Adapter<UserAppointmentRecycleViewAdapter.ViewHolder> implements View.OnClickListener{

    List<Appointment> userAppoinments;
    Appointment userAppoinment;
    Context context;
    FragmentActivity fragmentActivity;


    public UserAppointmentRecycleViewAdapter(List<Appointment> userAppoinments, Context context)
    {
        this.userAppoinments = userAppoinments;
        this.context = context;
        fragmentActivity = (FragmentActivity) context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctor_name;
        TextView doctor_special;
        TextView appointment_date;
        TextView appointment_time;
        TextView appointment_fees;

        public ViewHolder( View itemView) {
            super(itemView);
            doctor_name =itemView.findViewById(R.id.doctor_name_appointmentList);
            doctor_special = itemView.findViewById(R.id.doctor_special_appointmentList);
            appointment_date = itemView.findViewById(R.id.appointmentList_date);
            appointment_time = itemView.findViewById(R.id.appointmentList_time);
            appointment_fees = itemView.findViewById(R.id.doctor_fees_appoinmentList);
        }
    }

    @Override
    public void onClick(View v) {
        UserAppointmentDetailFragment detailFragment = new UserAppointmentDetailFragment();
        Bundle sendbudle = new Bundle();
        sendbudle.putSerializable("appointment_key" , userAppoinment);
        detailFragment.setArguments(sendbudle);
        fragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, detailFragment,null).addToBackStack(null).commit();
        Toast.makeText(context.getApplicationContext(),"masmsamsa" , Toast.LENGTH_SHORT).show();

    }

    public UserAppointmentRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.user_appointment_recycleview_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserAppointmentRecycleViewAdapter.ViewHolder holder, int position) {
        userAppoinment = userAppoinments.get(position);

        holder.doctor_name.setText(userAppoinment.getDoc_name());
        holder.doctor_special.setText(userAppoinment.getDoc_special());
        holder.appointment_date.setText(userAppoinment.getDatee());
        holder.appointment_time.setText(userAppoinment.getTime());
        holder.appointment_fees.setText(String.valueOf(userAppoinment.getDoc_fees()));
    }

    @Override
    public int getItemCount() {
        return userAppoinments.size();
    }
}

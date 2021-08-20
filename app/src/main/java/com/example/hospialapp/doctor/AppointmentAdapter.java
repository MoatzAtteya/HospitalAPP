package com.example.hospialapp.doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.hospialapp.R;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.appointments.Appointment;
import com.example.hospialapp.appointments.AppointmentMethods;
import com.example.hospialapp.user.pojo.UsersMethods;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder>   {

    List<Appointment> appoinmentList;
    Appointment doctorAppointment;
    Context context;
    DataBaseHelper db;

    public AppointmentAdapter(List<Appointment> appoinmentList, Context context) {
        this.appoinmentList = appoinmentList;
        this.context = context;
        db = new DataBaseHelper(context);
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView patient_name;
        TextView doctor_special;
        TextView appointment_date;
        TextView appointment_time;
        TextView appointment_fees;
        TextView appointment_id;
        Button delete_btn;

        public ViewHolder( View itemView) {
            super(itemView);
            patient_name = itemView.findViewById(R.id.patient_name_appointmentList_doc);
            doctor_special = itemView.findViewById(R.id.doctor_special_appointmentList_doc);
            appointment_date = itemView.findViewById(R.id.appointmentList_date_doc);
            appointment_time = itemView.findViewById(R.id.appointmentList_time_doc);
            appointment_fees = itemView.findViewById(R.id.doctor_fees_appoinmentList_doc);
            appointment_id = itemView.findViewById(R.id.appointment_id);
            delete_btn = itemView.findViewById(R.id.cancel_appointment_docList);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.doctor_appointment_item , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        doctorAppointment = appoinmentList.get(position);
        String patientName = UsersMethods.get_userName_byID(db , doctorAppointment.getUser_id());

        holder.appointment_id.setText(String.valueOf(doctorAppointment.getId()));
        holder.patient_name.setText(patientName);
        holder.doctor_special.setText(doctorAppointment.getDoc_special());
        holder.appointment_date.setText(doctorAppointment.getDatee());
        holder.appointment_time.setText(doctorAppointment.getTime());
        holder.appointment_fees.setText(String.valueOf(doctorAppointment.getDoc_fees()));

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppointmentMethods.delete_appointment_byID(db , doctorAppointment.getId())) {
                    Toast.makeText(context.getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    appoinmentList.remove(position);
                    notifyDataSetChanged();
                }else
                    Toast.makeText(context.getApplicationContext(), "Error in Deleting" , Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return appoinmentList.size();
    }



}

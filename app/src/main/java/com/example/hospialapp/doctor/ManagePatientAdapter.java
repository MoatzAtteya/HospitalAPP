package com.example.hospialapp.doctor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospialapp.R;
import com.example.hospialapp.patient.Patient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ManagePatientAdapter extends RecyclerView.Adapter<ManagePatientAdapter.ViewHolder> implements Filterable {

    List<Patient> patients ;
    List<Patient> patientListFull;
    Patient patient;
    Context context;
    OnItemClickListener listener;

    public ManagePatientAdapter(List<Patient> patients, Context context) {
        this.patients = patients;
        this.context = context;
        patientListFull = new ArrayList<>(patients);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.manage_patient_item , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        patient = patients.get(position);
        holder.patientName.setText(patient.getFull_name());
        holder.patientContact.setText(patient.getContact_number());
        holder.registerDate.setText(patient.getCreation_date());
        holder.patientID.setText(String.valueOf(patient.getId()));
        if(patient.getGender() == 0)
            holder.patientGender.setText("Male");
        else if(patient.getGender() == 1)
            holder.patientGender.setText("Female");

        holder.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("ID" , String.valueOf(patient.getId()));
                bundle.putString("NAME" , patient.getFull_name());
                bundle.putString("EMAIL" , patient.getEmail());
                bundle.putString("ADDRESS" , patient.getAddress());
                bundle.putString("GENDER" , String.valueOf(patient.getGender()));
                bundle.putString("AGE" , String.valueOf(patient.getAge()));
                bundle.putString("NUMBER" , patient.getContact_number());
                bundle.putString("MEDICALH" , patient.getMedical_history());
                bundle.putString("CREATIONDATE",patient.getCreation_date());
                ViewPatientData fragment = new ViewPatientData();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.doctor_fragmentContainer , fragment,null).commit();
            }
        });

    }

    public Patient getPatientAt(int position){
        return patients.get(position);
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void deletehRecyclyViewList() {
        patients.clear();
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return patientFilter;
    }
    private Filter patientFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Patient> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(patientListFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Patient patient : patientListFull){
                    if(patient.getFull_name().toLowerCase().contains(filterPattern)){
                        filteredList.add(patient);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            patients.clear();
            patients.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView patientName;
        TextView patientGender;
        TextView patientContact;
        TextView registerDate;
        TextView patientID;
        Button viewBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            patientName = itemView.findViewById(R.id.patient_name_managePatient);
            patientGender = itemView.findViewById(R.id.patient_gender_managePatient);
            patientContact = itemView.findViewById(R.id.patient_contNum_managePatient);
            registerDate = itemView.findViewById(R.id.regDate_managePatient);
            patientID = itemView.findViewById(R.id.patientID_managePatient);
            viewBtn = itemView.findViewById(R.id.view_patient_data);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(patients.get(position));
                    }
                }
            });

        }
    }

    public  interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;

    }
}

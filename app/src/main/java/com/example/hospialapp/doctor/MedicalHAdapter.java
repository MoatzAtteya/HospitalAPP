package com.example.hospialapp.doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospialapp.R;
import com.example.hospialapp.medicalHistory.MedicalHistory;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MedicalHAdapter extends RecyclerView.Adapter<MedicalHAdapter.ViewHolder> {

    Context context;
    List<MedicalHistory> medicalHistoryList;
    MedicalHistory medicalHistory;

    public MedicalHAdapter(Context context, List<MedicalHistory> medicalHistoryList) {
        this.context = context;
        this.medicalHistoryList = medicalHistoryList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.medical_history_item , parent , false);
        MedicalHAdapter.ViewHolder viewHolder = new MedicalHAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        medicalHistory = medicalHistoryList.get(position);
        holder.id.setText(String.valueOf(medicalHistory.getId()));
        holder.pressure.setText(String.valueOf(medicalHistory.getPressure()));
        holder.weight.setText(String.valueOf(medicalHistory.getWeight()));
        holder.sugar.setText(String.valueOf(medicalHistory.getSugar()));
        holder.temp.setText(String.valueOf(medicalHistory.getTemp()));
        holder.visitDate.setText(String.valueOf(medicalHistory.getCreation_date()));
        holder.prescription.setText(String.valueOf(medicalHistory.getPersciption()));
    }

    @Override
    public int getItemCount() {
        return medicalHistoryList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView pressure;
        TextView weight;
        TextView sugar;
        TextView temp;
        TextView visitDate;
        TextView prescription;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.medicalH_id);
            pressure = itemView.findViewById(R.id.patient_blood_pressure);
            weight = itemView.findViewById(R.id.patient_weight);
            sugar = itemView.findViewById(R.id.patient_sugar);
            temp = itemView.findViewById(R.id.patient_temp);
            visitDate = itemView.findViewById(R.id.patient_visit_date);
            prescription = itemView.findViewById(R.id.patient_Prescription);
        }
    }
}

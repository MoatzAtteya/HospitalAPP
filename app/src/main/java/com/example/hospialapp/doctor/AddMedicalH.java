package com.example.hospialapp.doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospialapp.R;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentAddMedicalHBinding;
import com.example.hospialapp.medicalHistory.MedicalHistory;
import com.example.hospialapp.medicalHistory.MedicalHistoryMethods;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddMedicalH extends Fragment {

    FragmentAddMedicalHBinding binding;
    String pressure , sugar , weight , temp , persciption , register_date;
    int patient_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddMedicalHBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        Bundle patientData = getArguments();
        if(patientData != null){
            patient_id = Integer.parseInt(patientData.getString("ID"));
        }

        binding.addMedicalHBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressure = binding.patientBloodPressure.getText().toString();
                sugar = binding.patientBloodsugar.getText().toString();
                weight = binding.patientBloodWeight.getText().toString();
                temp = binding.patientBloodTemp.getText().toString();
                persciption = binding.patientBloodPersciption.getText().toString();
                setCurrentTimeDate();
                if (pressure == "" || sugar == "" || weight == ""
                    || weight == "" || persciption == ""){
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Check Your Data Again!",Snackbar.LENGTH_SHORT).show();
                } else {
                    DataBaseHelper db = new DataBaseHelper(requireContext());
                    MedicalHistory medicalHistory = new MedicalHistory(patient_id , pressure , sugar,
                            weight , temp,
                            persciption ,register_date);
                    long isMedicalAddDone = MedicalHistoryMethods.add_new_MedicalH(db , medicalHistory);
                    if (isMedicalAddDone > 0){
                        Snackbar.make(getActivity().findViewById(android.R.id.content) , "Done",Snackbar.LENGTH_SHORT).show();
                    } else
                        Snackbar.make(getActivity().findViewById(android.R.id.content) , "Check Your Data Again!",Snackbar.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
    private void setCurrentTimeDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = Calendar.getInstance().getTime();
        register_date=formatter.format(date);
    }
}
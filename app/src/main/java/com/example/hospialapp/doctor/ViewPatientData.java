package com.example.hospialapp.doctor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospialapp.R;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentViewPatientDataBinding;
import com.example.hospialapp.main.MainActivity;
import com.example.hospialapp.medicalHistory.MedicalHistory;
import com.example.hospialapp.medicalHistory.MedicalHistoryMethods;

import java.util.ArrayList;
import java.util.List;

public class ViewPatientData extends Fragment {
    FragmentViewPatientDataBinding binding;
    MedicalHAdapter adapter;
    List<MedicalHistory> medicalHistoryList = new ArrayList<>();
    DataBaseHelper db;
    int patient_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBaseHelper(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentViewPatientDataBinding.inflate(inflater,container,false);
        View view = binding.getRoot();


        binding.medicalHRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.medicalHRecycleView.setHasFixedSize(true);



        Bundle patientData = getArguments();
        if(patientData != null){
            setPatientDataUI(patientData);
            patient_id = Integer.parseInt(patientData.getString("ID"));
        }
        medicalHistoryList = MedicalHistoryMethods.get_all_History(db , patient_id);
        updateUI();

        binding.addMedicalHBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMedicalH fragment = new AddMedicalH();
                fragment.setArguments(patientData);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer , fragment,null).addToBackStack(null).commit();
            }
        });



        return view;
    }

    private void updateUI() {
        if(adapter == null){
            adapter = new MedicalHAdapter(requireContext() , medicalHistoryList);
            binding.medicalHRecycleView.setAdapter(adapter);
        }else
            adapter.notifyDataSetChanged();
    }

    private void setPatientDataUI(Bundle bundle) {
        binding.patientNameViewData.setText(bundle.get("NAME").toString());
        binding.patientAddressViewData.setText(bundle.get("ADDRESS").toString());
        binding.patientAgeViewData.setText(bundle.get("AGE").toString());
        binding.patientEmailViewData.setText(bundle.get("EMAIL").toString());
        binding.patientNumberViewData.setText(bundle.get("NUMBER").toString());
        binding.patientMedialHViewData.setText(bundle.get("MEDICALH").toString());
        binding.regDatePatient.setText(bundle.get("CREATIONDATE").toString());
        if(Integer.parseInt(String.valueOf(bundle.get("GENDER"))) == 1){
            binding.patientGenderViewData.setText("Female");
        }else if(Integer.parseInt(String.valueOf(bundle.get("GENDER"))) == 0){
            binding.patientGenderViewData.setText("MALE");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
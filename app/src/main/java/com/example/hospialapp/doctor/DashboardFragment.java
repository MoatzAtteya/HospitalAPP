package com.example.hospialapp.doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospialapp.R;
import com.example.hospialapp.databinding.FragmentDoctorDashboardBinding;

public class DashboardFragment extends Fragment {
    FragmentDoctorDashboardBinding binding;
    int doc_id , specializationID , docFees;
    long contactNo;
    String address ,name, email , password , registerDate ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoctorDashboardBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        Bundle doctorData = getArguments();
       /* if(doctorData != null){
            // TODO: 7/24/2021 get all the doctor data from the received bundel.
            doc_id = Integer.parseInt(doctorData.getString("ID"));
            specializationID = Integer.parseInt(doctorData.getString("SPECID"));
            docFees = Integer.parseInt(doctorData.getString("Fees"));
            contactNo = Long.parseLong(doctorData.getString("CONTNUM"));
            address = doctorData.getString("ADDRESS");
            name = doctorData.getString("USERNAME");
            email = doctorData.getString("EMAIL");
            password = doctorData.getString("PASSWORD");
            registerDate = doctorData.getString("REGDATE");
        }*/
        // TODO: 7/24/2021 make a new bundel with the doctor's data to intent it to the 4 fragments.

        binding.doctorAddPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPatient addPatient = new AddPatient();
                addPatient.setArguments(doctorData);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, addPatient).addToBackStack(null).commit();
            }
        });

        binding.doctorUpateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDoctorProfile updateDoctorProfile = new UpdateDoctorProfile();
                updateDoctorProfile.setArguments(doctorData);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, updateDoctorProfile).addToBackStack(null).commit();

            }
        });

        binding.doctorViewAppoinmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewAppointments viewAppointments = new ViewAppointments();
                viewAppointments.setArguments(doctorData);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, viewAppointments).addToBackStack(null).commit();
            }
        });

        binding.doctorManagePatientBtnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagePatient managePatient = new ManagePatient();
                managePatient.setArguments(doctorData);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, managePatient).addToBackStack(null).commit();
            }
        });


        return view;
    }
}
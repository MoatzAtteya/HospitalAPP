package com.example.hospialapp.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentDoctorAppointmenHistoryBinding;
import com.example.hospialapp.appointments.Appointment;
import com.example.hospialapp.appointments.AppointmentMethods;

import java.util.ArrayList;
import java.util.List;


public class ViewAppointments extends Fragment {
    private  FragmentDoctorAppointmenHistoryBinding binding;
    List<Appointment> doctorAppointments = new ArrayList<>();
    AppointmentAdapter doctorAppointmentAdapter;
    DataBaseHelper db;
    String docName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBaseHelper(requireContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= FragmentDoctorAppointmenHistoryBinding.inflate(inflater,container,false);
        View view=binding.getRoot();

        binding.doctorAppointmentsRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.doctorAppointmentsRecycleView.setHasFixedSize(true);

        Bundle doctor_data = getArguments();
        if(doctor_data != null)
        {
            docName = doctor_data.getString("USERNAME");
            System.out.println("doctor name is: " + docName );
        }

        doctorAppointments = AppointmentMethods.get_allAppontments_ByDocName(db,docName);

        updateUI();


        return  view;

    }

    public void updateUI() {
        if(doctorAppointmentAdapter == null){
            doctorAppointmentAdapter = new AppointmentAdapter(doctorAppointments , requireContext());
            binding.doctorAppointmentsRecycleView.setAdapter(doctorAppointmentAdapter);
        }else
            doctorAppointmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}

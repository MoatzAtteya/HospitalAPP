package com.example.hospialapp.user.ui;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.R;
import com.example.hospialapp.databinding.FragmentUserViewAppoinmentBinding;
import com.example.hospialapp.appointments.Appointment;
import com.example.hospialapp.appointments.AppointmentMethods;
import com.example.hospialapp.user.pojo.UserAppointmentRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserViewAppoinmentFragment extends Fragment {
    private FragmentUserViewAppoinmentBinding binding;
    List<Appointment> userAppoinments = new ArrayList<>();
    UserAppointmentRecycleViewAdapter userAppointmentRecycleViewAdapter;
    DataBaseHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DataBaseHelper(requireContext());
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                UserDashboardFragment userDashboardFragment = new UserDashboardFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, userDashboardFragment).commit();
            }};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentUserViewAppoinmentBinding.inflate(inflater,container,false);
        View view=binding.getRoot();

        binding.appointmentRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.appointmentRecycleView.setHasFixedSize(true);

        Bundle received_Bundle=getArguments();
        int user_id = Integer.parseInt(received_Bundle.getString("ID"));
        System.out.println("user id is:" + user_id);

        userAppoinments = AppointmentMethods.get_allAppontments_ByID(db,user_id);

        updateUI();



        return view;
    }

    private void updateUI() {
        if(userAppointmentRecycleViewAdapter == null){
            userAppointmentRecycleViewAdapter = new UserAppointmentRecycleViewAdapter(userAppoinments, requireActivity());
            binding.appointmentRecycleView.setAdapter(userAppointmentRecycleViewAdapter);
        }else
            userAppointmentRecycleViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
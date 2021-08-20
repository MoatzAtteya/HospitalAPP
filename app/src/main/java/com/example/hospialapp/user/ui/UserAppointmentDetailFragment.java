package com.example.hospialapp.user.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospialapp.databinding.FragmentUserAppointmentDetailBinding;

public class UserAppointmentDetailFragment extends Fragment {
    FragmentUserAppointmentDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserAppointmentDetailBinding.inflate(inflater,container,false);
        View view = binding.getRoot();


        return view;
    }
}
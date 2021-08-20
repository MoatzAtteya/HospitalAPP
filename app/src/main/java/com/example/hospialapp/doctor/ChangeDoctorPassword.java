package com.example.hospialapp.doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospialapp.R;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentChangeDoctorPasswordBinding;
import com.google.android.material.snackbar.Snackbar;


public class ChangeDoctorPassword extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    String doctor_name , reg_date , doctor_password;
    int doctor_id;

    FragmentChangeDoctorPasswordBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangeDoctorPasswordBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        DataBaseHelper db = new DataBaseHelper(requireContext());
        Bundle doctorData = getArguments();
        if(doctorData != null){
            doctor_name = doctorData.getString("USERNAME");
            doctor_password = doctorData.getString("PASSWORD");
            doctor_id = Integer.parseInt(doctorData.getString("ID"));
            reg_date = doctorData.getString("REGDATE");
        }
        binding.doctorNameTextUpdate.setText(doctor_name);
        binding.doctorRegDateUpdate.setText(reg_date);

        binding.doctorUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current_password = String.valueOf(binding.doctorCurrentPassUpdateInput.getText());
                String new_entered_password = String.valueOf(binding.doctorNewPassUpdateInput.getText());
                String confirm_new_password = String.valueOf(binding.docotrConfirmPassUpdateIpnut.getText());
                if (current_password.equals(doctor_password) && new_entered_password.equals(confirm_new_password)){
                    DoctorsMethods.update_doctorPassword(db , new_entered_password , doctor_id);
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Updated" , Snackbar.LENGTH_SHORT).show();
                    clearUiInputs();
                }else
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Check your Data again!" , Snackbar.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    private void clearUiInputs() {
        binding.doctorCurrentPassUpdateInput.setText("");
        binding.doctorNewPassUpdateInput.setText("");
        binding.docotrConfirmPassUpdateIpnut.setText("");
    }


}
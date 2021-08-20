package com.example.hospialapp.doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospialapp.R;
import com.example.hospialapp.ValidationsMethods;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentUpdateDoctorProfileBinding;
import com.google.android.material.snackbar.Snackbar;


public class UpdateDoctorProfile extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentUpdateDoctorProfileBinding binding;
    String doctorName , doctorEmail , doctorAddress , date;
    int doctorID;
    boolean check_email,check_username,check_address;
    DataBaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUpdateDoctorProfileBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        db = new DataBaseHelper(requireContext());

        Bundle doctorData = getArguments();
        if(doctorData != null){
            doctorID = Integer.parseInt(doctorData.getString("ID"));
            doctorName = doctorData.getString("USERNAME");
            doctorAddress = doctorData.getString("ADDRESS");
            date = doctorData.getString("REGDATE");
            doctorEmail = doctorData.getString("EMAIL");
        }
        setUiData();

        binding.updateDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            boolean result = isInputUiCorrect();
            if(result){
                doctorName = binding.doctorNameUpdateInput.getText().toString();
                doctorAddress = binding.doctorAddressUpdateIpnut.getText().toString();
                doctorEmail =  binding.doctorEmailUpdateInput.getText().toString();
                if(DoctorsMethods.update_doctor_profile(db , doctorID , doctorName , doctorEmail , doctorAddress) > 0){
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Edit is done" , Snackbar.LENGTH_SHORT).show();
                    clearUiInputs();
                }else {
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Error while Editing!" , Snackbar.LENGTH_SHORT).show();

                }
            }else
                Snackbar.make(getActivity().findViewById(android.R.id.content) , "Check The data again!" , Snackbar.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private void clearUiInputs() {
        binding.doctorEmailUpdateInput.setText("");
        binding.doctorAddressUpdateIpnut.setText("");
        binding.doctorNameUpdateInput.setText("");
    }

    private boolean isInputUiCorrect() {
        check_username = ValidationsMethods.validate_doctorName_edit(binding);
        check_address = ValidationsMethods.validate_doctorAddress_edit(binding);
        check_email = ValidationsMethods.validate_doctorEmail_edit(binding);
        return check_username && check_address && check_email;

    }

    private void setUiData() {
        binding.doctorNameTextUpdate.setText(doctorName.toString());
        binding.doctorEmailUpdateInput.setText(doctorEmail.toString());
        binding.regDateDoctorUpdate.setText(date.toString());
        binding.doctorAddressUpdateIpnut.setText(doctorAddress.toString());
        binding.doctorNameUpdateInput.setText(doctorName.toString());
    }
}
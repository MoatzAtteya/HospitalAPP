package com.example.hospialapp.doctor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hospialapp.R;
import com.example.hospialapp.ValidationsMethods;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentDoctorEditPatientBinding;
import com.example.hospialapp.patient.Patient;
import com.example.hospialapp.patient.PatientMethods;
import com.google.android.material.snackbar.Snackbar;


public class EditPatient extends Fragment {
    FragmentDoctorEditPatientBinding binding;
    private boolean isNameCorrect , isAddressCorrect , isAgeCorrect , isEmailCorrect , isContNumCorrect , isMedicalHCorrect;
    int check_gender = -1 , patientGender ,patientAge , patientID ;
    String  patientFullName , patientContactNum , patientEmail , patientAddress , patientMedicalHistory ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoctorEditPatientBinding.inflate(inflater , container , false);
        View view = binding.getRoot();

        Bundle patientBundle = getArguments();
        if(patientBundle != null){
           setInputsData(patientBundle);
           patientID = Integer.parseInt(patientBundle.get("ID").toString());
           validatPatientInputs();
        }

        binding.editPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllPatientData();
                validatPatientInputs();
               // System.out.println(isNameCorrect + " , " + isAddressCorrect + " , " + isAgeCorrect + " , " + isContNumCorrect + " , " + isEmailCorrect +" , " + isMedicalHCorrect + " , " + check_gender);
                if(isAllInputsCorrect()){
                    DataBaseHelper db = new DataBaseHelper(requireContext());
                    Patient patient = new Patient(patientFullName , patientContactNum ,
                            patientEmail , patientAddress ,
                            patientMedicalHistory, patientID,
                            patientGender , patientAge);
                    if(PatientMethods.update_patient_data(db , patient) > 0){
                        Snackbar.make(getActivity().findViewById(android.R.id.content) , "Edit is done" , Snackbar.LENGTH_SHORT).show();
                        clearInputsUI();
                    }else {
                        Snackbar.make(getActivity().findViewById(android.R.id.content) , "Error while Editing!" , Snackbar.LENGTH_SHORT).show();
                    }

                }else
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Check The data again!" , Snackbar.LENGTH_SHORT).show();
            }
        });




        return view;
    }

    private void clearInputsUI() {
        binding.patientnameEditInput.setText("");
        binding.patientAddressInputedit.setText("");
        binding.patientEmailEditinput.setText("");
        binding.patientNumberEditinput.setText("");
        binding.patientAgeInputedit.setText("");
        binding.patientMedicalHInputedit.setText("");
    }

    private boolean isAllInputsCorrect() {
        return isNameCorrect && isEmailCorrect
                && isAddressCorrect && isMedicalHCorrect
                && isAgeCorrect && isContNumCorrect
                && (check_gender == 0 || check_gender == 1);
    }

    private void setInputsData(Bundle patientBundle) {
        binding.patientnameEditInput.setText(patientBundle.get("FULLNAME").toString());
        binding.patientAddressInputedit.setText(patientBundle.get("ADDRESS").toString());
        binding.patientEmailEditinput.setText(patientBundle.get("EMAIL").toString());
        binding.patientNumberEditinput.setText(patientBundle.get("NUMBER").toString());
        binding.patientAgeInputedit.setText(patientBundle.get("AGE").toString());
        binding.patientMedicalHInputedit.setText(patientBundle.get("MEDICALH").toString());
        if(Integer.parseInt(String.valueOf(patientBundle.get("GENDER"))) == 1){
            binding.femaleRadioBtnPatientedit.setChecked(true);
        }else if(Integer.parseInt(String.valueOf(patientBundle.get("GENDER"))) == 0){
            binding.maleRadioBtnPatientedit.setChecked(true);
        }
    }

    private void setAllPatientData() {
        patientFullName = binding.patientnameEditInput.getText().toString();
        patientEmail = binding.patientEmailEditinput.getText().toString();
        patientAddress = binding.patientAddressInputedit.getText().toString();
        patientMedicalHistory = binding.patientMedicalHInputedit.getText().toString();
        if(!binding.patientAgeInputedit.getText().toString().isEmpty() ){
            patientAge = Integer.valueOf(binding.patientAgeInputedit.getText().toString());
        }
        patientContactNum = binding.patientNumberEditinput.getText().toString();
    }

    private void validatPatientInputs() {
        isNameCorrect = ValidationsMethods.validate_PatientName_edit(binding);
        isAddressCorrect = ValidationsMethods.validate_patientAddress_edit(binding);
        isEmailCorrect = ValidationsMethods.validate_patientEmail_edit(binding);
        isMedicalHCorrect = ValidationsMethods.validate_patientMedicalH_edit(binding);
        isAgeCorrect = ValidationsMethods.check_patientAge_edit(binding);
        isContNumCorrect = ValidationsMethods.check_patientNum_edit(binding);
        check_gender = ValidationsMethods.validate_patientGender_edit(binding);
    }
}
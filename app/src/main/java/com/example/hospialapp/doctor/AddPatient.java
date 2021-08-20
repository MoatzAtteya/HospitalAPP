package com.example.hospialapp.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.hospialapp.R;
import com.example.hospialapp.ValidationsMethods;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentDoctorAddpatientBinding;
import com.example.hospialapp.patient.Patient;
import com.example.hospialapp.patient.PatientMethods;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddPatient extends Fragment {

    FragmentDoctorAddpatientBinding binding;
    private boolean isNameCorrect , isAddressCorrect , isAgeCorrect , isEmailCorrect , isContNumCorrect , isMedicalHCorrect;
    int check_gender = -1 , patientGender , doc_id  ,patientAge ;
    String register_date , patientFullName , patientContactNum , patientEmail , patientAddress , patientMedicalHistory ;

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDoctorAddpatientBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        Bundle doctorData = getArguments();
        doc_id =  Integer.parseInt(doctorData.getString("ID"));
        validatPatientInputs();

        binding.addPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatPatientInputs();
                setAllPatientData();

                if(isAllInputsCorrect()){
                    setCurrentTimeDate();
                    DataBaseHelper db = new DataBaseHelper(requireContext());
                    Patient patient = new Patient(patientFullName,
                            patientContactNum, patientEmail,
                            register_date , patientAddress,
                            patientMedicalHistory, doc_id ,
                            patientGender , patientAge);
                    long isPatientAddDone = PatientMethods.add_new_patient(db,patient);
                    if(isPatientAddDone > 0){
                        Snackbar.make(getActivity().findViewById(android.R.id.content) , "Adding Patient done" , Snackbar.LENGTH_SHORT).show();
                    }
                    else {
                        Snackbar.make(getActivity().findViewById(android.R.id.content) , "Something went wrong!" , Snackbar.LENGTH_SHORT).show();

                    }
                }else
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Check the data again!" , Snackbar.LENGTH_SHORT).show();
            }
        });


        return view;
    }
    private boolean isAllInputsCorrect() {
        return isNameCorrect && isEmailCorrect
                && isAddressCorrect && isMedicalHCorrect
                && isAgeCorrect && isContNumCorrect
                && (check_gender == 0 || check_gender == 1);
    }

    private void setAllPatientData() {
        patientFullName = binding.patientnameSignupInput.getText().toString();
        patientEmail = binding.patientEmailInput.getText().toString();
        patientAddress = binding.patientAddressInput.getText().toString();
        patientMedicalHistory = binding.patientMedicalHInput.getText().toString();
        if(!binding.patientAgeInput.getText().toString().isEmpty() ){
            patientAge = Integer.valueOf(binding.patientAgeInput.getText().toString());
        }
        patientContactNum = binding.patientNumberInput.getText().toString();
    }


    private void validatPatientInputs() {
        isNameCorrect = ValidationsMethods.validate_PatientName_signup(binding);
        isAddressCorrect = ValidationsMethods.validate_patientAddress_signup(binding);
        isEmailCorrect = ValidationsMethods.validate_patientEmail_signUp(binding);
        isMedicalHCorrect = ValidationsMethods.validate_patientMedicalH_signUp(binding);
        isAgeCorrect = ValidationsMethods.check_patientAge_signup(binding);
        isContNumCorrect = ValidationsMethods.check_patientNum_signup(binding);
        check_gender = ValidationsMethods.validate_patientGender_checked(binding);
    }

    private void setCurrentTimeDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = Calendar.getInstance().getTime();
        register_date=formatter.format(date);
    }
}

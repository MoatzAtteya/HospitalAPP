/*package com.example.hospialapp.doctor;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospialapp.ValidationsMethods;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentDoctorAddpatientBinding;
import com.example.hospialapp.patient.Patient;
import com.example.hospialapp.patient.PatientMethods;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddPatientViewModel extends ViewModel {

    public MutableLiveData<String> patientFullName = new MutableLiveData<>();
    public MutableLiveData<String> patientContactNum = new MutableLiveData<>();
    public MutableLiveData<String> patientEmail = new MutableLiveData<>();
    public MutableLiveData<String> patientAddress = new MutableLiveData<>();
    public MutableLiveData<Integer> patientAge = new MutableLiveData<>();
    public MutableLiveData<String> patientMedicalHistory = new MutableLiveData<>();
    private boolean isNameCorrect , isAddressCorrect , isAgeCorrect , isEmailCorrect , isContNumCorrect , isMedicalHCorrect;
    int check_gender = -1 , patientGender , doc_id;
    String register_date;

    Context context;
    FragmentDoctorAddpatientBinding binding;

    public AddPatientViewModel(Context context , FragmentDoctorAddpatientBinding binding , int doc_id){
        this.context = context;
        this.binding = binding;
        this.doc_id = doc_id;
        validatPatientInputs();

    }


    public void onAddBtnClicked()
    {
        validatPatientInputs();
        setAllPatientData();
        if(isAllInputsCorrect()){
            setCurrentTimeDate();
            DataBaseHelper db = new DataBaseHelper(context.getApplicationContext());
            Patient patient = new Patient(patientFullName.getValue(),
                    patientContactNum.getValue(), patientEmail.getValue() ,
                    register_date , patientAddress.getValue() ,
                    patientMedicalHistory.getValue(), doc_id ,
                    patientGender , patientAge.getValue());
            long isPatientAddDone = PatientMethods.add_new_patient(db,patient);
            if(isPatientAddDone > 0){
                Toast.makeText(context.getApplicationContext(),"ADDED",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context.getApplicationContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();

            }
        }else
            Toast.makeText(context.getApplicationContext(),"Check The Fields again",Toast.LENGTH_SHORT).show();


    }

    private boolean isAllInputsCorrect() {
        return isNameCorrect && isEmailCorrect
                && isAddressCorrect && isMedicalHCorrect
                && isAgeCorrect && isContNumCorrect
                && (check_gender == 0 || check_gender == 1);
    }

    private void setAllPatientData() {
        patientFullName.setValue(binding.patientnameSignupInput.getText().toString());
        patientEmail.setValue(binding.patientEmailInput.getText().toString());
        patientAddress.setValue(binding.patientAddressInput.getText().toString());
        patientMedicalHistory.setValue(binding.patientMedicalHInput.getText().toString());
        patientAge.setValue(Integer.valueOf(binding.patientAgeInput.getText().toString()));
        patientContactNum.setValue(binding.patientNumberInput.getText().toString());
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
*/
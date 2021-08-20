package com.example.hospialapp;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.hospialapp.databinding.ActivityMainBinding;
import com.example.hospialapp.databinding.ActivityUserSignUpBinding;
import com.example.hospialapp.databinding.ActivityUserSignUpBinding;
import com.example.hospialapp.databinding.FragmentDoctorAddpatientBinding;
import com.example.hospialapp.databinding.FragmentDoctorEditPatientBinding;
import com.example.hospialapp.databinding.FragmentUpdateDoctorProfileBinding;
import com.example.hospialapp.databinding.FragmentUpdateUserProfileBinding;

public class ValidationsMethods {

    public static boolean  check_username_login , check_password_login, check_address , check_passwordConf , check_password , check_userName , check_fullName , check_email , medicalHistory , check_age ,check_contNum ;
    public static int check_gender;

    public static boolean validate_userName_login(ActivityMainBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";
        binding.usernameLoginInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.usernameLoginLayout.setError("username field can not be empty!");
                    check_username_login =false;
                }else if (binding.passwordLoginInput.length() > 20){
                    binding.usernameLoginLayout.setError("username is too large!");
                    check_username_login =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.usernameLoginLayout.setError("Invalid username!");
                    check_username_login =false;
                }
                else {
                    binding.usernameLoginLayout.setError(null);
                    check_username_login =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    return check_username_login;

    }

    public static boolean validate_password_logIn(ActivityMainBinding binding) {
        binding.passwordLoginInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    binding.passwordLoginLayout.setError("Password can not be empty");
                    check_password_login=false;
                }else if(binding.passwordLoginInput.length() < 10){
                    binding.passwordLoginLayout.setError("Password can not be less than 10 symbols");
                    check_password_login=false;
                }else {
                    binding.passwordLoginLayout.setError(null);
                    check_password_login=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    return check_password_login;
    }

    public static boolean validate_userName_signup(ActivityUserSignUpBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";

        binding.usernameSignupInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.usernameSignupLayout.setError("username field can bot be empty");
                    check_userName =false;
                }else if (count > 20){
                    binding.usernameSignupLayout.setError("username is too large!");
                    check_userName =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.usernameSignupLayout.setError("Invalid username!");
                    check_userName =false;
                }
                else {
                    binding.usernameSignupLayout.setError(null);
                    check_userName =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_userName;
    }

    public static boolean validate_email_signUp(ActivityUserSignUpBinding binding){
        String checkEmail = "[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+";

        binding.EmailSignupInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    binding.EmailSignupLayout.setError("Email can not be empty");
                    check_email=false;
                }else if(!s.toString().matches(checkEmail))
                {
                    binding.EmailSignupLayout.setError("Invalid Email Address");
                    check_email=false;
                }else {
                    binding.EmailSignupLayout.setError(null);
                    check_email = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_email;
    }

    public static boolean validate_password_signUp(ActivityUserSignUpBinding binding) {
        binding.passwordSignupInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    binding.passordSignupLayout.setError("Password can not be empty");
                    check_password=false;
                }else if(binding.passwordSignupInput.length() < 10){
                    binding.passordSignupLayout.setError("Password can not be less than 10 symbols");
                    check_password=false;
                }else {
                    binding.passordSignupLayout.setError(null);
                    check_password=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return check_password;
    }

    public static boolean check_confirm_password(ActivityUserSignUpBinding binding){
        binding.passwordCreateSignupInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    binding.passordCreateSignupLayout.setError("Password can not be empty!");
                    check_passwordConf=false;
                }

                else if(!s.toString().equals(binding.passwordSignupInput.getText().toString())){
                    binding.passordCreateSignupLayout.setError("Password does not matches!");
                    check_passwordConf=false;
                }else {
                    binding.passordCreateSignupLayout.setError(null);
                    check_passwordConf=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_passwordConf;
    }

    public static boolean validate_address_signup(ActivityUserSignUpBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";
        binding.addressTextIpnut.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.addressTextLayout.setError("address field can bot be empty");
                    check_address =false;
                }else if (count > 20){
                    binding.addressTextLayout.setError("address is too large!");
                    check_address =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.addressTextLayout.setError("Invalid address!");
                    check_address =false;
                }
                else {
                    binding.addressTextLayout.setError(null);
                    check_address =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_address;
    }

    public static boolean validate_fullName_signup(ActivityUserSignUpBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";

        binding.fullNameTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.fullNameTextLayout.setError("username field can bot be empty");
                    check_fullName =false;
                }else if (count > 20){
                    binding.fullNameTextLayout.setError("username is too large!");
                    check_fullName =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.fullNameTextLayout.setError("Invalid username!");
                    check_fullName =false;
                }
                else {
                    binding.fullNameTextLayout.setError(null);
                    check_fullName =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_fullName;
    }

    public static int validate_gender_checked(ActivityUserSignUpBinding binding){
        if(binding.maleRadioBtn.isChecked()){
            check_gender = 0;
            binding.femaleRadioBtn.setError(null);
        } else if(binding.femaleRadioBtn.isChecked()){
            check_gender = 1;
            binding.femaleRadioBtn.setError(null);
        }else {
            binding.femaleRadioBtn.setError("Select Gender");
            check_gender = -1;
        }
        return check_gender;
    }


    public static boolean validate_userName_update(FragmentUpdateUserProfileBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";

        binding.usernameUpdateInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.usernameUpdateLayout.setError("username field can bot be empty");
                    check_userName =false;
                }else if (count > 20){
                    binding.usernameUpdateLayout.setError("username is too large!");
                    check_userName =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.usernameUpdateLayout.setError("Invalid username!");
                    check_userName =false;
                }
                else {
                    binding.usernameUpdateLayout.setError(null);
                    check_userName =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_userName;
    }

    public static boolean validate_email_update(FragmentUpdateUserProfileBinding binding){
        String checkEmail = "[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+";

        binding.EmailUpdateInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    binding.EmailUpdateLayout.setError("Email can not be empty");
                    check_email=false;
                }else if(!s.toString().matches(checkEmail))
                {
                    binding.EmailUpdateLayout.setError("Invalid Email Address");
                    check_email=false;
                }else {
                    binding.EmailUpdateLayout.setError(null);
                    check_email = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_email;
    }

    public static boolean validate_address_update(FragmentUpdateUserProfileBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";
        binding.addressUpdateIpnut.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.addressUpdateLayout.setError("address field can bot be empty");
                    check_address =false;
                }else if (count > 20){
                    binding.addressUpdateLayout.setError("address is too large!");
                    check_address =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.addressUpdateLayout.setError("Invalid address!");
                    check_address =false;
                }
                else {
                    binding.addressUpdateLayout.setError(null);
                    check_address =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_address;
    }
    public static boolean validate_PatientName_signup(FragmentDoctorAddpatientBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";

        binding.patientnameSignupInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.patientnameSignupLayout.setError("Name field can not be empty");
                    check_userName =false;
                }else if (count > 20){
                    binding.patientnameSignupLayout.setError("Name is too large!");
                    check_userName =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.patientnameSignupLayout.setError("Invalid Name!");
                    check_userName =false;
                }
                else {
                    binding.patientnameSignupLayout.setError(null);
                    check_userName =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_userName;
    }

    public static boolean validate_patientEmail_signUp(FragmentDoctorAddpatientBinding binding){
        String checkEmail = "[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+";

        binding.patientEmailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    binding.patientEmailInputLayout.setError("Email can not be empty");
                    check_email=false;
                }else if(!s.toString().matches(checkEmail))
                {
                    binding.patientEmailInputLayout.setError("Invalid Email Address");
                    check_email=false;
                }else {
                    binding.patientEmailInputLayout.setError(null);
                    check_email = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_email;
    }

    public static boolean validate_patientMedicalH_signUp(FragmentDoctorAddpatientBinding binding){
        String checkHistory = "([a-zA-Z-0-9_ ])*$";

        binding.patientMedicalHInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().matches(checkHistory))
                {
                    binding.patientMedichalHInputLayout.setError("Invalid History");
                    medicalHistory=false;
                }else {
                    binding.patientMedichalHInputLayout.setError(null);
                    medicalHistory = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return medicalHistory;
    }
    public static boolean validate_patientAddress_signup(FragmentDoctorAddpatientBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";
        binding.patientAddressInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.patientAddressInputlayout.setError("Address field can bot be empty");
                    check_address =false;
                }else if (count > 20){
                    binding.patientAddressInputlayout.setError("Address is too large!");
                    check_address =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.patientAddressInputlayout.setError("Invalid Address!");
                    check_address =false;
                }
                else {
                    binding.patientAddressInputlayout.setError(null);
                    check_address =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_address;
    }

    public static boolean check_patientAge_signup(FragmentDoctorAddpatientBinding binding){
        binding.patientAgeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    binding.patientAgeInputlayout.setError("Age Field can not be empty!");
                    check_age = false;
                } else if(count > 3){
                    binding.patientAgeInputlayout.setError("Enter Correct Age!");
                    check_age = false;
                }else {
                    binding.patientAgeInputlayout.setError(null);
                    check_age = true;

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return  check_age;
    }

    public  static boolean check_patientNum_signup(FragmentDoctorAddpatientBinding binding){
        binding.patientNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    binding.patientNumberTextInputLayout.setError("Contact Number Can not be empty!");
                    check_contNum = false;
                }else {
                    binding.patientNumberTextInputLayout.setError(null);
                    check_contNum = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return  check_contNum;
    }

    public static int validate_patientGender_checked(FragmentDoctorAddpatientBinding binding){
        if(binding.maleRadioBtnPatient.isChecked()){
            check_gender = 0;
            binding.femaleRadioBtnPatient.setError(null);
        } else if(binding.femaleRadioBtnPatient.isChecked()){
            check_gender = 1;
            binding.femaleRadioBtnPatient.setError(null);
        }else {
            binding.femaleRadioBtnPatient.setError("Select Gender");
            check_gender = -1;
        }
        return check_gender;
    }

    public static boolean validate_PatientName_edit(FragmentDoctorEditPatientBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";
        if (binding.patientnameEditInput.getText().toString().isEmpty())
        {
            binding.patientnameEditLayout.setError("Name field can not be empty");
            check_userName =false;
        }else if (binding.patientnameEditInput.getText().length() > 20){
            binding.patientnameEditLayout.setError("Name is too large!");
            check_userName =false;
        }else if(!binding.patientnameEditInput.getText().toString().trim().matches(checkFullName)){
            binding.patientnameEditLayout.setError("Invalid Name!");
            check_userName =false;
        }
        else {
            binding.patientnameEditLayout.setError(null);
            check_userName =true;
        }
        binding.patientnameEditInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.patientnameEditLayout.setError("Name field can not be empty");
                    check_userName =false;
                }else if (count > 20){
                    binding.patientnameEditLayout.setError("Name is too large!");
                    check_userName =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.patientnameEditLayout.setError("Invalid Name!");
                    check_userName =false;
                }
                else {
                    binding.patientnameEditLayout.setError(null);
                    check_userName =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_userName;
    }

    public static boolean validate_patientEmail_edit(FragmentDoctorEditPatientBinding binding){
        String checkEmail = "[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+";
        if(binding.patientEmailEditinput.getText().toString().isEmpty())
        {
            binding.patientEmailEditLayout.setError("Email can not be empty");
            check_email=false;
        }else if(!binding.patientEmailEditinput.getText().toString().matches(checkEmail))
        {
            binding.patientEmailEditLayout.setError("Invalid Email Address");
            check_email=false;
        }else {
            binding.patientEmailEditLayout.setError(null);
            check_email = true;
        }
        binding.patientEmailEditinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    binding.patientEmailEditLayout.setError("Email can not be empty");
                    check_email=false;
                }else if(!s.toString().matches(checkEmail))
                {
                    binding.patientEmailEditLayout.setError("Invalid Email Address");
                    check_email=false;
                }else {
                    binding.patientEmailEditLayout.setError(null);
                    check_email = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_email;
    }

    public static boolean validate_patientMedicalH_edit(FragmentDoctorEditPatientBinding binding){
        String checkHistory = "([a-zA-Z-0-9_ ])*$";
        if(!binding.patientMedicalHInputedit.getText().toString().matches(checkHistory))
        {
            binding.patientMedichalHInputLayoutedit.setError("Invalid History");
            medicalHistory=false;
        }else {
            binding.patientMedichalHInputLayoutedit.setError(null);
            medicalHistory = true;
        }

        binding.patientMedicalHInputedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().matches(checkHistory))
                {
                    binding.patientMedichalHInputLayoutedit.setError("Invalid History");
                    medicalHistory=false;
                }else {
                    binding.patientMedichalHInputLayoutedit.setError(null);
                    medicalHistory = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return medicalHistory;
    }

    public static int validate_patientGender_edit(FragmentDoctorEditPatientBinding binding){
        if(binding.maleRadioBtnPatientedit.isChecked()){
            check_gender = 0;
            binding.femaleRadioBtnPatientedit.setError(null);
        } else if(binding.femaleRadioBtnPatientedit.isChecked()){
            check_gender = 1;
            binding.femaleRadioBtnPatientedit.setError(null);
        }else {
            binding.femaleRadioBtnPatientedit.setError("Select Gender");
            check_gender = -1;
        }
        return check_gender;
    }

    public  static boolean check_patientNum_edit(FragmentDoctorEditPatientBinding binding){
        if(binding.patientNumberEditinput.getText().toString().isEmpty())
        {
            binding.patientNumberEditLayout.setError("Contact Number Can not be empty!");
            check_contNum = false;
        }else {
            binding.patientNumberEditLayout.setError(null);
            check_contNum = true;
        }
        binding.patientNumberEditinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    binding.patientNumberEditLayout.setError("Contact Number Can not be empty!");
                    check_contNum = false;
                }else {
                    binding.patientNumberEditLayout.setError(null);
                    check_contNum = true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return  check_contNum;
    }

    public static boolean check_patientAge_edit(FragmentDoctorEditPatientBinding binding){
        if(binding.patientAgeInputedit.getText().toString().isEmpty())
        {
            binding.patientAgeInputlayoutedit.setError("Age Field can not be empty!");
            check_age = false;
        }else {
            binding.patientAgeInputlayoutedit.setError(null);
            check_age = true;

        }
        binding.patientAgeInputedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    binding.patientAgeInputlayoutedit.setError("Age Field can not be empty!");
                    check_age = false;
                }else {
                    binding.patientAgeInputlayoutedit.setError(null);
                    check_age = true;

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() > 3){
                    binding.patientAgeInputlayoutedit.setError("Enter Correct Age!");
                    check_age = false;
                }
            }
        });
        return  check_age;
    }
    public static boolean validate_patientAddress_edit(FragmentDoctorEditPatientBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";
        if (binding.patientAddressInputedit.getText().toString().isEmpty())
        {
            binding.patientAddressInputlayoutedit.setError("Address field can bot be empty");
            check_address =false;
        }else if (binding.patientAddressInputedit.getText().length() > 20){
            binding.patientAddressInputlayoutedit.setError("Address is too large!");
            check_address =false;
        }else if(!binding.patientAddressInputedit.getText().toString().trim().matches(checkFullName)){
            binding.patientAddressInputlayoutedit.setError("Invalid Address!");
            check_address =false;
        }
        else {
            binding.patientAddressInputlayoutedit.setError(null);
            check_address =true;
        }
        binding.patientAddressInputedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.patientAddressInputlayoutedit.setError("Address field can bot be empty");
                    check_address =false;
                }else if (count > 20){
                    binding.patientAddressInputlayoutedit.setError("Address is too large!");
                    check_address =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.patientAddressInputlayoutedit.setError("Invalid Address!");
                    check_address =false;
                }
                else {
                    binding.patientAddressInputlayoutedit.setError(null);
                    check_address =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_address;
    }

    public static boolean validate_doctorName_edit(FragmentUpdateDoctorProfileBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";
        if (binding.doctorNameUpdateInput.getText().toString().isEmpty())
        {
            binding.usernameUpdateLayout.setError("Name field can not be empty");
            check_userName =false;
        }else if (binding.doctorNameUpdateInput.getText().length() > 20){
            binding.usernameUpdateLayout.setError("Name is too large!");
            check_userName =false;
        }else if(!binding.doctorNameUpdateInput.getText().toString().trim().matches(checkFullName)){
            binding.usernameUpdateLayout.setError("Invalid Name!");
            check_userName =false;
        }
        else {
            binding.usernameUpdateLayout.setError(null);
            check_userName =true;
        }
        binding.doctorNameUpdateInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.usernameUpdateLayout.setError("Name field can not be empty");
                    check_userName =false;
                }else if (count > 20){
                    binding.usernameUpdateLayout.setError("Name is too large!");
                    check_userName =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.usernameUpdateLayout.setError("Invalid Name!");
                    check_userName =false;
                }
                else {
                    binding.usernameUpdateLayout.setError(null);
                    check_userName =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_userName;
    }

    public static boolean validate_doctorAddress_edit(FragmentUpdateDoctorProfileBinding binding){
        String checkFullName = "[a-zA-Z-_]+([a-zA-Z-0-9_ ])*$";
        if (binding.doctorAddressUpdateIpnut.getText().toString().isEmpty())
        {
            binding.doctorAddressUpdateLayout.setError("Address field can bot be empty");
            check_address =false;
        }else if (binding.doctorAddressUpdateIpnut.getText().length() > 20){
            binding.doctorAddressUpdateLayout.setError("Address is too large!");
            check_address =false;
        }else if(!binding.doctorAddressUpdateIpnut.getText().toString().trim().matches(checkFullName)){
            binding.doctorAddressUpdateLayout.setError("Invalid Address!");
            check_address =false;
        }
        else {
            binding.doctorAddressUpdateLayout.setError(null);
            check_address =true;
        }
        binding.doctorAddressUpdateIpnut.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty())
                {
                    binding.doctorAddressUpdateLayout.setError("Address field can bot be empty");
                    check_address =false;
                }else if (count > 20){
                    binding.doctorAddressUpdateLayout.setError("Address is too large!");
                    check_address =false;
                }else if(!s.toString().trim().matches(checkFullName)){
                    binding.doctorAddressUpdateLayout.setError("Invalid Address!");
                    check_address =false;
                }
                else {
                    binding.doctorAddressUpdateLayout.setError(null);
                    check_address =true;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_address;
    }

    public static boolean validate_doctorEmail_edit(FragmentUpdateDoctorProfileBinding binding){
        String checkEmail = "[a-zA-Z0-9]+@[a-z]+\\.+[a-z]+";
        if(binding.doctorEmailUpdateInput.getText().toString().isEmpty())
        {
            binding.doctorEmailUpdateLayout.setError("Email can not be empty");
            check_email=false;
        }else if(!binding.doctorEmailUpdateInput.getText().toString().matches(checkEmail))
        {
            binding.doctorEmailUpdateLayout.setError("Invalid Email Address");
            check_email=false;
        }else {
            binding.doctorEmailUpdateLayout.setError(null);
            check_email = true;
        }
        binding.doctorEmailUpdateInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty())
                {
                    binding.doctorEmailUpdateLayout.setError("Email can not be empty");
                    check_email=false;
                }else if(!s.toString().matches(checkEmail))
                {
                    binding.doctorEmailUpdateLayout.setError("Invalid Email Address");
                    check_email=false;
                }else {
                    binding.doctorEmailUpdateLayout.setError(null);
                    check_email = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return check_email;
    }
}

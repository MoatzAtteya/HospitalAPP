package com.example.hospialapp;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.ActivityUserSignUpBinding;
import com.example.hospialapp.user.pojo.UsersMethods;
import com.example.hospialapp.user.pojo.users;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class SignUpViewModel extends ViewModel {

    public MutableLiveData<String> userEmail = new MutableLiveData<>();
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> userFullName = new MutableLiveData<>();
    public MutableLiveData<String> userPass = new MutableLiveData<>();
    public MutableLiveData<String> userPassConfirm = new MutableLiveData<>();
    public MutableLiveData<String> userAddress = new MutableLiveData<>();

    String register_date;

    private  boolean check_email, check_userName ,check_passwordConf ,check_address ,check_password , check_fullName ;
    int check_gender =-1 , userGender;

    Context context;
    ActivityUserSignUpBinding binding;

    public SignUpViewModel (Context context , ActivityUserSignUpBinding binding){
        this.context = context;
        this.binding = binding;
        validateInputs(binding);

    }

    public void setOnSignUpBtn()
    {
        validateInputs(binding);
        setAllUserData();

        if(isAllInputsCorrect()){
            setCurrentTimeDate();
            setUserGender();
            DataBaseHelper db = new DataBaseHelper(context.getApplicationContext());
            users user = new users(
                    userGender,
                    userName.getValue(),
                    userFullName.getValue(),
                    userEmail.getValue(),
                    userAddress.getValue(),
                    userPass.getValue(),
                    register_date
            );
            if(UsersMethods.check_username_exist(db,userName.getValue())){
                binding.usernameSignupLayout.setError("UserName is already exist");
            }else {
                UsersMethods.add_new_user(db,user);
                Toast.makeText(context.getApplicationContext(),"Signed Up",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(context.getApplicationContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
        }


    }

    private void setAllUserData() {
        userName.setValue(binding.usernameSignupInput.getText().toString());
        userFullName.setValue(binding.fullNameTextInput.getText().toString());
        userEmail.setValue(binding.EmailSignupInput.getText().toString());
        userAddress.setValue(binding.addressTextIpnut.getText().toString());
        userPass.setValue(binding.passwordSignupInput.getText().toString());
    }


    private void setCurrentTimeDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = Calendar.getInstance().getTime();
        register_date=formatter.format(date);
    }

    private void setUserGender(){
        userGender = check_gender;
    }

    private void validateInputs(ActivityUserSignUpBinding binding)
    {
        check_email=ValidationsMethods.validate_email_signUp(binding);
        check_fullName=ValidationsMethods.validate_fullName_signup(binding);
        check_userName=ValidationsMethods.validate_userName_signup(binding);
        check_address=ValidationsMethods.validate_address_signup(binding);
        check_password=ValidationsMethods.validate_password_signUp(binding);
        check_passwordConf=ValidationsMethods.check_confirm_password(binding);
        check_gender = ValidationsMethods.validate_gender_checked(binding);
    }

    private boolean isAllInputsCorrect()
    {
        return   check_fullName && check_userName &&
                check_address && check_password
                && check_passwordConf && check_email
                && (check_gender == 0 || check_gender == 1);

    }

}

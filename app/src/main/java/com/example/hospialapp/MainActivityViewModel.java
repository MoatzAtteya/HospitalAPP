package com.example.hospialapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.ActivityMainBinding;
import com.example.hospialapp.doctor.DashboardActivity;
import com.example.hospialapp.doctor.DoctorsMethods;
import com.example.hospialapp.user.pojo.UsersMethods;
import com.example.hospialapp.user.ui.UserDashboardActivity;

public class MainActivityViewModel extends ViewModel {

    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    private Context context;
    private ActivityMainBinding binding;
    private boolean isUserNameCorrect , isPassCorrect;

    public MainActivityViewModel (Context context , ActivityMainBinding binding){
        this.context = context;
        this.binding = binding;
        this.isUserNameCorrect = ValidationsMethods.validate_userName_login(binding);
        this.isPassCorrect = ValidationsMethods.validate_password_logIn(binding);
    }

    public void onLoginClick()
    {
        this.isUserNameCorrect = ValidationsMethods.validate_userName_login(binding);
        this.isPassCorrect = ValidationsMethods.validate_password_logIn(binding);

        if(isPassCorrect && isUserNameCorrect){
            boolean isUserExist;

            DataBaseHelper db = new DataBaseHelper(context.getApplicationContext());
            isUserExist = UsersMethods.check_user_exist(db , userName.getValue() , password.getValue() );
            if (isUserExist){
                Intent intent=new Intent(context.getApplicationContext(), UserDashboardActivity.class);
                intent.putExtra("userName", userName.getValue());
                intent.putExtra("password", password.getValue());
                context.startActivity(intent);
                ((Activity)context).finish();

                Toast.makeText(context.getApplicationContext(),"Logging in" , Toast.LENGTH_SHORT).show();

            }else
                Toast.makeText(context.getApplicationContext(),"Wrong username or password!" , Toast.LENGTH_SHORT).show();


        }else {
            Toast.makeText(context.getApplicationContext(),"Invalid Data!" , Toast.LENGTH_SHORT).show();
        }

    }

    public void onDoctorLoginClick()
    {
        this.isUserNameCorrect = ValidationsMethods.validate_userName_login(binding);
        this.isPassCorrect = ValidationsMethods.validate_password_logIn(binding);

        if(isPassCorrect && isUserNameCorrect){
            boolean isDoctorExist;
            userName.setValue(binding.usernameLoginInput.getText().toString());
            password.setValue(binding.passwordLoginInput.getText().toString());

            DataBaseHelper db = new DataBaseHelper(context.getApplicationContext());
            isDoctorExist = DoctorsMethods.check_doctorExist_login(db , userName.getValue() , password.getValue() );
            if (isDoctorExist){
                Intent doctorIntent = new Intent(context.getApplicationContext() , DashboardActivity.class);
                doctorIntent.putExtra("doctor_username" , userName.getValue());
                doctorIntent.putExtra("doctor_password" , password.getValue());
                context.startActivity(doctorIntent);
                ((Activity)context).finish();


            }else
                Toast.makeText(context.getApplicationContext(),"Wrong username or password!" , Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(context.getApplicationContext(),"Invalid Data!" , Toast.LENGTH_SHORT).show();
        }
    }

    public void onCreateBtnClick()
    {
        Intent intent = new Intent(context.getApplicationContext(),SignUpActivity.class);
        context.startActivity(intent);
    }

}



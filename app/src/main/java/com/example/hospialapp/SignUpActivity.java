package com.example.hospialapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.ActivityUserSignUpBinding;
import com.example.hospialapp.user.pojo.UsersMethods;
import com.example.hospialapp.user.pojo.users;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    ActivityUserSignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_sign_up);
        SignUpViewModel signUpViewModel= ViewModelProviders.of(this , new SignUpViewModeFactory(this,binding)).get(SignUpViewModel.class);
        binding.setSignUpViewModel(signUpViewModel);


    }
}
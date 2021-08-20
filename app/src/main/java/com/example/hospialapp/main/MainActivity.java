package com.example.hospialapp.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.hospialapp.MainActivityViewModel;
import com.example.hospialapp.R;
import com.example.hospialapp.databinding.ActivityMainBinding;
import com.example.hospialapp.main.MainActivityViewModelFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(this , new MainActivityViewModelFactory(this , binding)).get(MainActivityViewModel.class);
        binding.setMainActivityViewModel(mainActivityViewModel);


    }
}
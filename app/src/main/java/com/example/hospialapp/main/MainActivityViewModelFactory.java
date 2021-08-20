package com.example.hospialapp.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.hospialapp.MainActivityViewModel;
import com.example.hospialapp.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;
    private ActivityMainBinding binding;

    public MainActivityViewModelFactory(Context context, ActivityMainBinding binding) {
        this.context = context;
        this.binding = binding;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T)new MainActivityViewModel(context , binding);
    }
}

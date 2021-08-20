package com.example.hospialapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.hospialapp.databinding.ActivityUserSignUpBinding;

import org.jetbrains.annotations.NotNull;

public class SignUpViewModeFactory extends ViewModelProvider.NewInstanceFactory {

    Context context;
    ActivityUserSignUpBinding binding;

    public SignUpViewModeFactory(Context context, ActivityUserSignUpBinding binding) {
        this.context = context;
        this.binding = binding;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T)new SignUpViewModel(context,binding);
    }
}

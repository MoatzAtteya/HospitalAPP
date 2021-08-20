/*package com.example.hospialapp.doctor;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.hospialapp.SignUpViewModel;
import com.example.hospialapp.databinding.ActivityUserSignUpBinding;
import com.example.hospialapp.databinding.FragmentDoctorAddpatientBinding;

import org.jetbrains.annotations.NotNull;

public class AddPatientFactory extends ViewModelProvider.NewInstanceFactory {
    Context context;
    FragmentDoctorAddpatientBinding binding;
    int doc_id;

    public AddPatientFactory(Context context, FragmentDoctorAddpatientBinding binding , int doc_id) {
        this.context = context;
        this.binding = binding;
        this.doc_id = doc_id;
    }

    @NonNull
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T)new AddPatientViewModel(context , binding,doc_id);
    }
}
*/
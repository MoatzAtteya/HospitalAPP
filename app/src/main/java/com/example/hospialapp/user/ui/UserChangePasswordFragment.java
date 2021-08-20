package com.example.hospialapp.user.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentUserChangePasswordBinding;
import com.example.hospialapp.user.pojo.UsersMethods;
import com.google.android.material.snackbar.Snackbar;

public class UserChangePasswordFragment extends Fragment {
    FragmentUserChangePasswordBinding binding;
    String username, date, password;
    int id;
    DataBaseHelper db;
    boolean check_new_password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentUserChangePasswordBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        db = new DataBaseHelper(requireContext());

        Bundle bundle=getArguments();
        id = Integer.parseInt(bundle.getString("ID"));
        username = bundle.getString("USERNAME");
        date = bundle.getString("DATE");
        password = bundle.getString("PASSWORD");

        binding.usernameTextUpdate.setText(username);
        binding.regDateUpdate.setText(date);


        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current_password = String.valueOf(binding.currentPassUpdateInput.getText());
                String new_entered_password = String.valueOf(binding.newPassUpdateInput.getText());
                String confirm_new_password = String.valueOf(binding.confirmPassUpdateIpnut.getText());
                //System.out.println(current_password +" " + password + " " + confirm_new_password + " " + new_entered_password);

                if ((current_password.equals(password)) && (new_entered_password.equals(confirm_new_password))){
                    UsersMethods.update_userPassword(db , new_entered_password , id);
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Updated" , Snackbar.LENGTH_SHORT).show();
                }
                else
                Snackbar.make(getActivity().findViewById(android.R.id.content) , "Check Your Entered Data" , Snackbar.LENGTH_SHORT).show();

            }
        });

        return  view;
    }
}
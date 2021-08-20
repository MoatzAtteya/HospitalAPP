package com.example.hospialapp.user.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.ValidationsMethods;
import com.example.hospialapp.databinding.FragmentUpdateUserProfileBinding;
import com.example.hospialapp.user.pojo.UsersMethods;

public class UserUpdateProfileFragment extends Fragment {

    FragmentUpdateUserProfileBinding binding;
    String username , address , date , email ;
    int id;
    DataBaseHelper db;
    boolean check_email,check_username,check_address;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentUpdateUserProfileBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        db = new DataBaseHelper(requireContext());

        Bundle bundle=getArguments();
         id = Integer.parseInt(bundle.getString("ID"));
         username = bundle.getString("USERNAME");
         address = bundle.getString("ADDRESS");
         date = bundle.getString("DATE");
         email = bundle.getString("EMAIL");

        binding.usernameTextUpdate.setText(username);
        binding.usernameUpdateInput.setText(username);
        binding.regDateUpdate.setText(date);
        binding.EmailUpdateInput.setText(email);
        binding.addressUpdateIpnut.setText(address);

        check_username= ValidationsMethods.validate_userName_update(binding);
        check_address=ValidationsMethods.validate_address_update(binding);
        check_email = ValidationsMethods.validate_email_update(binding);

        binding.updateBtn.setOnClickListener(v -> {
            check_username=ValidationsMethods.validate_userName_update(binding);
            check_address=ValidationsMethods.validate_address_update(binding);
            check_email = ValidationsMethods.validate_email_update(binding);
            if(check_username && check_address &&check_email){
                username = binding.usernameUpdateInput.getText().toString();
                email = binding.EmailUpdateInput.getText().toString();
                address = binding.addressUpdateIpnut.getText().toString();
                if(UsersMethods.check_username_exist(db,username,id)){
                    binding.usernameUpdateInput.setError("username is already taken!");
                }else {
                    UsersMethods.update_userData(db,username , email , address , id);
                    Toast.makeText(requireContext(),"Updated",Toast.LENGTH_SHORT).show();
                }

            }else
                Toast.makeText(requireContext(),"Enter Valid Data!",Toast.LENGTH_SHORT).show();

        });



        return view;
    }

}
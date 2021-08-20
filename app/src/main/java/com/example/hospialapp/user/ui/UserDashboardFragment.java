package com.example.hospialapp.user.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hospialapp.R;
import com.example.hospialapp.databinding.FragmentUserDashboardBinding;

public class UserDashboardFragment extends Fragment {
    FragmentUserDashboardBinding binding;
    String username,fullname,address,password,date,email;
    int id , gender;
    Bundle AllUserDataBundle;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,Bundle savedInstanceState) {
        binding=FragmentUserDashboardBinding.inflate(inflater,container,false);
        View view=binding.getRoot();


        Bundle bundle=getArguments();
        if(bundle !=null){
             id = Integer.parseInt(bundle.getString("ID"));
             username = bundle.getString("USERNAME");
             fullname = bundle.getString("FULLNAME");
             address = bundle.getString("ADDRESS");
             password = bundle.getString("PASSWORD");
             date = bundle.getString("DATE");
             email = bundle.getString("EMAIL");
             gender = Integer.parseInt(bundle.getString("GENDER"));
        }


        binding.bookAppoinmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllUserDataBundle = CreateUserBundle();
                UserBookAppoinmentFragment baf=new UserBookAppoinmentFragment();
                baf.setArguments(AllUserDataBundle);
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, baf,null).addToBackStack(null).commit();
            }
        });
        
        binding.upateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllUserDataBundle = CreateUserBundle();
                UserUpdateProfileFragment baf=new UserUpdateProfileFragment();
                baf.setArguments(AllUserDataBundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, baf,null).addToBackStack(null).commit();

            }
        });
        
        binding.viewAppoinmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllUserDataBundle = CreateUserBundle();
                UserViewAppoinmentFragment userViewAppoinmentFragment = new UserViewAppoinmentFragment();
                userViewAppoinmentFragment.setArguments(AllUserDataBundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , userViewAppoinmentFragment,null).addToBackStack(null).commit();
            }
        });

        return view;
    }

    private Bundle CreateUserBundle(){
        Bundle bundle=new Bundle();
        bundle.putString("USERNAME" , username);
        bundle.putString("FULLNAME" , fullname);
        bundle.putString("ID" , String.valueOf(id));
        bundle.putString("GENDER" , String.valueOf(gender));
        bundle.putString("DATE" , date);
        bundle.putString("EMAIL" , email);
        bundle.putString("PASSWORD" , password);
        bundle.putString("ADDRESS" , address);
        return bundle;
    }
}

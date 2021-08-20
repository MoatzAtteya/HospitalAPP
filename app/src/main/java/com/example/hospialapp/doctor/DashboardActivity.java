package com.example.hospialapp.doctor;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.hospialapp.R;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.main.MainActivity;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView name_textView_nav , email_textView_nav;
    Bundle doctorDataBundel;
    Doctors doctor;
    Fragment current_fragment;
    String logInName ,logInPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_navigation_drawer);




        Intent intent = getIntent();
        if(intent != null){
             logInPass = intent.getStringExtra("doctor_password");
             logInName = intent.getStringExtra("doctor_username");
        }

        toolbar=findViewById(R.id.doctor_mainActivity_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout_doctor);
        navigationView = (NavigationView)  findViewById(R.id.navigation_view_doctor);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.opening_nav , R.string.closing_nav);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        DataBaseHelper db=new DataBaseHelper(this);
        doctor = DoctorsMethods.getDoctorByUserAndPass(db , logInName , logInPass );

        View headerView = navigationView.getHeaderView(0);
        name_textView_nav = headerView.findViewById(R.id.doctorname_nav_text);
        email_textView_nav = headerView.findViewById(R.id.doctor_email_nav_text);
        name_textView_nav.setText(doctor.getName());
        email_textView_nav.setText(doctor.getEmail());

        doctorDataBundel = createDoctorBundle();
        current_fragment = new DashboardFragment();
        current_fragment.setArguments(doctorDataBundel);
        getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, current_fragment).commit();

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if( current_fragment instanceof DashboardFragment){
            super.onBackPressed();
        }else
            showDashBoardFragment();


    }

    private void showDashBoardFragment() {
        current_fragment=new DashboardFragment();
        current_fragment.setArguments(doctorDataBundel);
        getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer,current_fragment ).commit();
    }

    private Bundle createDoctorBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("USERNAME" , doctor.getName());
        bundle.putString("PASSWORD" , doctor.getPassword());
        bundle.putString("EMAIL" , doctor.getEmail());
        bundle.putString("ADDRESS" , doctor.getAddress());
        bundle.putString("FEES" , String.valueOf(doctor.getDocFees()));
        bundle.putString("CONTNUM" , String.valueOf(doctor.getContactNo()));
        bundle.putString("ID" , String.valueOf(doctor.getID()));
        bundle.putString("SPECID" , String.valueOf(doctor.getSpecializationID()));
        bundle.putString("REGDATE" , doctor.getRegisterDate());
        return  bundle;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.doctor_AppoinmentHistory_nav_btn:
                current_fragment = new ViewAppointments();
                current_fragment.setArguments(doctorDataBundel);
                getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, current_fragment).commit();
                break;
            case R.id.doctor_dashboard_nav_btn:
                current_fragment = new DashboardFragment();
                current_fragment.setArguments(doctorDataBundel);
                getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, current_fragment).commit();
                break;

            case R.id.doctor_AddPatient_nav_btn:
                current_fragment = new AddPatient();
                current_fragment.setArguments(doctorDataBundel);
                getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, current_fragment).commit();
                break;

            case R.id.doctor_EditPatient_nav_btn:
                current_fragment = new ManagePatient();
                current_fragment.setArguments(doctorDataBundel);
                getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, current_fragment).commit();
                break;

            case R.id.doctor_changePass_nav_btn:
                current_fragment = new ChangeDoctorPassword();
                current_fragment.setArguments(doctorDataBundel);
                getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, current_fragment).commit();
                break;

            case R.id.doctor_myProfile_nav_btn:
                current_fragment = new UpdateDoctorProfile();
                current_fragment.setArguments(doctorDataBundel);
                getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer, current_fragment).commit();
                break;

            case R.id.doctor_logOut_nav_btn:
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
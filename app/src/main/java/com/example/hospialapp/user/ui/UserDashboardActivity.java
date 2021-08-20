package com.example.hospialapp.user.ui;
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
import android.widget.Toast;

import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.main.MainActivity;
import com.example.hospialapp.R;
import com.example.hospialapp.user.pojo.UsersMethods;
import com.example.hospialapp.user.pojo.users;
import com.google.android.material.navigation.NavigationView;

public class UserDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView name_textField,email_textField;
    users user;
    Bundle UserDataBundle;
    Fragment current_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_navigation_drawer_user);

        Intent intent=getIntent();
        String name = intent.getStringExtra("userName");
        String password=intent.getStringExtra("password");

        //attaching the toolbar in dashboard activity with navigation_drawer_user.
        toolbar=(androidx.appcompat.widget.Toolbar) findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout_user);
        navigationView = (NavigationView) findViewById(R.id.navigation_view_user);
        //set listener for navigations items.
        navigationView.setNavigationItemSelectedListener(this);

        //attaching the toolbar with the navigation menu
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.opening_nav , R.string.closing_nav);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //getting the user full_data from the database by name and pass
        DataBaseHelper db=new DataBaseHelper(this);
        user= UsersMethods.get_user_byNameAndPassword(db,name,password);

        //get the navigation_header from navigation_header_user activity.
        View headerView = navigationView.getHeaderView(0);
        name_textField=(TextView) headerView.findViewById(R.id.username_nav_text);
        email_textField=(TextView) headerView.findViewById(R.id.email_nav_text);
        name_textField.setText(user.getUserName());
        email_textField.setText(user.getEmail());

        //make dashboard fragment the default fragment.

        UserDataBundle = CreateUserBundle();
        current_fragment=new UserDashboardFragment();
        current_fragment.setArguments(UserDataBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, current_fragment).commit();


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if (current_fragment instanceof UserDashboardFragment){
            super.onBackPressed();
        }else
            showDashBoardFragment();
    }

    private void showDashBoardFragment() {
        current_fragment=new UserDashboardFragment();
        current_fragment.setArguments(UserDataBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,current_fragment ).commit();

    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.dashboard_nav_btn:
                current_fragment=new UserDashboardFragment();
                current_fragment.setArguments(UserDataBundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,current_fragment ).commit();
                break;

            case R.id.bookAppoinment_nav_btn:
                current_fragment=new UserBookAppoinmentFragment();
                current_fragment.setArguments(UserDataBundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, current_fragment).commit();
                break;

            case R.id.AppoinmentHistory_nav_btn:
                current_fragment = new UserViewAppoinmentFragment();
                current_fragment.setArguments(UserDataBundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , current_fragment).commit();

                break;

            case R.id.medicalHistory_nav_btn:
                Toast.makeText(getApplicationContext(),"medical history",Toast.LENGTH_SHORT).show();
                break;

            case R.id.myProfile_nav_btn:
                current_fragment=new UserUpdateProfileFragment();
                current_fragment.setArguments(UserDataBundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, current_fragment).commit();
                break;

            case R.id.changePass_nav_btn:
                current_fragment=new UserChangePasswordFragment();
                current_fragment.setArguments(UserDataBundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, current_fragment).commit();


                break;
            case R.id.logOut_nav_btn:
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(),"log out",Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    private Bundle CreateUserBundle(){
        Bundle DataBundle = new Bundle();
        DataBundle.putString("USERNAME" , user.getUserName());
        DataBundle.putString("FULLNAME" , user.getFullName());
        DataBundle.putString("ID" , String.valueOf(user.getId()));
        DataBundle.putString("GENDER" , String.valueOf(user.getGender()));
        DataBundle.putString("DATE" , user.getRegister_date());
        DataBundle.putString("EMAIL" , user.getEmail());
        DataBundle.putString("PASSWORD" , user.getPassword());
        DataBundle.putString("ADDRESS" , user.getAddress());
        return DataBundle;
    }
}
package com.example.hospialapp.user.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.doctor.DoctorsMethods;
import com.example.hospialapp.doctor.Doctorspecialization_Methods;
import com.example.hospialapp.databinding.FragmentUserBookappoinmentBinding;
import com.example.hospialapp.appointments.Appointment;
import com.example.hospialapp.appointments.AppointmentMethods;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserBookAppoinmentFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private FragmentUserBookappoinmentBinding binding;
    String doctor_special,date,time ,doctor_name;
    String [] specialist_names = {"Gynecologis/Obsterician" , "General Physician" , "Dermatologist" , "Homeopath" ,"Ayurveda" ,"Dentist", "Ear-Nose-Throat(Ent) Specialist", "Demo test" ,  "Bones Specialist" , "Dermatologist" ," Cardiology"};
    int spcialization_id ,doc_fees =0 , doc_id=0;
    DataBaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding=FragmentUserBookappoinmentBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        db = new DataBaseHelper(requireContext());

        //getting user data by intent from DashboardActivity.
        Bundle bundle=getArguments();
        int id = Integer.parseInt(bundle.getString("ID"));


        //setting up  spinners data and adapter.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item , specialist_names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.doctorspecialSpinner.setAdapter(adapter);
        binding.doctorspecialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctor_special = parent.getItemAtPosition(position).toString();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.DKGRAY);
                ((TextView) parent.getChildAt(0)).setTextSize(20);

                spcialization_id = Doctorspecialization_Methods.getID_specialByName(db, doctor_special);
                String[] names1 = DoctorsMethods.get_doctorsBy_specialID(db, spcialization_id);
                //Toast.makeText(parent.getContext(), doctor_special, Toast.LENGTH_SHORT).show();

                String[] doctor_names = filter_array(names1);
                ArrayAdapter ad=new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item ,doctor_names);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.doctorsSpinner.setAdapter(ad);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.doctorsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctor_name = parent.getItemAtPosition(position).toString();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.DKGRAY);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
               // Toast.makeText(parent.getContext(),doctor_name,Toast.LENGTH_SHORT).show();
                doc_fees = DoctorsMethods.get_docFees_byName(db,doctor_name);
                doc_id = DoctorsMethods.get_doctorID_byName(db,doctor_name);
                binding.consultancyFees.setText(String.valueOf(doc_fees));
                //System.out.println("doctor id isssssssssssssss" + doc_id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.dateBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
              showDatePickerDailog();
            }
        });

        binding.timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d = Calendar.getInstance().getTime();
        String register_date=formatter.format(d);
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appointment userAppoinment = new Appointment(doc_fees,doctor_name,doctor_special,date,time,id,register_date);
                if(AppointmentMethods.add_appoinment(db, userAppoinment) != -1){
                    Toast.makeText(requireContext(),"Appointment Submited.",Toast.LENGTH_LONG).show();
                } else
                {
                    Toast.makeText(requireContext(),"Something went wrong!.",Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }


    public String[] filter_array(String[] arr){
        String[] doc_names2;
        int counter=0;
        for (int i=0 ; i<arr.length ; i++){
            if(arr[i] != null){
                counter++;
            }
        }
        doc_names2 = new String[counter];
        //System.out.println(counter + " ////");
        int k=0;
        for (int i=0 ; i<doc_names2.length;i++){
            if (arr != null)
                doc_names2[k++] = arr[i];
        }
        return doc_names2;
    }

    // creating an instance of calendar and obj from DatePickerDialog class to create a Date dialog picker
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDatePickerDailog(){
        Context context = requireContext();
        DatePickerDialog datePickerDialog = new DatePickerDialog( context,this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    // creating an instance of calendar and obj from TimePickerDialog class to create a time dialog picker
    private void showTimePickerDialog(){
        Context context = requireContext();
        Calendar c=Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(context , this ,c.get(Calendar.HOUR_OF_DAY) , c.get(Calendar.MINUTE) , DateFormat.is24HourFormat(context));
        timePickerDialog.show();
    }

    //ondateset click listener to specify what will happen after selecting date
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = month + "/" + dayOfMonth + "/" + year;
        binding.dateBtn.setText(date);
    }

    //ontimeset click listener to specify what will happen after selecting time
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        time=hourOfDay + ":" + minute;
        binding.timeBtn.setText(time);
    }
}

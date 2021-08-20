package com.example.hospialapp.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospialapp.R;
import com.example.hospialapp.database.DataBaseHelper;
import com.example.hospialapp.databinding.FragmentDoctorManagePatientBinding;
import com.example.hospialapp.patient.Patient;
import com.example.hospialapp.patient.PatientMethods;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ManagePatient extends Fragment {
    FragmentDoctorManagePatientBinding binding;
    ManagePatientAdapter adapter;
    DataBaseHelper db;
    List<Patient> patientList = new ArrayList<>();
    int doc_id;

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDoctorManagePatientBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        Bundle bundle = getArguments();
        doc_id = Integer.parseInt(bundle.getString("ID"));

        binding.managePatientRecycleView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.managePatientRecycleView.setHasFixedSize(true);

        db = new DataBaseHelper(requireContext());
        patientList = PatientMethods.get_allPatients_byDocID(db , doc_id);
        updateUI();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove( RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Patient patient = adapter.getPatientAt(position);
                int patientID = patient.getId();
                if(PatientMethods.delete_patient_byID(db,patientID)){
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Patient Deleted" , Snackbar.LENGTH_SHORT).show();
                }else
                    Snackbar.make(getActivity().findViewById(android.R.id.content) , "Error while deleting!" , Snackbar.LENGTH_SHORT).show();


            }
        }).attachToRecyclerView(binding.managePatientRecycleView);

        adapter.setOnItemClickListener(new ManagePatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient patient) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("ID" , String.valueOf(patient.getId()));
                bundle1.putString("FULLNAME" , patient.getFull_name());
                bundle1.putString("ADDRESS" , patient.getAddress());
                bundle1.putString("AGE" , String.valueOf(patient.getAge()));
                bundle1.putString("NUMBER" , patient.getContact_number());
                bundle1.putString("MEDICALH" , patient.getMedical_history());
                bundle1.putString("EMAIL" , patient.getEmail());
                bundle1.putString("GENDER" , String.valueOf(patient.getGender()));
                EditPatient editPatient = new EditPatient();
                editPatient.setArguments(bundle1);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.doctor_fragmentContainer , editPatient,null).addToBackStack(null).commit();
            }
        });

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.manage_patient_menu , menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchItem = menu.findItem(R.id.search_patient);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_patients:
                PatientMethods.delete_allPatients(db);
                Snackbar.make(getActivity().findViewById(android.R.id.content) , "All patients deleted" , Snackbar.LENGTH_SHORT).show();
                adapter.deletehRecyclyViewList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUI() {
        if(adapter == null){
            adapter = new ManagePatientAdapter(patientList , requireContext());
            binding.managePatientRecycleView.setAdapter(adapter);
        }else
            adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}

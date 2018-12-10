package com.example.mihailkalchev.criminalintent.views;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mihailkalchev.criminalintent.CrimePagerActivity;
import com.example.mihailkalchev.criminalintent.R;
import com.example.mihailkalchev.criminalintent.models.Crime;
import com.example.mihailkalchev.criminalintent.models.CrimeLab;

import java.util.ArrayList;
import java.util.Objects;

public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> crimes;
    private static final String TAG = "CrimeListFragment";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        crimes = CrimeLab.getCrimeLab(getActivity()).getCrimes();

//        ArrayAdapter<Crime> arrayAdapter = new ArrayAdapter<>(getActivity(),
//                R.layout.support_simple_spinner_dropdown_item, crimes);
//        setListAdapter(arrayAdapter);

        CrimeAdapter adapter = new CrimeAdapter(crimes);
        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime crime = ((CrimeAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(),CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getmId());
        startActivity(i);
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(Objects.requireNonNull(getActivity()), 0, crimes);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            Crime crime = getItem(position);

            TextView titleTextView = convertView.findViewById(R.id.crime_list_title);
            titleTextView.setText(crime.getmTitle());

            TextView dateTextView = convertView.findViewById(R.id.crime_list_date);
            dateTextView.setText(crime.getDate().toString());

            CheckBox solvedCheckBox = convertView.findViewById(R.id.crime_list_solved_checkbox);
            solvedCheckBox.setChecked(crime.isSolved());

            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }
}

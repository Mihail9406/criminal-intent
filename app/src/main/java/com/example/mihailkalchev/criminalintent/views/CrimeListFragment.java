package com.example.mihailkalchev.criminalintent.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mihailkalchev.criminalintent.R;
import com.example.mihailkalchev.criminalintent.models.Crime;
import com.example.mihailkalchev.criminalintent.models.CrimeLab;

import java.util.ArrayList;

public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> crimes;
    private static final String TAG = "CrimeListFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        crimes = CrimeLab.getCrimeLab(getActivity()).getCrimes();

        ArrayAdapter<Crime> arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.support_simple_spinner_dropdown_item, crimes);
        setListAdapter(arrayAdapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime crime = (Crime) getListAdapter().getItem(position);
        Log.d(TAG, crime.getmTitle() + " was clicked");
    }

}

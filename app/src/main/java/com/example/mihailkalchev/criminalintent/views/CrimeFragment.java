package com.example.mihailkalchev.criminalintent.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.mihailkalchev.criminalintent.R;
import com.example.mihailkalchev.criminalintent.models.Crime;
import com.example.mihailkalchev.criminalintent.models.CrimeLab;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private Crime mCrime;
    private EditText titleField;
    private Button dateButton;
    private CheckBox solvedCheckBox;
    public static final String EXTRA_CRIME_ID = "com.example.mihailkalchev.criminalintent.crime_id";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.getCrimeLab(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        titleField = v.findViewById(R.id.crime_title_hint);
        titleField.setText(mCrime.getmTitle());
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        String format = "EEEE, MMM d, yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        dateButton = v.findViewById(R.id.crime_date);
        dateButton.setText(sdf.format(mCrime.getDate()));
        dateButton.setEnabled(false);

        solvedCheckBox = v.findViewById(R.id.crime_solved);
        solvedCheckBox.setChecked(mCrime.isSolved());
        solvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    public static CrimeFragment newInstance(UUID crimeID) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeID);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }
}


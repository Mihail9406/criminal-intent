package com.example.mihailkalchev.criminalintent;

import android.support.v4.app.Fragment;

import com.example.mihailkalchev.criminalintent.views.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}

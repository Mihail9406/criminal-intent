package com.example.mihailkalchev.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.mihailkalchev.criminalintent.models.Crime;
import com.example.mihailkalchev.criminalintent.models.CrimeLab;
import com.example.mihailkalchev.criminalintent.views.CrimeFragment;

import java.util.ArrayList;
import java.util.UUID;

public class CrimePagerActivity extends FragmentActivity {
    private ViewPager viewPager;
    private ArrayList<Crime> crimes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);

        crimes = CrimeLab.getCrimeLab(this).getCrimes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Crime crime = crimes.get(i);
                return CrimeFragment.newInstance(crime.getmId());
            }

            @Override
            public int getCount() {
                return crimes.size();
            }
        });

        UUID crimeID = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i = 0; i < crimes.size(); i++) {
            if(crimes.get(i).getmId().equals(crimeID)){
                viewPager.setCurrentItem(i);
                break;
            }
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Crime crime = crimes.get(i);
                if(crime.getmTitle() != null){
                    setTitle(crime.getmTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}

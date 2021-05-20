package edu.slcc.asdv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity
{
    private static final String EXTRA_CRIME_ID = "crime_id";


    private ViewPager2 mViewPager;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId)
    {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = findViewById(R.id.crime_view_pager);
        mViewPager.setClipToPadding(false);
        mViewPager.setPageTransformer(new MarginPageTransformer(this.getResources().getDimensionPixelOffset(R.dimen.margin)));


        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStateAdapter(fragmentManager, this.getLifecycle())
        {
            @Override
            public int getItemCount()
            {
                return mCrimes.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position)
            {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

        });

        for (int i = 0; i < mCrimes.size(); i++)
        {
            if
            (mCrimes.get(i).getId().equals(crimeId))
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public void jumpToFirstPage(View view)
    {
        mViewPager.setCurrentItem(0);
    }

    public void jumpToLastPage(View view)
    {
        mViewPager.setCurrentItem(mCrimes.size() - 1);
    }
}
package org.example.fire_test.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import org.example.fire_test.activities.MyPageFragment;
import org.example.fire_test.activities.NoticeFragment;
import org.example.fire_test.activities.RecommendFragment;
import org.example.fire_test.activities.SearchFragment;
import org.example.fire_test.activities.ShareFragment;

/**
 * Created by takuya on 2/20/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                RecommendFragment tab1 = new RecommendFragment();
                return tab1;
            case 1:
                NoticeFragment tab2 = new NoticeFragment();
                return tab2;
            case 2:
                MyPageFragment tab3 = new MyPageFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return mNumOfTabs;
    }
}

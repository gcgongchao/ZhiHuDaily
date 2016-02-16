package com.general.android.zhihudaily.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.general.android.zhihudaily.fragment.MessageListFragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by GeneralAndroid
 */
public class MessageListFragmentAdapter extends FragmentPagerAdapter{

    private ArrayList<String> dates;
    public MessageListFragmentAdapter(FragmentManager fm,ArrayList<String> dates) {
        super(fm);
        this.dates=dates;
    }

    @Override
    public Fragment getItem(int position) {
        return MessageListFragment.newInstance(dates.get(position));
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Calendar displayDate = Calendar.getInstance();
        displayDate.add(Calendar.DAY_OF_YEAR, -position);

        return DateFormat.getDateInstance().format(displayDate.getTime());

    }
}

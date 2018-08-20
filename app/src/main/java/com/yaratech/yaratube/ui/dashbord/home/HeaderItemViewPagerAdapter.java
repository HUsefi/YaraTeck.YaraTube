package com.yaratech.yaratube.ui.dashbord.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yaratech.yaratube.data.model.Headeritem;

import java.util.ArrayList;
import java.util.List;

public class HeaderItemViewPagerAdapter extends FragmentPagerAdapter {
    private List<Headeritem> headeritems = new ArrayList<>();

    public void setHeaderItems(List<Headeritem> headeritems) {
        this.headeritems = headeritems;
        notifyDataSetChanged();
    }

    public HeaderItemViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return HeaderHolderFragment.newInstance(headeritems.get(position).getFeatureAvatar().getXxhdpi());
    }

    @Override
    public int getCount() {
        if(headeritems==null)
            return 0;
        return headeritems.size();
    }
}

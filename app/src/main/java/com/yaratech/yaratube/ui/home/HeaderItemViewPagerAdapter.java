package com.yaratech.yaratube.ui.home;

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
        return PlaceHolderFragment.newInstance(headeritems.get(position).getAvatar().getXxxdpi());
    }

    @Override
    public int getCount() {
        return headeritems.size();
    }
}

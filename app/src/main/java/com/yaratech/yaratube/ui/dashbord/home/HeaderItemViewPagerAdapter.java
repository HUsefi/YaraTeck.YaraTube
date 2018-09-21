package com.yaratech.yaratube.ui.dashbord.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yaratech.yaratube.data.model.Headeritem;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class HeaderItemViewPagerAdapter extends FragmentPagerAdapter {
    private List<Product> headeritems = new ArrayList<>();

    public void setHeaderItems(List<Product> headeritems) {
        this.headeritems = headeritems;
        notifyDataSetChanged();
    }

    public HeaderItemViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return HeaderHolderFragment.newInstance(headeritems.get(position).getFeatureAvatar().getXxhdpi()
                , headeritems.get(position));
    }

    @Override
    public int getCount() {
        if (headeritems == null)
            return 0;
        return headeritems.size();
    }
}

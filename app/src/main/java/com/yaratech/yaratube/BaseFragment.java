package com.yaratech.yaratube;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.ui.category.CategoryFragment;
import com.yaratech.yaratube.ui.home.HomeFragment;


public class BaseFragment extends Fragment {

    private BottomNavigationView mBottomNavigationView;
    public BaseFragment() {
        // Required empty public constructor
    }

    public static BaseFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBottomNavigationView=view.findViewById(R.id.navigation);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragment(HomeFragment.newInstance());
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        item.setChecked(true);
                        setFragment(HomeFragment.newInstance());
                        break;
                    case R.id.navigation_category:
                        item.setChecked(true);
                        setFragment(CategoryFragment.newInstance());
                        break;
                }
                return false;
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
    }
}

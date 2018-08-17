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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.ui.category.CategoryFragment;
import com.yaratech.yaratube.ui.home.HomeFragment;


public class BaseFragment extends Fragment {

    private BottomNavigationView mBottomNavigationView;
    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private FragmentManager fragmentManager;
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
        //setFragment(HomeFragment.newInstance());
        setHomeFragment();
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        item.setChecked(true);
                       // setFragment(HomeFragment.newInstance());
                        setHomeFragment();
                        break;
                    case R.id.navigation_category:
                        item.setChecked(true);
                       // setFragment(CategoryFragment.newInstance());
                        setCategoryFragment();
                        break;
                }
                return false;
            }
        });

    }

    private void setHomeFragment() {
        if (homeFragment == null) {
            if (categoryFragment != null && categoryFragment.isVisible()){
                fragmentManager.beginTransaction().hide(categoryFragment).commit();
            }
            homeFragment = HomeFragment.newInstance();
            fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, homeFragment).commit();

        }else if (!homeFragment.isVisible()){
            Log.e("home ",homeFragment.isVisible()+" "+categoryFragment.isVisible());
            fragmentManager.beginTransaction().hide(categoryFragment).commit();
            fragmentManager.beginTransaction().show(homeFragment).commit();
        }
    }
    private void setCategoryFragment() {
        if (categoryFragment == null) {
            if (homeFragment !=null && homeFragment.isVisible()){
                fragmentManager.beginTransaction().hide(homeFragment).commit();
            }
            categoryFragment = CategoryFragment.newInstance();
            fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit();
        }else if (!categoryFragment.isVisible()){
            Log.e("category ",homeFragment.isVisible()+" "+categoryFragment.isVisible());
            fragmentManager.beginTransaction().hide(homeFragment).commit();
            fragmentManager.beginTransaction().show(categoryFragment).commit();
        }
    }



//    private void setFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getChildFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
//    }
}

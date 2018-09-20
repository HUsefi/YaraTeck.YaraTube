package com.yaratech.yaratube.ui.dashbord;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.dashbord.category.CategoryFragment;
import com.yaratech.yaratube.ui.dashbord.home.HomeFragment;
import com.yaratech.yaratube.ui.dashbord.more.MoreMenuFragment;


public class BaseFragment extends Fragment {

    private BottomNavigationView mBottomNavigationView;
    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private FragmentManager fragmentManager;
    private MoreMenuFragment moreMenuFragment;
    public static final String BASE_FRAGMENT_TAG = "BaseFragment";

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
        mBottomNavigationView = view.findViewById(R.id.navigation);
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
                    case R.id.navigation_more:
                        item.setChecked(true);
                        setMoreMenuFragment();
                }
                return false;
            }
        });

    }

    private void setHomeFragment() {
        if (homeFragment == null) {
            if (categoryFragment != null && categoryFragment.isVisible()) {
                fragmentManager.beginTransaction().hide(categoryFragment).commit();
            }
            if (moreMenuFragment != null && moreMenuFragment.isVisible()) {
                fragmentManager.beginTransaction().hide(moreMenuFragment).commit();
            }
            homeFragment = HomeFragment.newInstance();
            fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, homeFragment).commit();

        } else if (!homeFragment.isVisible()) {
            if (categoryFragment != null)
                fragmentManager.beginTransaction().hide(categoryFragment).commit();
            if (moreMenuFragment != null)
                fragmentManager.beginTransaction().hide(moreMenuFragment).commit();
            fragmentManager.beginTransaction().show(homeFragment).commit();
        }
    }

    private void setCategoryFragment() {
        if (categoryFragment == null) {
            if (homeFragment != null && homeFragment.isVisible()) {
                fragmentManager.beginTransaction().hide(homeFragment).commit();
            }
            if (moreMenuFragment != null && moreMenuFragment.isVisible()) {
                fragmentManager.beginTransaction().hide(moreMenuFragment).commit();
            }

            categoryFragment = CategoryFragment.newInstance();
            fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, categoryFragment).commit();
        } else if (!categoryFragment.isVisible()) {
            if (homeFragment != null)
            fragmentManager.beginTransaction().hide(homeFragment).commit();
            if (moreMenuFragment != null)
            fragmentManager.beginTransaction().hide(moreMenuFragment).commit();
            fragmentManager.beginTransaction().show(categoryFragment).commit();
        }
    }

    private void setMoreMenuFragment() {


        if (moreMenuFragment == null) {
            if (homeFragment != null && homeFragment.isVisible()) {
                fragmentManager.beginTransaction().hide(homeFragment).commit();
            }
            if (categoryFragment != null && categoryFragment.isVisible()) {
                fragmentManager.beginTransaction().hide(categoryFragment).commit();
            }

            moreMenuFragment = MoreMenuFragment.newInstance();
            fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, moreMenuFragment).commit();
        } else if (!moreMenuFragment.isVisible()) {
            if (homeFragment != null)
            fragmentManager.beginTransaction().hide(homeFragment).commit();
            if (categoryFragment != null)
            fragmentManager.beginTransaction().hide(categoryFragment).commit();
            fragmentManager.beginTransaction().show(moreMenuFragment).commit();
        }


//            if (homeFragment.isVisible() ) {
//                fragmentManager.beginTransaction().hide(homeFragment).commit();
//
//                moreMenuFragment = MoreMenuFragment.newInstance();
//                fragmentManager = getChildFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.frameLayout, moreMenuFragment).commit();
//
//
//        }
//        else {
//                if ((categoryFragment == null) || categoryFragment.isVisible()) {
//                    fragmentManager.beginTransaction().hide(categoryFragment).commit();
//
//                    moreMenuFragment = MoreMenuFragment.newInstance();
//                    fragmentManager = getChildFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.add(R.id.frameLayout, moreMenuFragment).commit();
//                }


//    private void setFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getChildFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
//    }
    }
}


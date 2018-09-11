package com.yaratech.yaratube.ui.dashbord.more;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreMenuFragment extends Fragment {

    private TextView mTextView;
    private ProfileFragment profileFragment;

    public MoreMenuFragment() {
        // Required empty public constructor
    }

    public static MoreMenuFragment newInstance() {

        Bundle args = new Bundle();

        MoreMenuFragment fragment = new MoreMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = view.findViewById(R.id.text_more_profile);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileFragment = ProfileFragment.newInstance();
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.more_fragment_container, profileFragment)
                .addToBackStack(null)
                .commit();

            }
        });
    }
}

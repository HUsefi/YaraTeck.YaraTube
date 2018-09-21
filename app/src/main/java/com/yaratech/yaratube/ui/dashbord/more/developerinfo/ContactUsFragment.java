package com.yaratech.yaratube.ui.dashbord.more.developerinfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {


    public ContactUsFragment() {
        // Required empty public constructor
    }

    public static ContactUsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ContactUsFragment fragment = new ContactUsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

}

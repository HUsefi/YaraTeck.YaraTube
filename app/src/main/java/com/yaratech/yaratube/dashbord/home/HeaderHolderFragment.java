package com.yaratech.yaratube.dashbord.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Headeritem;

import java.util.List;


public class HeaderHolderFragment extends Fragment {

    private ImageView mHeaderImage;
    private static final String ARG_SECTION_NUMBER = "section_number";
    String url;


    public HeaderHolderFragment() {
    }

    public static HeaderHolderFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, url);
        HeaderHolderFragment fragment = new HeaderHolderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments().getString(ARG_SECTION_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_place_holder, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHeaderImage = view.findViewById(R.id.image_view_header);
        Glide.with(this).load(url).into(mHeaderImage);
    }
}

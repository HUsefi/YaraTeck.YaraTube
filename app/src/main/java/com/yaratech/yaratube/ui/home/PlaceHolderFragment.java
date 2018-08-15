package com.yaratech.yaratube.ui.home;


import android.os.Bundle;
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

import static com.yaratech.yaratube.utils.Constant.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceHolderFragment extends Fragment {

    private ImageView mHeaderImage;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String urlHeaderImage;
    private List<Headeritem> headeritems;
    public PlaceHolderFragment() {
        // Required empty public constructor
    }

    public static PlaceHolderFragment newInstance(String url) {
        
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, url);
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_place_holder, container, false);
        mHeaderImage = rootView.findViewById(R.id.image_view_header);
        Log.e("HIDE"," "+getArguments().getString(ARG_SECTION_NUMBER));
//        urlHeaderImage = BASE_URL + '/' + headeritem.getFeatureAvatar().getXxhdpi();
        Glide.with(rootView.getContext()).load(getArguments().getString(ARG_SECTION_NUMBER)).into(mHeaderImage);
       // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;

    }

}

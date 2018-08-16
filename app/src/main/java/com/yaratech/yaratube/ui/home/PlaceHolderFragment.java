package com.yaratech.yaratube.ui.home;


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

import static com.yaratech.yaratube.utils.Constant.BASE_URL;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceHolderFragment extends Fragment {

    private ImageView mHeaderImage;
    private static final String ARG_SECTION_NUMBER = "section_number";
    private String urlHeaderImage;
    private List<Headeritem> headeritems;
    String url;


    public PlaceHolderFragment() {
    }

    public static PlaceHolderFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, url);
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        fragment.setArguments(args);
        Log.e("DDDDD", " " + url);
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

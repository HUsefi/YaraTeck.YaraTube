package com.yaratech.yaratube.ui.profile.imageprovider;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageProviderFragment extends DialogFragment {

    private Button buttonCamera, buttonGallery;
    private OnImageProviderListener monImageProviderListener;
    public static final String IMAGE_PROVIDER_TAG = "image_provider_dialog";

    public ImageProviderFragment() {
        // Required empty public constructor
    }

    public static ImageProviderFragment newInstance() {

        Bundle args = new Bundle();
        ImageProviderFragment fragment = new ImageProviderFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonCamera = view.findViewById(R.id.button_camera);
        buttonGallery = view.findViewById(R.id.button_gallery);

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monImageProviderListener.openCamera();
                dismiss();
            }
        });
        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monImageProviderListener.openGallery();
                dismiss();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        monImageProviderListener = (OnImageProviderListener) getParentFragment();
    }

    public interface OnImageProviderListener {
        void openCamera();

        void openGallery();
    }


}

package com.yaratech.yaratube.ui.dashbord.more;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.ui.dashbord.more.developerinfo.AboutUsFragment;
import com.yaratech.yaratube.ui.dashbord.more.developerinfo.ContactUsFragment;
import com.yaratech.yaratube.ui.login.DialogContainerFragment;
import com.yaratech.yaratube.ui.profile.ProfileFragment;

import static com.yaratech.yaratube.ui.login.DialogContainerFragment.DIALOG_CONTAINER_FRAGMENT_TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreMenuFragment extends Fragment {

    private TextView mTextViewProfile,mTextViewAbout,mTextViewContact;
    private ProfileFragment profileFragment;
    private AboutUsFragment aboutUsFragment;
    private ContactUsFragment contactUsFragment;
    private ReplaceContainer replaceContainer;
    private MoreMenuPresenter moreMenuPresenter;
    private DialogContainerFragment dialogContainerFragment;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        replaceContainer=(ReplaceContainer)context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        replaceContainer = null;
    }

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
        mTextViewProfile = view.findViewById(R.id.text_more_profile);
        mTextViewAbout = view.findViewById(R.id.text_more_about_us);
        mTextViewContact = view.findViewById(R.id.text_more_contact_us);


        mTextViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!moreMenuPresenter.isLogin()) {
                    dialogContainerFragment = DialogContainerFragment.newInstance();
                    dialogContainerFragment.show(getFragmentManager(), DIALOG_CONTAINER_FRAGMENT_TAG);
                }
                else
               replaceContainer.replace(profileFragment.newInstance());
            }
        });

        mTextViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceContainer.replace(aboutUsFragment.newInstance());
            }
        });

        mTextViewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceContainer.replace(contactUsFragment.newInstance());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        moreMenuPresenter =new MoreMenuPresenter(getContext());
    }

    public interface ReplaceContainer {
        void replace(Fragment fragment);
    }
}

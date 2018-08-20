package com.yaratech.yaratube.ui.dashbord.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.MainActivity;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.ui.OnProductItemClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeContract.Veiw
,HomeItemAdapter.OnHomeItemClickListener{

    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;
    private ProgressBar mProgressBar;
    private HomeContract.Presenter mPresenter;
    OnProductItemClick onProductItemClick;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new HomePresenter(this);
        mProgressBar = view.findViewById(R.id.progress_bar_home);
        mPresenter.fetchDataStoreFromRemote();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_home);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter = new HomeAdapter(getContext(), getFragmentManager());
        mRecyclerView.setAdapter(mHomeAdapter);
    }


    public void onAttach(Context context) {
        if (context instanceof MainActivity)
            onProductItemClick = (OnProductItemClick) context;
        super.onAttach(context);
    }
    public void onDetach() {
        onProductItemClick = null;
        super.onDetach();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void notAvailableDate() {
        Toast.makeText(getContext(), "not available data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDateStore(Store store) {
        mHomeAdapter.setDateStore(store);
    }

    public void getHomeProductItem(Product product) {
        onProductItemClick.onClick(product);
    }


}

package com.yaratech.yaratube.ui.productlist;


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

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.ProductList;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment implements ProductListContract.Veiw {

    ProductListPresenter mProductListPresenter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    //private CategoryAdapter mCategoryAdapter;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("UserId", id);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressBar = view.findViewById(R.id.progress_bar_list_product);
        mProgressBar.setVisibility(View.GONE);
        mProductListPresenter = new ProductListPresenter(getContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_list_product);
        mRecyclerView.setLayoutManager(linearLayoutManager);
   //     mCategoryAdapter = new CategoryAdapter(getContext(), this);
   //     mRecyclerView.setAdapter(mCategoryAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProductListPresenter.fetchDataProductListFromRemote(getArguments().getInt("UserId"));
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
    public void onGetDateProductList(List<ProductList> productLists) {

    }
}

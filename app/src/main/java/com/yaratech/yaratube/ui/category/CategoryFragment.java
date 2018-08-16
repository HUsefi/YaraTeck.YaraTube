package com.yaratech.yaratube.ui.category;


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
import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.ui.productlist.ProductListFragment;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryContract.View
        ,CategoryAdapter.ItemClickListener {

    CategoryPresenter categoryPresenter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private CategoryAdapter mCategoryAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar = view.findViewById(R.id.progress_bar_category);
        mProgressBar.setVisibility(View.GONE);
        categoryPresenter = new CategoryPresenter(getContext(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView = view.findViewById(R.id.recycler_view_category);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mCategoryAdapter = new CategoryAdapter(getContext(), this);
       mRecyclerView.setAdapter(mCategoryAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        categoryPresenter.fetchCategoryDataFromRemote();
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
    public void notAvailableDateCategort() {
        Toast.makeText(getContext(), "not available data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDateCategory(List<CategoryList> categoryList) {
        mCategoryAdapter.setData(categoryList);
    }

    @Override
    public void onItemClick(CategoryList categoryList) {
        ((CategoryFragment.OnCategoryFragmentActionListener) getContext()).onCategorylistItemClicked(categoryList);
    }


    public interface OnCategoryFragmentActionListener{
        void onCategorylistItemClicked(CategoryList category);
    }
}

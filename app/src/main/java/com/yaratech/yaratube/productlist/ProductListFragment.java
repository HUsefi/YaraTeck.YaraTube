package com.yaratech.yaratube.productlist;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.ProductList;

import java.util.List;


public class ProductListFragment extends Fragment implements ProductListContract.Veiw,
        ProductListAdapter.ItemClickListener {

    ProductListPresenter mProductListPresenter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private ProductListAdapter mProductListAdapter;
    public static final String PRODUCT_LIST_FRAGMENT_TAG = "ProductList";

    public ProductListFragment() {
    }

    public static ProductListFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("CategoryId", id);
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
        mProductListPresenter = new ProductListPresenter( this);
        mRecyclerView = view.findViewById(R.id.recycler_view_list_product);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,
                LinearLayout.VERTICAL,false));
        mProductListAdapter = new ProductListAdapter(getContext(),this);
        mRecyclerView.setAdapter(mProductListAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProductListPresenter.fetchDataProductListFromRemote(getArguments().getInt("CategoryId"));
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
        mProductListAdapter.setData(productLists);

    }

    @Override
    public void onItemClick(int categoryId) {
        ((ProductListFragment.OnProductListFragmentActionListener) getContext())
                .onProductListItemClicked(categoryId);
    }


    public interface OnProductListFragmentActionListener{
        void onProductListItemClicked(int categoryId);
    }
}

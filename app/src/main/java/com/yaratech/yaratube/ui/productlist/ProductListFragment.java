package com.yaratech.yaratube.ui.productlist;



import android.content.Context;
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


import com.yaratech.yaratube.MainActivity;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.ui.OnProductItemClick;

import java.util.List;


public class ProductListFragment extends Fragment implements ProductListContract.Veiw,
        ProductListAdapter.ItemClickListener {

    ProductListPresenter mProductListPresenter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private ProductListAdapter mProductListAdapter;
    public static final String PRODUCT_LIST_FRAGMENT_TAG = "ProductList";
    OnProductItemClick onProductItemClick;



    public ProductListFragment() {
    }

    public static ProductListFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("CategoryId", id);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
    public void onGetDateProductList(List<Product> productLists) {
        mProductListAdapter.setData(productLists);

    }

    @Override
    public void onItemClick(Product product) {
        onProductItemClick.onClick(product);
    }

}

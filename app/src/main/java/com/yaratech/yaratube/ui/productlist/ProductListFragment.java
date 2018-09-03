package com.yaratech.yaratube.ui.productlist;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    boolean isLoading = false;
    private ProductListAdapter mProductListAdapter;
    public static final String PRODUCT_LIST_FRAGMENT_TAG = "ProductList";
    OnProductItemClick onProductItemClick;
    private int offset = 0;
    private int limit = 10;


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
        mProductListPresenter = new ProductListPresenter(this);
        mRecyclerView = view.findViewById(R.id.recycler_view_list_product);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2,
                LinearLayout.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mProductListAdapter = new ProductListAdapter(getContext(), this);
        mRecyclerView.setAdapter(mProductListAdapter);
        mRecyclerView.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                Log.e("OFFSET", "" + offset);
                mProductListPresenter.fetchDataProductListFromRemote(getArguments().getInt("CategoryId"), offset, limit);
            }

//            @Override
//            public int getTotalPageCount() {
//                return 0;
//            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProductListPresenter.fetchDataProductListFromRemote(getArguments().getInt("CategoryId"), offset, limit);
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
        isLoading = false;
        Toast.makeText(getContext(), "not available data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDateProductList(List<Product> productLists) {
        isLoading = false;
        mProductListAdapter.UpdateData(productLists);
        offset += productLists.size();
        Log.e("Tag", " mag " + offset + " " + productLists.size());
    }

    @Override
    public void onItemClick(Product product) {
        onProductItemClick.onClick(product);
    }

}

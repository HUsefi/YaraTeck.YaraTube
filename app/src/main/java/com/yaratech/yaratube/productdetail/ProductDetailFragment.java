package com.yaratech.yaratube.productdetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.ProductDetails;


public class ProductDetailFragment extends Fragment implements ProductDetailContract.Veiw{

    private ProductDetailPresenter mProductDetailPresenter;
    private ProductDetails mProductDetails;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public void setData(ProductDetails productDetails){
        mProductDetails = productDetails;
    }

    public static ProductDetailFragment newInstance(int id) {
        
        Bundle args = new Bundle();
        args.putInt("ProductId", id);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }





    @Override
    public void notAvailableDate() {
        Toast.makeText(getContext(), "not available data", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGetDateProductDetail(ProductDetails productDetails) {
        
    }

}

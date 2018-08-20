package com.yaratech.yaratube.ui.productdetail;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetails;

import java.util.List;


public class ProductDetailFragment extends Fragment implements ProductDetailContract.Veiw{

    ProductDetailPresenter mProductDetailPresenter;
    Product product;
    private ProgressBar mProgressBar;
    RecyclerView mRecyclerView;
    private ImageView mImageviewdisplay;
    private ImageView mImageViewIcon;
    private TextView mTextViewTitle;
    TextView mTextViewDescription;
    private CommentRecyclerAdapter mCommentRecyclerAdapter;
    public static final String PRODUCT_DETAIL_FRAGMENT_TAG = "ProductDetail";


    public ProductDetailFragment() {
        // Required empty public constructor
    }

//    public static ProductDetailFragment newInstance(int id) {
//        Bundle args = new Bundle();
//        args.putInt("ProductId", id);
//        ProductDetailFragment fragment = new ProductDetailFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static ProductDetailFragment newInstance(Product product) {
        Bundle args = new Bundle();
        args.putParcelable("product", product);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageviewdisplay=view.findViewById(R.id.image_view_palyer);
        mTextViewTitle=view.findViewById(R.id.text_view_product_name_player);
        mImageViewIcon=view.findViewById(R.id.image_view_icon);
        mTextViewDescription = view.findViewById(R.id.text_view_content_discription);
        mProgressBar=view.findViewById(R.id.progress_bar_detail_product);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView=view.findViewById(R.id.recycler_view_comment);
        initRecycleview();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mProductDetailPresenter =new ProductDetailPresenter(getContext(),this);
        product = getArguments().getParcelable("product");
        mProductDetailPresenter.fetchDataProductDetailFromRemote(product.getId());

    }

    private void initRecycleview() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false));
        mCommentRecyclerAdapter = new CommentRecyclerAdapter();
        mRecyclerView.setAdapter(mCommentRecyclerAdapter);
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
    public void onGetDateProductDetail(ProductDetails productDetails) {
        Glide.with(getContext()).load(productDetails.getFeatureAvatar().getXxhdpi()).into(mImageviewdisplay);
        mTextViewTitle.setText(productDetails.getName());
        mTextViewDescription.setText(productDetails.getDescription());
        mProductDetailPresenter.fetchCommentFromRemote(product.getId());

    }

    @Override
    public void onGetDateComment(List<Comment> commentList) {
        mCommentRecyclerAdapter.setData(commentList);
    }


}

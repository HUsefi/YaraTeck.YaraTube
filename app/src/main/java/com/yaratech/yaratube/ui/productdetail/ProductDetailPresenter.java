package com.yaratech.yaratube.ui.productdetail;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetails;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.UserRepository;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.data.source.remote.APIResult;

import java.util.List;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private Repository mReposiroryProductDetail;
    private ProductDetailContract.View mView;
    private UserRepository userRepository;


    ProductDetailPresenter(Context context, ProductDetailContract.View mView) {
        this.mView = mView;
        mReposiroryProductDetail = new Repository(context);
        userRepository = new UserRepository(context);
        userRepository.setDatabase(AppDatabase.getAppDatabase(context));
    }

    @Override
    public void fetchDataProductDetailFromRemote(int productId) {
        mView.showProgressBar();
        mReposiroryProductDetail.getProductDetail(new APIResult<Product>() {
            @Override
            public void onSuccess(Product result) {
                mView.hideProgressBar();
                mView.onGetDateProductDetail(result);
            }

            @Override
            public void onFail(String errorMessage) {
                mView.hideProgressBar();
                mView.notAvailableDate();
            }
        }, productId);

    }

    @Override
    public void fetchCommentFromRemote(Product product) {
        mReposiroryProductDetail.getComment(new APIResult<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> result) {
                mView.hideProgressBar();
                mView.onGetDateComment(result);
            }

            @Override
            public void onFail(String errorMessage) {
                mView.hideProgressBar();
                mView.notAvailableDate();
            }
        }, product);

    }

    @Override
    public boolean isLogin() {
        return userRepository.isLogin();
    }

    @Override
    public void login(FragmentManager fragmentManager) {
        userRepository.login(fragmentManager);
    }

}

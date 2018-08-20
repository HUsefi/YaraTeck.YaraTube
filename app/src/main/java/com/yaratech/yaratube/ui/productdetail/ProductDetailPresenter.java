package com.yaratech.yaratube.ui.productdetail;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.ProductDetails;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.remote.APIResult;

import java.util.List;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private Repository mReposiroryProductDetail;
    private ProductDetailContract.Veiw mView;

     ProductDetailPresenter(ProductDetailContract.Veiw mView) {
        this.mView = mView;
        mReposiroryProductDetail = new Repository();
    }

    @Override
    public void fetchDataProductDetailFromRemote(int productId) {
        mView.showProgressBar();
        mReposiroryProductDetail.getProductDetail(new APIResult<ProductDetails>() {
            @Override
            public void onSuccess(ProductDetails result) {
                mView.hideProgressBar();
                mView.onGetDateProductDetail(result);
            }

            @Override
            public void onFail() {
                mView.hideProgressBar();
                mView.notAvailableDate();
            }
        }, productId);

    }

    @Override
    public void fetchCommentFromRemote(int productId) {
        mReposiroryProductDetail.getComment(new APIResult<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> commentList) {
                mView.hideProgressBar();
                mView.onGetDateComment(commentList);
            }

            @Override
            public void onFail() {
                mView.hideProgressBar();
                mView.notAvailableDate();
            }
        }, productId);
    }


}

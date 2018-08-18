package com.yaratech.yaratube.productdetail;

import com.yaratech.yaratube.data.model.ProductDetails;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.remote.APIResult;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private Repository mReposiroryProductDetail;
    private ProductDetailContract.Veiw mView;

    public ProductDetailPresenter(ProductDetailContract.Veiw mView) {
        this.mView = mView;
        this.mReposiroryProductDetail = mReposiroryProductDetail;
    }

    @Override
    public void fetchDataProductDetailFromRemote(int productId) {
        mReposiroryProductDetail.getProductDetail(new APIResult<ProductDetails>() {
            @Override
            public void onSuccess(ProductDetails result) {
                mView.onGetDateProductDetail(result);

            }

            @Override
            public void onFail() {
                mView.notAvailableDate();
            }
        }, productId);

    }
}

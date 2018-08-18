package com.yaratech.yaratube.productlist;

import android.content.Context;

import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.remote.APIResult;

import java.util.List;

public class ProductListPresenter implements ProductListContract.Presenter {

    private Repository mReposiroryProductList;
    private ProductListContract.Veiw mView;

    public ProductListPresenter(Context context, ProductListContract.Veiw mView) {
        mReposiroryProductList = new Repository();
        this.mView = mView;
    }

    @Override
    public void fetchDataProductListFromRemote(int productId) {
        mView.showProgressBar();
        mReposiroryProductList.getProductList(new APIResult<List<ProductList>>() {
            @Override
            public void onSuccess(List<ProductList> result) {
                mView.onGetDateProductList(result);
                mView.hideProgressBar();
            }

            @Override
            public void onFail() {
                mView.notAvailableDate();
                mView.hideProgressBar();
            }
        }, productId);

    }
}

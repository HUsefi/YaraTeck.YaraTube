package com.yaratech.yaratube.ui.productlist;

        import android.content.Context;

        import com.yaratech.yaratube.data.model.Product;
        import com.yaratech.yaratube.data.model.ProductList;
        import com.yaratech.yaratube.data.source.Repository;
        import com.yaratech.yaratube.data.source.remote.APIResult;

        import java.util.List;

public class ProductListPresenter implements ProductListContract.Presenter {

    private Repository mReposiroryProductList;
    private ProductListContract.Veiw mView;
    Context context;

    ProductListPresenter(ProductListContract.Veiw mView) {
        mReposiroryProductList = new Repository(context);
        this.mView = mView;
    }

    @Override
    public void fetchDataProductListFromRemote(int productId, int offset, int limit) {
        mView.showProgressBar();
        mReposiroryProductList.getProductList(new APIResult<List<Product>>() {
            @Override
            public void onSuccess(List<Product> result) {
                mView.hideProgressBar();
                mView.onGetDateProductList(result);

            }

            @Override
            public void onFail(String errorMessage) {
                mView.hideProgressBar();
                mView.notAvailableDate();
            }
        }, productId, offset, limit);

    }
}

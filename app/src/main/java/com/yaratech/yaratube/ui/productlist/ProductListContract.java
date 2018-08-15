package com.yaratech.yaratube.ui.productlist;

import com.yaratech.yaratube.data.model.ProductList;


import java.util.List;

public interface ProductListContract {
    public interface Veiw{
        void showProgressBar();
        void hideProgressBar();
        void notAvailableDate();
        void onGetDateProductList(List<ProductList> productLists );

    }

    public interface Presenter{
        void fetchDataProductListFromRemote(int productId);
    }
}

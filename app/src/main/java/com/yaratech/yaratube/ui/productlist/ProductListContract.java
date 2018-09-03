package com.yaratech.yaratube.ui.productlist;

import com.yaratech.yaratube.data.model.Product;



import java.util.List;

public interface ProductListContract {
    public interface Veiw{
        void showProgressBar();
        void hideProgressBar();
        void notAvailableDate();
        void onGetDateProductList(List<Product> productLists );

    }

    public interface Presenter{
        void fetchDataProductListFromRemote(int categoryId, int offset, int limit);
    }
}

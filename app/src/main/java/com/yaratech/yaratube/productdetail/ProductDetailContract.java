package com.yaratech.yaratube.productdetail;

import com.yaratech.yaratube.data.model.ProductDetails;

public interface ProductDetailContract {
    public interface Veiw{
        void notAvailableDate();
        void onGetDateProductDetail(ProductDetails productDetails);

    }

    public interface Presenter{
        void fetchDataProductDetailFromRemote(int productId);
    }
}

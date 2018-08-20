package com.yaratech.yaratube.ui.productdetail;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.ProductDetails;

import java.util.List;

public interface ProductDetailContract {
    public interface Veiw{
        void showProgressBar();
        void hideProgressBar();
        void notAvailableDate();
        void onGetDateProductDetail(ProductDetails productDetails);
        void onGetDateComment(List<Comment> commentList);
    }

    public interface Presenter{
        void fetchDataProductDetailFromRemote(int productId);
        void fetchCommentFromRemote(int productId);

    }

}

package com.yaratech.yaratube.ui.productdetail;

import android.support.v4.app.FragmentManager;

import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetails;

import java.util.List;

public interface ProductDetailContract {
    interface View {
        void showProgressBar();

        void hideProgressBar();

        void notAvailableDate();

        void onGetDateProductDetail(Product product);

        void onGetDateComment(List<Comment> commentList);

        void openCommentDialog(int productId);
    }

    interface Presenter {
        void fetchDataProductDetailFromRemote(int productId);

        void fetchCommentFromRemote(Product product);

        boolean isLogin();

        void login(FragmentManager fragmentManager);

    }

}

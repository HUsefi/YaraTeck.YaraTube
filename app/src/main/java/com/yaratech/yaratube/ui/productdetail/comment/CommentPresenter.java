package com.yaratech.yaratube.ui.productdetail.comment;

import android.content.Context;

import com.yaratech.yaratube.data.model.CommentPostResponse;
import com.yaratech.yaratube.data.source.UserRepository;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.data.source.remote.APIResult;

public class CommentPresenter implements CommentContract.Presenter {

    private CommentContract.View view;
    private UserRepository repository;

    CommentPresenter(CommentContract.View view, Context context, AppDatabase database) {

        this.view = view;
        this.repository = new UserRepository(context);
        repository.setDatabase(database);
    }

    @Override
    public void sendCommentToServer(int score, String comment, int productId, String token) {

        repository.sendUsercomment(score, comment, productId, token,
                new APIResult<CommentPostResponse>() {
                    @Override
                    public void onSuccess(CommentPostResponse result) {

                        view.showMessage("نظر شما پس از بررسی ثبت خواهد شد");
                        view.dissmissDialog();
                    }

                    @Override
                    public void onFail(String errorMessage) {

                        view.showMessage(errorMessage);
                    }
                });
    }

    @Override
    public String getToken() {

        return repository.getUser().getToken();
    }
}

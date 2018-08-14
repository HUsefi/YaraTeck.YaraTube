package com.yaratech.yaratube.ui.category;

import android.content.Context;

import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.remote.APIResult;

import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View mView;
    private Repository mCategoryRepository;

    public CategoryPresenter(Context context,CategoryContract.View view) {
        mView = view;
        mCategoryRepository=new Repository();
     }

    @Override
    public void fetchCategoryDataFromRemote() {
        mView.showProgressBar();
        mCategoryRepository.getCategory(new APIResult<List<CategoryList>>() {
            @Override
            public void onSuccess(List<CategoryList> result) {
                mView.onGetDateCategory(result);
                mView.hideProgressBar();
            }

            @Override
            public void onFail() {
                mView.notAvailableDateCategort();
                mView.hideProgressBar();
            }
        });




    }
}

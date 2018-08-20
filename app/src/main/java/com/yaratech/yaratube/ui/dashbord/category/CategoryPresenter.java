package com.yaratech.yaratube.ui.dashbord.category;

import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.remote.APIResult;

import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View mView;
    private Repository mCategoryRepository;

     CategoryPresenter(CategoryContract.View view) {
        mView = view;
        mCategoryRepository=new Repository();
     }

    @Override
    public void fetchCategoryDataFromRemote() {
        mView.showProgressBar();
        mCategoryRepository.getCategory(new APIResult<List<CategoryList>>() {
            @Override
            public void onSuccess(List<CategoryList> result) {
                mView.hideProgressBar();
                mView.onGetDateCategory(result);
            }

            @Override
            public void onFail() {
                mView.hideProgressBar();
                mView.notAvailableDateCategort();
            }
        });




    }
}

package com.yaratech.yaratube.dashbord.category;

import com.yaratech.yaratube.data.model.CategoryList;

import java.util.List;

public interface CategoryContract {
    interface View {
        void showProgressBar();
        void hideProgressBar();
        void notAvailableDateCategort();
        void onGetDateCategory(List<CategoryList> category_list);
    }

    interface Presenter {

        void fetchCategoryDataFromRemote();
    }
}

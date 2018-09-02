package com.yaratech.yaratube.ui.dashbord.home;

import android.content.Context;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.remote.APIResult;

public class HomePresenter implements HomeContract.Presenter {

    HomeContract.Veiw view;
    Repository mRepository;
    Context context;

    public HomePresenter(HomeContract.Veiw view) {
        this.view = view;
        mRepository = new Repository(context);

    }

    @Override
    public void fetchDataStoreFromRemote() {
        view.showProgressBar();
        mRepository.getStore(new APIResult<Store>() {
            @Override
            public void onSuccess(Store result) {
                view.hideProgressBar();
                view.onGetDateStore(result);
            }

            @Override
            public void onFail(String errorMessage) {
                view.hideProgressBar();
                view.notAvailableDate();
            }
        });
    }
}

package com.yaratech.yaratube.dashbord.home;

import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.remote.APIResult;

public class HomePresenter implements HomeContract.Presenter {

    HomeContract.Veiw view;
    Repository mRepository;

    public HomePresenter(HomeContract.Veiw view) {
        this.view = view;
        mRepository = new Repository();

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
            public void onFail() {
                view.hideProgressBar();
                view.notAvailableDate();
            }
        });
    }
}
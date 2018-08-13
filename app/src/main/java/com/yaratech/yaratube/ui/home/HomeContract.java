package com.yaratech.yaratube.ui.home;

import com.yaratech.yaratube.data.model.Store;

import java.util.List;

public interface HomeContract {

    public interface Veiw{
        void showProgressBar();
        void hideProgressBar();
        void notAvailableDate();
        void onGetDateStore(Store store);

    }

    public interface Presenter{
        void fetchDataStoreFromRemote();
    }


}


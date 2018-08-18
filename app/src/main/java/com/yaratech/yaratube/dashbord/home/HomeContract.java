package com.yaratech.yaratube.dashbord.home;

import com.yaratech.yaratube.data.model.Store;

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


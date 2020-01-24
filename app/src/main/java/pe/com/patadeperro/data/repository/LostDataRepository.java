package pe.com.patadeperro.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import pe.com.patadeperro.data.datasource.datastore.LostDataStore;
import pe.com.patadeperro.data.datasource.datastore.LostDataStoreFactory;
import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.LostRepository;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.interactor.lost.LostCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostListCallback;
import pe.com.patadeperro.interactor.lost.LostUpdatedCallback;
// import pe.com.patadeperro.interactor.lost.LostExistCallback;

public class LostDataRepository implements LostRepository {

    private final LostDataStoreFactory lostDataStoreFactory;

    public LostDataRepository(LostDataStoreFactory lostDataStoreFactory) {
        this.lostDataStoreFactory = lostDataStoreFactory;
    }

    @Override
    public void createLost(Lost lost, LostCreatedCallback lostCreatedCallback) {
        final LostDataStore lostDataStore = lostDataStoreFactory.create(
                lostDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());
        lostDataStore.createLost(lost, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                lostCreatedCallback.onLostCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                Lost newLost = (Lost) object;
                lostCreatedCallback.onLostCreatedSuccess(newLost);
            }
        });
    }

    @Override
    public void updateLost(Lost lost, LostUpdatedCallback lostUpdatedCallback) {
        final LostDataStore lostDataStore = lostDataStoreFactory.update(
                lostDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());
        lostDataStore.updateLost(lost, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                lostUpdatedCallback.onLostUpdatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                Lost newLost = (Lost) object;
                lostUpdatedCallback.onLostUpdatedSuccess(newLost);
            }
        });

    }

    @Override
    public void loadLosts(LostListCallback requestListCallback) {

        final LostDataStore lostDataStore = lostDataStoreFactory.create(
                lostDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());

        lostDataStore.lostsList(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                requestListCallback.onLostError(message);
            }

            @Override
            public void onSuccess(Object object) {

                ArrayList<Lost> LostList = (ArrayList<Lost>) object;
                requestListCallback.onLostSuccess(LostList);
            }
        });

    }

}

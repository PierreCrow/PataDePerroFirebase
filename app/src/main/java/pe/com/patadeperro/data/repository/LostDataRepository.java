package pe.com.patadeperro.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.datastore.LostDataStore;
import pe.com.patadeperro.data.datasource.datastore.LostDataStoreFactory;
import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.domain.repository.LostRepository;
import pe.com.patadeperro.interactor.lost.LostCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostListCallback;
import pe.com.patadeperro.interactor.lost.LostListCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostUpdatedCallback;
import pe.com.patadeperro.interactor.lost.LostDeletedCallback;

public class LostDataRepository implements LostRepository {

    private final LostDataStoreFactory lostDataStoreFactory;

    public LostDataRepository(
            LostDataStoreFactory lostDataStoreFactory) {
        this.lostDataStoreFactory = lostDataStoreFactory;
    }

    @Override
    public void createLost(
            Lost lost,
            int lostDataLocation,
            LostCreatedCallback lostCreatedCallback) {

        final LostDataStore lostDataStore = lostDataStoreFactory.create(
//                lostDataStoreFactory.CLOUD,
                lostDataLocation,
                FirebaseFirestore.getInstance());

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

                Lost newUser = (Lost) object;
                lostCreatedCallback.onLostCreatedSuccess(newUser);
            }
        });
    }

    @Override
    public void createLostList(
            List<Lost> lostList,
            int lostDataLocation,
            LostListCreatedCallback lostListCreateCallback) {

        final LostDataStore lostDataStore = lostDataStoreFactory.create(
//                lostDataStoreFactory.CLOUD,
                lostDataLocation,
                FirebaseFirestore.getInstance());

        lostDataStore.createLostList(lostList, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                lostListCreateCallback.onLostListCreateError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<Lost> newLostList = (List<Lost>) object;
                lostListCreateCallback.onLostListCreateSuccess(newLostList);
            }
        });
    }

    @Override
    public void updateLost(
            Lost lost,
            int lostDataLocation,
            LostUpdatedCallback lostUpdatedCallback) {

        final LostDataStore lostDataStore = lostDataStoreFactory.create(
//                lostDataStoreFactory.CLOUD,
                lostDataLocation,
                FirebaseFirestore.getInstance());

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

                Lost updLost = (Lost) object;
                lostUpdatedCallback.onLostUpdatedSuccess(updLost);
            }
        });

    }

    @Override
    public void deleteLost(
            Lost lost,
            int lostDataLocation,
            LostDeletedCallback lostDeletedCallback) {

        final LostDataStore lostDataStore;

        lostDataStore = lostDataStoreFactory.create(
                lostDataLocation,
                FirebaseFirestore.getInstance());

        lostDataStore.deleteLost(lost, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                lostDeletedCallback.onLostDeletedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                Lost updLost = (Lost) object;
                lostDeletedCallback.onLostDeletedSuccess(updLost);
            }
        });

    }

    @Override
    public void loadLosts(
            int lostDataLocation,
            LostListCallback requestListCallback) {

        final LostDataStore lostDataStore = lostDataStoreFactory.create(
//                lostDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());
                lostDataLocation, FirebaseFirestore.getInstance());

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

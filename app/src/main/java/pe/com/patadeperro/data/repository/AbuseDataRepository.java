package pe.com.patadeperro.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import pe.com.patadeperro.data.datasource.datastore.AbuseDataStore;
import pe.com.patadeperro.data.datasource.datastore.AbuseDataStoreFactory;
import pe.com.patadeperro.domain.model.Abuse;
import pe.com.patadeperro.domain.repository.AbuseRepository;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.interactor.abuse.AbuseCreatedCallback;
import pe.com.patadeperro.interactor.abuse.AbuseListCallback;
import pe.com.patadeperro.interactor.abuse.AbuseUpdatedCallback;
// import pe.com.patadeperro.interactor.abuse.AbuseExistCallback;

public class AbuseDataRepository implements AbuseRepository {

    private final AbuseDataStoreFactory abuseDataStoreFactory;

    public AbuseDataRepository(AbuseDataStoreFactory abuseDataStoreFactory) {
        this.abuseDataStoreFactory = abuseDataStoreFactory;
    }

    @Override
    public void createAbuse(Abuse abuse, AbuseCreatedCallback abuseCreatedCallback) {
        final AbuseDataStore abuseDataStore = abuseDataStoreFactory.create(
                abuseDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());
        abuseDataStore.createAbuse(abuse, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                abuseCreatedCallback.onAbuseCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                Abuse newAbuse = (Abuse) object;
                abuseCreatedCallback.onAbuseCreatedSuccess(newAbuse);
            }
        });
    }

    @Override
    public void updateAbuse(Abuse abuse, AbuseUpdatedCallback abuseUpdatedCallback) {
        final AbuseDataStore abuseDataStore = abuseDataStoreFactory.update(
                abuseDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());
        abuseDataStore.updateAbuse(abuse, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                abuseUpdatedCallback.onAbuseUpdatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                Abuse newAbuse = (Abuse) object;
                abuseUpdatedCallback.onAbuseUpdatedSuccess(newAbuse);
            }
        });

    }

    @Override
    public void loadAbuses(AbuseListCallback requestListCallback) {

        final AbuseDataStore abuseDataStore = abuseDataStoreFactory.create(
                abuseDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());

        abuseDataStore.abusesList(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                requestListCallback.onAbuseError(message);
            }

            @Override
            public void onSuccess(Object object) {

                ArrayList<Abuse> AbuseList = (ArrayList<Abuse>) object;
                requestListCallback.onAbuseSuccess(AbuseList);
            }
        });

    }

}

package pe.com.patadeperro.data.datasource.datastore;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;

import pe.com.patadeperro.data.datasource.cloud.store.CloudPetDataStore;
import pe.com.patadeperro.data.datasource.db.store.DbPetDataStore;

public class PetDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;

    private final Context context;

    public PetDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }


    public PetDataStore create(
            int dataSource, FirebaseFirestore db) {
        PetDataStore petDataStore = null;

        switch (dataSource) {
            case CLOUD:
                petDataStore = createCloudDataStore(db);
                break;
            case DB:
                petDataStore = new DbPetDataStore(context);
                break;
        }
        return petDataStore;
    }

    private PetDataStore createCloudDataStore(
            FirebaseFirestore db) {
        return new CloudPetDataStore(db);
    }


}

package pe.com.patadeperro.data.datasource.datastore;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;

import pe.com.patadeperro.data.datasource.cloud.store.CloudLostDataStore;
//import pe.com.patadeperro.data.datasource.db.store.DbLostDataStore;

public class LostDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;

    private final Context context;

    public LostDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }

    public LostDataStore create(
            int dataSource, FirebaseFirestore db) {
        LostDataStore lostDataStore = null;

        switch (dataSource) {
            case CLOUD:
                lostDataStore = createCloudDataStore(db);
                break;
            case DB:
                //lostDataStore = new DbLostDataStore(context);
                break;
        }
        return lostDataStore;
    }

    public LostDataStore update(
            int dataSource, FirebaseFirestore db) {
        LostDataStore lostDataStore = null;

        switch (dataSource) {
            case CLOUD:
                lostDataStore = createCloudDataStore(db);
                break;
            case DB:
                //lostDataStore = new DbLostDataStore(context);
                break;
        }
        return lostDataStore;
    }

    private LostDataStore createCloudDataStore(
            FirebaseFirestore db) {
        return new CloudLostDataStore(db);
    }

}

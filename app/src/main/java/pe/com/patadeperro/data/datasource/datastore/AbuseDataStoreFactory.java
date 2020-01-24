package pe.com.patadeperro.data.datasource.datastore;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;

import pe.com.patadeperro.data.datasource.cloud.store.CloudAbuseDataStore;
//import pe.com.patadeperro.data.datasource.db.store.DbAbuseDataStore;

public class AbuseDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;

    private final Context context;

    public AbuseDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }

    public AbuseDataStore create(
            int dataSource, FirebaseFirestore db) {
        AbuseDataStore abuseDataStore = null;

        switch (dataSource) {
            case CLOUD:
                abuseDataStore = createCloudDataStore(db);
                break;
            case DB:
                //abuseDataStore = new DbAbuseDataStore(context);
                break;
        }
        return abuseDataStore;
    }

    public AbuseDataStore update(
            int dataSource, FirebaseFirestore db) {
        AbuseDataStore abuseDataStore = null;

        switch (dataSource) {
            case CLOUD:
                abuseDataStore = createCloudDataStore(db);
                break;
            case DB:
                //abuseDataStore = new DbAbuseDataStore(context);
                break;
        }
        return abuseDataStore;
    }

    private AbuseDataStore createCloudDataStore(
            FirebaseFirestore db) {
        return new CloudAbuseDataStore(db);
    }

}

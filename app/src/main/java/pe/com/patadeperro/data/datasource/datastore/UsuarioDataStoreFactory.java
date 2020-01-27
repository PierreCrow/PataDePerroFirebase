package pe.com.patadeperro.data.datasource.datastore;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import pe.com.patadeperro.data.datasource.cloud.store.CloudUsuarioDataStore;
import pe.com.patadeperro.data.datasource.db.store.DbUsuarioDataStore;

public class UsuarioDataStoreFactory {

    public static final int DB = 1;
    public static final int CLOUD = 0;

    private final Context context;

    public UsuarioDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        this.context = context.getApplicationContext();
    }

    public UsuarioDataStore create(
            int dataSource, FirebaseFirestore db) {

        UsuarioDataStore usuarioDataStore = null;

        switch (dataSource) {
            case CLOUD:
                usuarioDataStore = createCloudDataStore(db);
                break;
            case DB:
                usuarioDataStore = new DbUsuarioDataStore(context);
                break;
        }
        return usuarioDataStore;
    }

    private UsuarioDataStore createCloudDataStore(
            FirebaseFirestore db) {
        return new CloudUsuarioDataStore(db );
    }

}

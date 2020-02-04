package pe.com.patadeperro.data.datasource.cloud.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pe.com.patadeperro.data.datasource.cloud.model.CloudUsuario;
import pe.com.patadeperro.data.datasource.datastore.UsuarioDataStore;
import pe.com.patadeperro.data.mapper.UsuarioDataMapper;
import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.presentation.utils.Constants;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloudUsuarioDataStore implements UsuarioDataStore {
    private static final String TAG = "CloudUsuarioDataStore";
    private FirebaseFirestore db;

    public CloudUsuarioDataStore(FirebaseFirestore db) {
        //FirebaseApp.initializeApp(context);
       // db = FirebaseFirestore.getInstance();
        this.db = db;
    }

    @Override
    public void createUsuario(Usuario usuario, RepositoryCallback repositoryCallback) {

        UsuarioDataMapper usuarioDataMapper= new UsuarioDataMapper();
        CloudUsuario cloudUsuario=usuarioDataMapper.transformToCloud(usuario);

        Map<String, Object> data = new HashMap<>();
        // no olvidar el idCloud
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_id, cloudUsuario.getId());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_uid, cloudUsuario.getUid());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_name, cloudUsuario.getName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber, cloudUsuario.getPhoneNumber());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_email, cloudUsuario.getEmail());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_location, new GeoPoint(cloudUsuario.getLat(), cloudUsuario.getLng()));
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_logged, cloudUsuario.isLogged());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_active, cloudUsuario.isActive());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_created_at, cloudUsuario.getCreated_at());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_notifications, cloudUsuario.isNotifications());

        // dentro de este bloque va idCloud
        db.collection(Constants.FIREBASE_TABLES.USER)
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        // aquí está idCloud
                        usuario.setIdCloud(documentReference.getId());
                        usuario.cloudIntCount += 1;
                        repositoryCallback.onSuccess(
                                usuario
                        );
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        repositoryCallback.onError(e);
                    }
                });
    }

    @Override
    public void updateUsuario(Usuario usuario, RepositoryCallback repositoryCallback) {

        UsuarioDataMapper usuarioDataMapper = new UsuarioDataMapper();
        CloudUsuario cloudUsuario = usuarioDataMapper.transformToCloud(usuario);

        Map<String, Object> data = new HashMap<>();

        // no olvidar el idCloud
        usuario.cloudIntCount += 1;
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_id, cloudUsuario.getId());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_uid, cloudUsuario.getUid());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_name, cloudUsuario.getName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber, cloudUsuario.getPhoneNumber());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_email, cloudUsuario.getEmail());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_location, new GeoPoint(cloudUsuario.getLat(), cloudUsuario.getLng()));
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_logged, cloudUsuario.isLogged());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_active, cloudUsuario.isActive());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_created_at, cloudUsuario.getCreated_at());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_notifications, cloudUsuario.isNotifications());

        // aquí está el idCloud
        db.collection(Constants.FIREBASE_TABLES.USER).document(cloudUsuario.getIdCloud())
                .set(data, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        repositoryCallback.onSuccess(usuario);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                repositoryCallback.onError(e);
            }
        });
    }

    @Override
    public void deleteUsuario(Usuario usuario, RepositoryCallback repositoryCallback) {

        UsuarioDataMapper usuarioDataMapper= new UsuarioDataMapper();
        CloudUsuario cloudUsuario=usuarioDataMapper.transformToCloud(usuario);


        Map<String, Object> data = new HashMap<>();
        // no olvidar el idCloud
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_id, cloudUsuario.getId());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_uid, cloudUsuario.getUid());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_name, cloudUsuario.getName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber, cloudUsuario.getPhoneNumber());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_email, cloudUsuario.getEmail());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_location, new GeoPoint(cloudUsuario.getLat(), cloudUsuario.getLng()));
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_logged, cloudUsuario.isLogged());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_active, cloudUsuario.isActive());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_created_at, cloudUsuario.getCreated_at());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_notifications, cloudUsuario.isNotifications());

        // aquí está idCloud
        usuario.cloudIntCount += 1;
        db.collection(Constants.FIREBASE_TABLES.USER).document(cloudUsuario.getIdCloud())
                .delete()
                .addOnSuccessListener
                        (new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void aVoid) {
                                repositoryCallback.onSuccess(usuario);
                            }
                        })
                .addOnFailureListener
                        (new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                repositoryCallback.onError(e);
                            }
                        });
    }

    @Override
    public void usuariosList(RepositoryCallback repositoryCallback) {

        db.collection(Constants.FIREBASE_TABLES.USER)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            repositoryCallback.onError(e);
                            return;
                        }
                        List<Usuario> usuarios = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                            GeoPoint location = doc.getGeoPoint(Constants.FIREBASE_TABLES_FIELDS.USER_location);
                            String idDbKeyStr = doc.get(Constants.FIREBASE_TABLES_FIELDS.USER_id).toString();
                                // int idDbKey = Integer.parseInt(idDbKeyStr);
                                int idDbKey;
                                if (idDbKeyStr==null) {
                                    idDbKey = 0;
                                } else {
                                    idDbKey = Integer.parseInt(idDbKeyStr);
                                }

                            Usuario usuario = new Usuario(
                                    idDbKey,        // Int id
                                    doc.getId(),    // String idCloud
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_uid),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_name),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_email),
                                    location.getLatitude(),
                                    location.getLongitude(),
                                    doc.getBoolean(Constants.FIREBASE_TABLES_FIELDS.USER_logged),
                                    doc.getBoolean(Constants.FIREBASE_TABLES_FIELDS.USER_active),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_created_at),
                                    doc.getBoolean(Constants.FIREBASE_TABLES_FIELDS.USER_notifications)
                                      );
                            usuarios.add(usuario);
                        }
                            repositoryCallback.onSuccess(usuarios);
                    }
                });
    }

    @Override
    public void verifyUsuarioExist(String phone, RepositoryCallback repositoryCallback) {

        db.collection(Constants.FIREBASE_TABLES.USER)
                .whereEqualTo(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber, phone)
                //.orderBy("sent", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            repositoryCallback.onError(e);
                            return;
                        }

                        Usuario usuario = null;

                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                            GeoPoint location = doc.getGeoPoint(Constants.FIREBASE_TABLES_FIELDS.USER_location);
                            int idDbKey = Integer.parseInt(doc.getLong(Constants.FIREBASE_TABLES_FIELDS.USER_id).toString());

                            if (phone.equals(doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber))) {
                                usuario = new Usuario(
                                        idDbKey,        // Int id
                                        doc.getId(),    // String idCloud
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_uid),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_name),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_email),
                                        location.getLatitude(),
                                        location.getLongitude(),
                                        doc.getBoolean(Constants.FIREBASE_TABLES_FIELDS.USER_logged),
                                        doc.getBoolean(Constants.FIREBASE_TABLES_FIELDS.USER_active),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_created_at),
                                        doc.getBoolean(Constants.FIREBASE_TABLES_FIELDS.USER_notifications)

                                );
                                // usuario.setId("");
                            }
                        }

                        repositoryCallback.onSuccess(usuario);
                    }
                });
    }

}

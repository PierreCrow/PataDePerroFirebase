package pe.com.patadeperro.data.datasource.cloud.store;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pe.com.patadeperro.data.datasource.datastore.UsuarioDataStore;
import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.presentation.utils.Constants;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
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

        Map<String, Object> user = new HashMap<>();
        user.put(Constants.FIREBASE_TABLES_FIELDS.USER_name, usuario.getName());
        user.put(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber, usuario.getPhoneNumber());
        user.put(Constants.FIREBASE_TABLES_FIELDS.USER_email, usuario.getEmail());
        user.put(Constants.FIREBASE_TABLES_FIELDS.USER_location, new GeoPoint(usuario.getLat(), usuario.getLng()));
        user.put(Constants.FIREBASE_TABLES_FIELDS.USER_logged, usuario.isLogged());
        user.put(Constants.FIREBASE_TABLES_FIELDS.USER_active, usuario.isActive());
        user.put(Constants.FIREBASE_TABLES_FIELDS.USER_created_at, usuario.getCreated_at());
        user.put(Constants.FIREBASE_TABLES_FIELDS.USER_notifications, usuario.isNotifications());

        db.collection(Constants.FIREBASE_TABLES.USER)
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        usuario.setIdCloud(documentReference.getId());
                        repositoryCallback.onSuccess(usuario);
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

        Map<String, Object> data = new HashMap<>();
//        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_name, usuario.getName());
//        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_email, usuario.getEmail());
//        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_logged, usuario.isLogged());

        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_name, usuario.getName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber, usuario.getPhoneNumber());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_email, usuario.getEmail());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_location, new GeoPoint(usuario.getLat(), usuario.getLng()));
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_logged, usuario.isLogged());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_active, usuario.isActive());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_created_at, usuario.getCreated_at());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_notifications, usuario.isNotifications());

        db.collection(Constants.FIREBASE_TABLES.USER).document(usuario.getIdCloud())
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

        Map<String, Object> data = new HashMap<>();
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_name, usuario.getName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber, usuario.getPhoneNumber());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_email, usuario.getEmail());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_location, new GeoPoint(usuario.getLat(), usuario.getLng()));
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_logged, usuario.isLogged());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_active, usuario.isActive());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_created_at, usuario.getCreated_at());
        data.put(Constants.FIREBASE_TABLES_FIELDS.USER_notifications, usuario.isNotifications());

        db.collection(Constants.FIREBASE_TABLES.USER).document(usuario.getIdCloud())
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

                            if (phone.equals(doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber))) {
                                usuario = new Usuario(
                                        // (Integer) doc.get(Constants.FIREBASE_TABLES_FIELDS.USER_id),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_idCloud),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_uid),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_name),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_phoneNumber),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_email),
                                        0.0,
                                        0.0,
                                        doc.getBoolean(Constants.FIREBASE_TABLES_FIELDS.USER_logged),
                                        doc.getBoolean(Constants.FIREBASE_TABLES_FIELDS.USER_active),
                                        doc.getString(Constants.FIREBASE_TABLES_FIELDS.USER_created_at),
                                        doc.getBoolean(Constants.FIREBASE_TABLES_FIELDS.USER_notifications)
                                        );
                                usuario.setId(0);
                            }
                        }

                        repositoryCallback.onSuccess(usuario);
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
                            String ddd=doc.getId();
                            Usuario usuario = new Usuario(
                                    // (Integer) doc.get(Constants.FIREBASE_TABLES_FIELDS.USER_id),
                                    doc.getId(),
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
}

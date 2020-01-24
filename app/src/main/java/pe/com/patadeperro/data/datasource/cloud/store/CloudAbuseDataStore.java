package pe.com.patadeperro.data.datasource.cloud.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.com.patadeperro.data.datasource.datastore.AbuseDataStore;
import pe.com.patadeperro.domain.model.Abuse;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.presentation.utils.Constants;

public class CloudAbuseDataStore implements AbuseDataStore {
    private static final String TAG = "CloudAbuseDataStore";

    private FirebaseFirestore db;

    public CloudAbuseDataStore(FirebaseFirestore db) {
    //    this.db = FirebaseFirestore.getInstance();
        this.db = db;
    }

    @Override
    public void createAbuse(Abuse abuse, RepositoryCallback repositoryCallback) {

        Map<String, Object> abuseH = new HashMap<>();
        // abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_id, abuse.getId());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_idCloud, abuse.getIdCloud());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_petName, abuse.getPetName());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_race, abuse.getRace());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_gender, abuse.getGender());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_color, abuse.getColor());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_age, abuse.getAge());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_contactPhoneNumber, abuse.getContactPhoneNumber());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_contactName, abuse.getContactName());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_description, abuse.getDescription());
//        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_reward, abuse.getReward());
//        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_rewardAmount, abuse.getRewardAmount());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_country, abuse.getCountry());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_state, abuse.getState());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_city, abuse.getCity());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_urlImage, abuse.getUrlImage());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_lat, abuse.getLat());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_lng, abuse.getLng());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_abuseAddress, abuse.getAbuseAddress());
        abuseH.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_complaint, abuse.getComplaint());


        db.collection(Constants.FIREBASE_TABLES.ABUSE)
                .add(abuse)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        abuse.setIdCloud(documentReference.getId());
                        repositoryCallback.onSuccess(abuse);
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
    public void updateAbuse(Abuse abuse, RepositoryCallback repositoryCallback) {

        Map<String, Object> data = new HashMap<>();
        data.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_petName, abuse.getPetName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.ABUSE_abuseAddress, abuse.getAbuseAddress());

        db.collection(Constants.FIREBASE_TABLES.ABUSE).document(abuse.getIdCloud())
                .set(data, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        repositoryCallback.onSuccess(abuse);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                repositoryCallback.onError(e);
            }
        });
    }

    /*
    @Override
    public void verifyAbuseExist(String phone, RepositoryCallback repositoryCallback) {

        // exigido por el IDE... ¿?
        // Lo dejamos vacío

    }
    */

    @Override
    public void abusesList(RepositoryCallback repositoryCallback) {

        db.collection(Constants.FIREBASE_TABLES.ABUSE)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(
                            @Nullable QuerySnapshot queryDocumentSnapshots,
                            @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            repositoryCallback.onError(e);
                            return;
                        }
                        List<Abuse> abuses = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                            // GeoPoint location = doc.getGeoPoint(Constants.FIREBASE_TABLES_FIELDS.ABUSE_location);
                            Abuse abuse = new Abuse(
                                    // doc.getString(Constants.FIREBASE_TABLES_FIELDS.ABUSE_id),
                                    // doc.getString(Constants.FIREBASE_TABLES_FIELDS.ABUSE_idCloud),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.ABUSE_petName),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.ABUSE_contactPhoneNumber),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.ABUSE_contactName)
                                    );
                            abuses.add(abuse);
                        }
                        repositoryCallback.onSuccess(abuses);
                    }
                });
    }
}

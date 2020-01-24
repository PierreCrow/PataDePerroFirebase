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

import pe.com.patadeperro.data.datasource.datastore.LostDataStore;
import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.presentation.utils.Constants;

public class CloudLostDataStore implements LostDataStore {
    private static final String TAG = "CloudLostDataStore";

    private FirebaseFirestore db;

    public CloudLostDataStore(FirebaseFirestore db) {
    //    this.db = FirebaseFirestore.getInstance();
        this.db = db;
    }

    @Override
    public void createLost(Lost lost, RepositoryCallback repositoryCallback) {

        Map<String, Object> lostH = new HashMap<>();
        // lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_id, lost.getId());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_idCloud, lost.getIdCloud());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_petName, lost.getPetName());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_race, lost.getRace());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_gender, lost.getGender());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_color, lost.getColor());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_age, lost.getAge());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_contactPhoneNumber, lost.getContactPhoneNumber());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_contactName, lost.getContactName());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_description, lost.getDescription());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_reward, lost.getReward());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_rewardAmount, lost.getRewardAmount());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_country, lost.getCountry());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_state, lost.getState());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_city, lost.getCity());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_urlImage, lost.getUrlImage());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lat, lost.getLat());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lng, lost.getLng());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lostAddress, lost.getLostAddress());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_found, lost.getFound());


        db.collection(Constants.FIREBASE_TABLES.LOST)
                .add(lost)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        lost.setIdCloud(documentReference.getId());
                        repositoryCallback.onSuccess(lost);
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
    public void updateLost(Lost lost, RepositoryCallback repositoryCallback) {

        Map<String, Object> data = new HashMap<>();
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_petName, lost.getPetName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lostAddress, lost.getLostAddress());

        db.collection(Constants.FIREBASE_TABLES.LOST).document(lost.getIdCloud())
                .set(data, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        repositoryCallback.onSuccess(lost);
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
    public void verifyLostExist(String phone, RepositoryCallback repositoryCallback) {

        // exigido por el IDE... ¿?
        // Lo dejamos vacío

    }
    */

    @Override
    public void lostsList(RepositoryCallback repositoryCallback) {

        db.collection(Constants.FIREBASE_TABLES.LOST)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(
                            @Nullable QuerySnapshot queryDocumentSnapshots,
                            @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            repositoryCallback.onError(e);
                            return;
                        }
                        List<Lost> losts = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                            // GeoPoint location = doc.getGeoPoint(Constants.FIREBASE_TABLES_FIELDS.LOST_location);
                            Lost lost = new Lost(
                                    // doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_id),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_idCloud),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_petName),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_race),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_gender),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_color),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_age),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_contactPhoneNumber),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_contactName),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_description),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_reward),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_rewardAmount),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_country),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_state),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_city),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_urlImage),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_lat),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_lng),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_lostAddress),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_found)
                                    );
                            losts.add(lost);
                        }
                        repositoryCallback.onSuccess(losts);
                    }
                });
    }
}

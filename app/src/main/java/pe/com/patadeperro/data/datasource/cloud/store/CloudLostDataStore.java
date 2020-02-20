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

import pe.com.patadeperro.data.datasource.cloud.model.CloudLost;
import pe.com.patadeperro.data.datasource.datastore.LostDataStore;
import pe.com.patadeperro.data.mapper.LostDataMapper;
import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.presentation.ui.activities.a38LostSplashActivity;
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

        LostDataMapper lostDataMapper= new LostDataMapper();
        CloudLost cloudLost=lostDataMapper.transformToCloud(lost);

        Map<String, Object> lostH = new HashMap<>();
        // no olvidar el idCloud
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_id, cloudLost.getId());
//        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_idCloud, cloudLost.getIdCloud());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_petName, cloudLost.getPetName());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_race, cloudLost.getRace());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_gender, cloudLost.getGender());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_color, cloudLost.getColor());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_age, cloudLost.getAge());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_contactPhoneNumber, cloudLost.getContactPhoneNumber());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_contactName, cloudLost.getContactName());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_description, cloudLost.getDescription());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_reward, cloudLost.getReward());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_rewardAmount, cloudLost.getRewardAmount());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_country, cloudLost.getCountry());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_state, cloudLost.getState());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_city, cloudLost.getCity());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_urlImage, cloudLost.getUrlImage());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lat, cloudLost.getLat());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lng, cloudLost.getLng());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lostAddress, cloudLost.getLostAddress());
        lostH.put(Constants.FIREBASE_TABLES_FIELDS.LOST_found, cloudLost.getFound());

        // dentro de este bloque va idCloud
        db.collection(Constants.FIREBASE_TABLES.LOST)
                .add(lostH)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        // aquí está idCloud
                        lost.setIdCloud(documentReference.getId());
                        lost.cloudIntCount += 1;
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
    public void createLostList(List<Lost> lostList, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void updateLost(Lost lost, RepositoryCallback repositoryCallback) {

        LostDataMapper lostDataMapper= new LostDataMapper();
        CloudLost cloudLost=lostDataMapper.transformToCloud(lost);

        Map<String, Object> data = new HashMap<>();

        // no olvidar el idCloud
        lost.cloudIntCount += 1;
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_id, cloudLost.getId());
//        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_idCloud, cloudLost.getIdCloud());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_petName, cloudLost.getPetName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_race, cloudLost.getRace());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_gender, cloudLost.getGender());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_color, cloudLost.getColor());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_age, cloudLost.getAge());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_contactPhoneNumber, cloudLost.getContactPhoneNumber());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_contactName, cloudLost.getContactName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_description, cloudLost.getDescription());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_reward, cloudLost.getReward());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_rewardAmount, cloudLost.getRewardAmount());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_country, cloudLost.getCountry());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_state, cloudLost.getState());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_city, cloudLost.getCity());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_urlImage, cloudLost.getUrlImage());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lat, cloudLost.getLat());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lng, cloudLost.getLng());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lostAddress, cloudLost.getLostAddress());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_found, cloudLost.getFound());

        // aquí está el idCloud
        db.collection(Constants.FIREBASE_TABLES.LOST).document(cloudLost.getIdCloud())
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

    @Override
    public void deleteLost(Lost lost, RepositoryCallback repositoryCallback) {

        LostDataMapper lostDataMapper= new LostDataMapper();
        CloudLost cloudLost=lostDataMapper.transformToCloud(lost);

        Map<String, Object> data = new HashMap<>();
        // no olvidar el idCloud
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_id, cloudLost.getId());
//        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_idCloud, cloudLost.getIdCloud());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_petName, cloudLost.getPetName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_race, cloudLost.getRace());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_gender, cloudLost.getGender());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_color, cloudLost.getColor());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_age, cloudLost.getAge());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_contactPhoneNumber, cloudLost.getContactPhoneNumber());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_contactName, cloudLost.getContactName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_description, cloudLost.getDescription());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_reward, cloudLost.getReward());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_rewardAmount, cloudLost.getRewardAmount());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_country, cloudLost.getCountry());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_state, cloudLost.getState());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_city, cloudLost.getCity());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_urlImage, cloudLost.getUrlImage());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lat, cloudLost.getLat());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lng, cloudLost.getLng());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_lostAddress, cloudLost.getLostAddress());
        data.put(Constants.FIREBASE_TABLES_FIELDS.LOST_found, cloudLost.getFound());

        // aquí está idCloud
        lost.cloudIntCount += 1;
        db.collection(Constants.FIREBASE_TABLES.LOST).document(cloudLost.getIdCloud())
                .delete()
                .addOnSuccessListener
                        (new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void aVoid) {
                                repositoryCallback.onSuccess(lost);
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
    public void lostsList(RepositoryCallback repositoryCallback) {

        LostDataMapper lostDataMapper= new LostDataMapper();

        db.collection(Constants.FIREBASE_TABLES.LOST)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            repositoryCallback.onError(e);
                            return;
                        }
                        if (a38LostSplashActivity.flagLostsListLoaded){
                            return;
                        }

                        List<Lost> losts = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                            // GeoPoint location = doc.getGeoPoint(Constants.FIREBASE_TABLES_FIELDS.LOST_location);

                            CloudLost cloudLost = new CloudLost(
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_id),
//                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.LOST_idCloud), <-- Nooo
                                    doc.getId(),    // Este dato será el String idCloud
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

                            Lost lost = lostDataMapper.transformFromCloud(cloudLost);
                            losts.add(lost);
                        }
                        repositoryCallback.onSuccess(losts);
                    }
                });
    }

    /**
     * verifyLostExist ... no por ahora
     *
     @Override
     public void verifyLostExist(String phone, RepositoryCallback repositoryCallback) {

     // exigido por el IDE... ¿?
     // Lo dejamos vacío

     }
     */

}

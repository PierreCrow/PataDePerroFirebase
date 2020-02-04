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

import pe.com.patadeperro.data.datasource.datastore.PetDataStore;
import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.data.mapper.PetDataMapper;
import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.presentation.utils.Constants;

public class CloudPetDataStore implements PetDataStore {
    private static final String TAG = "CloudPetDataStore";

    private FirebaseFirestore db;

    public CloudPetDataStore(FirebaseFirestore db) {
    //    this.db = FirebaseFirestore.getInstance();
        this.db = db;
    }

    @Override
    public void createPet(Pet pet, RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();
        DbPet dbPet=petDataMapper.transformToDb(pet);
        
        Map<String, Object> petH = new HashMap<>();
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_name, dbPet.getName());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_race, dbPet.getRace());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_gender, dbPet.getGender());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_age, dbPet.getAge());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_color, dbPet.getColor());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_qrCode, dbPet.getQrCode());


        db.collection(Constants.FIREBASE_TABLES.PET)
                .add(petH)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        dbPet.setIdCloud(documentReference.getId());
                        repositoryCallback.onSuccess(pet);
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
    public void updatePet(Pet pet, RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();
        DbPet dbPet=petDataMapper.transformToDb(pet);

        Map<String, Object> data = new HashMap<>();
//        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_id, dbPet.getId());
//        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_idCloud, dbPet.getIdCloud());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_idUser, dbPet.getIdUser());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_name, dbPet.getName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_race, dbPet.getRace());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_gender, dbPet.getGender());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_age, dbPet.getAge());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_color, dbPet.getColor());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_qrCode, dbPet.getQrCode());

        db.collection(Constants.FIREBASE_TABLES.PET).document(dbPet.getIdCloud())
                .set(data, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        repositoryCallback.onSuccess(pet);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                repositoryCallback.onError(e);
            }
        });
    }

    /**
     * verifyPetExist ... no por ahora
     * 
    @Override
    public void verifyPetExist(String phone, RepositoryCallback repositoryCallback) {

        // exigido por el IDE... ¿?
        // Lo dejamos vacío

    }
    */

    @Override
    public void petsList(RepositoryCallback repositoryCallback) {

        db.collection(Constants.FIREBASE_TABLES.PET)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            repositoryCallback.onError(e);
                            return;
                        }
                        List<DbPet> dbPets = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                            // GeoPoint location = doc.getGeoPoint(Constants.FIREBASE_TABLES_FIELDS.PET_location);
                            DbPet dbPet = new DbPet(
                                    // (String) doc.get(Constants.FIREBASE_TABLES_FIELDS.PET_id),
                                    // doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_idCloud),
                                    doc.getId(),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_idUser),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_name),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_race),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_gender),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_age),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_color),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_qrCode)
                                    );
                            dbPets.add(dbPet);
                        }
                        repositoryCallback.onSuccess(dbPets);
                    }
                });
    }
}

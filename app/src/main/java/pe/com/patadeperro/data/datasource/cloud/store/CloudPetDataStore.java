package pe.com.patadeperro.data.datasource.cloud.store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

import pe.com.patadeperro.data.datasource.datastore.PetDataStore;
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

        Map<String, Object> petH = new HashMap<>();
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_name, pet.getName());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_race, pet.getRace());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_gender, pet.getGender());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_age, pet.getAge());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_color, pet.getColor());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_qrCode, pet.getQrCode());


        db.collection(Constants.FIREBASE_TABLES.PET)
                .add(pet)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        pet.setIdCloud(documentReference.getId());
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

        Map<String, Object> data = new HashMap<>();
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_name, pet.getName());

        db.collection(Constants.FIREBASE_TABLES.PET).document(pet.getIdCloud())
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


    /*
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
                        List<Pet> pets = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                            // GeoPoint location = doc.getGeoPoint(Constants.FIREBASE_TABLES_FIELDS.PET_location);
                            Pet pet = new Pet(
                                    // (String) doc.get(Constants.FIREBASE_TABLES_FIELDS.PET_id),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_idCloud),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_idUser),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_name),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_race),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_gender),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_age),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_color),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_qrCode)
                                    );
                            pets.add(pet);
                        }
                        repositoryCallback.onSuccess(pets);
                    }
                });
    }
}

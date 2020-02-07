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

import pe.com.patadeperro.data.datasource.cloud.model.CloudPet;
import pe.com.patadeperro.data.datasource.datastore.PetDataStore;
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
        CloudPet cloudPet=petDataMapper.transformToCloud(pet);
        
        Map<String, Object> petH = new HashMap<>();
        // no olvidar el idCloud
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_id, cloudPet.getId());
//        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_idCloud, cloudPet.getIdCloud());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_idUser, cloudPet.getIdUser());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_name, cloudPet.getName());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_race, cloudPet.getRace());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_gender, cloudPet.getGender());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_age, cloudPet.getAge());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_color, cloudPet.getColor());
        petH.put(Constants.FIREBASE_TABLES_FIELDS.PET_qrCode, cloudPet.getQrCode());

        // dentro de este bloque va idCloud
        db.collection(Constants.FIREBASE_TABLES.PET)
                .add(petH)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        // aquí está idCloud
                        pet.setIdCloud(documentReference.getId());
                        pet.cloudIntCount += 1;
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
        CloudPet cloudPet=petDataMapper.transformToCloud(pet);

        Map<String, Object> data = new HashMap<>();

        // no olvidar el idCloud
        pet.cloudIntCount += 1;
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_id, cloudPet.getId());
//        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_idCloud, cloudPet.getIdCloud());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_idUser, cloudPet.getIdUser());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_name, cloudPet.getName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_race, cloudPet.getRace());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_gender, cloudPet.getGender());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_age, cloudPet.getAge());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_color, cloudPet.getColor());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_qrCode, cloudPet.getQrCode());

        // aquí está el idCloud
        db.collection(Constants.FIREBASE_TABLES.PET).document(cloudPet.getIdCloud())
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

    @Override
    public void deletePet(Pet pet, RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();
        CloudPet cloudPet=petDataMapper.transformToCloud(pet);

        Map<String, Object> data = new HashMap<>();
        // no olvidar el idCloud
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_id, cloudPet.getId());
//        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_idCloud, cloudPet.getIdCloud());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_idUser, cloudPet.getIdUser());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_name, cloudPet.getName());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_race, cloudPet.getRace());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_gender, cloudPet.getGender());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_age, cloudPet.getAge());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_color, cloudPet.getColor());
        data.put(Constants.FIREBASE_TABLES_FIELDS.PET_qrCode, cloudPet.getQrCode());

        // aquí está idCloud
        pet.cloudIntCount += 1;
        db.collection(Constants.FIREBASE_TABLES.PET).document(cloudPet.getIdCloud())
                .delete()
                .addOnSuccessListener
                        (new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void aVoid) {
                                repositoryCallback.onSuccess(pet);
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
    public void petsList(RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();

        db.collection(Constants.FIREBASE_TABLES.PET)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            repositoryCallback.onError(e);
                            return;
                        }
                        List<Pet> pets = new ArrayList<>();
                        for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {

                            // GeoPoint location = doc.getGeoPoint(Constants.FIREBASE_TABLES_FIELDS.PET_location);

                            CloudPet cloudPet = new CloudPet(
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_id),
//                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_idCloud), <-- Nooo
                                    doc.getId(),    // Este dato será el String idCloud
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_idUser),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_name),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_race),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_gender),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_age),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_color),
                                    doc.getString(Constants.FIREBASE_TABLES_FIELDS.PET_qrCode)
                                    );

                            Pet pet = petDataMapper.transformFromCloud(cloudPet);
                            pets.add(pet);
                        }
                        repositoryCallback.onSuccess(pets);
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

}

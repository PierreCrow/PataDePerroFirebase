package pe.com.patadeperro.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.datastore.PetDataStore;
import pe.com.patadeperro.data.datasource.datastore.PetDataStoreFactory;
import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.domain.repository.PetRepository;
import pe.com.patadeperro.interactor.pet.PetCreatedCallback;
import pe.com.patadeperro.interactor.pet.PetListCallback;
import pe.com.patadeperro.interactor.pet.PetListCreatedCallback;
import pe.com.patadeperro.interactor.pet.PetUpdatedCallback;
import pe.com.patadeperro.interactor.pet.PetDeletedCallback;

public class PetDataRepository implements PetRepository {

    private final PetDataStoreFactory petDataStoreFactory;

    public PetDataRepository(
            PetDataStoreFactory petDataStoreFactory) {
        this.petDataStoreFactory = petDataStoreFactory;
    }

    @Override
    public void createPet(
            Pet pet,
            int petDataLocation,
            PetCreatedCallback petCreatedCallback) {

        final PetDataStore petDataStore = petDataStoreFactory.create(
//                petDataStoreFactory.CLOUD,
                petDataLocation,
                FirebaseFirestore.getInstance());

        petDataStore.createPet(pet, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                petCreatedCallback.onPetCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                Pet newUser = (Pet) object;
                petCreatedCallback.onPetCreatedSuccess(newUser);
            }
        });
    }

    @Override
    public void createPetList(
            List<Pet> petList,
            int petDataLocation,
            PetListCreatedCallback petListCreateCallback) {

        final PetDataStore petDataStore = petDataStoreFactory.create(
//                petDataStoreFactory.CLOUD,
                petDataLocation,
                FirebaseFirestore.getInstance());

        petDataStore.createPetList(petList, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                petListCreateCallback.onPetListCreateError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<Pet> newPetList = (List<Pet>) object;
                petListCreateCallback.onPetListCreateSuccess(newPetList);
            }
        });
    }

    @Override
    public void updatePet(
            Pet pet,
            int petDataLocation,
            PetUpdatedCallback petUpdatedCallback) {

        final PetDataStore petDataStore = petDataStoreFactory.create(
//                petDataStoreFactory.CLOUD,
                petDataLocation,
                FirebaseFirestore.getInstance());

        petDataStore.updatePet(pet, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                petUpdatedCallback.onPetUpdatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                Pet updPet = (Pet) object;
                petUpdatedCallback.onPetUpdatedSuccess(updPet);
            }
        });

    }

    @Override
    public void deletePet(
            Pet pet,
            int petDataLocation,
            PetDeletedCallback petDeletedCallback) {

        final PetDataStore petDataStore;

        petDataStore = petDataStoreFactory.create(
                petDataLocation,
                FirebaseFirestore.getInstance());

        petDataStore.deletePet(pet, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                petDeletedCallback.onPetDeletedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                Pet updPet = (Pet) object;
                petDeletedCallback.onPetDeletedSuccess(updPet);
            }
        });

    }

    @Override
    public void loadPets(
            int petDataLocation,
            PetListCallback requestListCallback) {

        final PetDataStore petDataStore = petDataStoreFactory.create(
//                petDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());
                petDataLocation, FirebaseFirestore.getInstance());

        petDataStore.petsList(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                requestListCallback.onPetError(message);
            }

            @Override
            public void onSuccess(Object object) {

                ArrayList<Pet> PetList = (ArrayList<Pet>) object;
                requestListCallback.onPetSuccess(PetList);
            }
        });


    }

}

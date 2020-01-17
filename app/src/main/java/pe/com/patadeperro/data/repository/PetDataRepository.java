package pe.com.patadeperro.data.repository;

import pe.com.patadeperro.data.datasource.datastore.PetDataStore;
import pe.com.patadeperro.data.datasource.datastore.PetDataStoreFactory;
import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.domain.repository.PetRepository;
import pe.com.patadeperro.interactor.pet.PetCreatedCallback;
// import pe.com.patadeperro.interactor.pet.PetExistCallback;
import pe.com.patadeperro.interactor.pet.PetListCallback;
import pe.com.patadeperro.interactor.pet.PetUpdatedCallback;

public class PetDataRepository implements PetRepository {

    private final PetDataStoreFactory petDataStoreFactory;

    public PetDataRepository(PetDataStoreFactory petDataStoreFactory) {
        this.petDataStoreFactory = petDataStoreFactory;
    }

    @Override
    public void createPet(Pet pet, PetCreatedCallback petCreatedCallback) {
        final PetDataStore petDataStore = petDataStoreFactory.create(petDataStoreFactory.CLOUD);
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

                Pet newPet = (Pet) object;
                petCreatedCallback.onPetCreatedSuccess(newPet);
            }
        });
    }

    @Override
    public void updatePet(Pet pet, PetUpdatedCallback petUpdatedCallback) {

    }

    @Override
    public void loadPets(PetListCallback requestListCallback) {

    }

}

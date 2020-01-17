package pe.com.patadeperro.domain.repository;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.interactor.pet.PetCreatedCallback;
import pe.com.patadeperro.interactor.pet.PetListCallback;
import pe.com.patadeperro.interactor.pet.PetUpdatedCallback;

public interface PetRepository {

    void createPet(Pet pet, PetCreatedCallback petCreatedCallback);

    void updatePet(Pet pet, PetUpdatedCallback petUpdatedCallback);

    void loadPets(final PetListCallback requestListCallback);

}

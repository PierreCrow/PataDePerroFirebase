package pe.com.patadeperro.domain.repository;

import java.util.List;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.interactor.pet.PetCreatedCallback;
import pe.com.patadeperro.interactor.pet.PetDeletedCallback;
import pe.com.patadeperro.interactor.pet.PetListCallback;
import pe.com.patadeperro.interactor.pet.PetListCreatedCallback;
import pe.com.patadeperro.interactor.pet.PetUpdatedCallback;

public interface PetRepository {

    void createPet(
            Pet pet,
            int petDataLocation,
            PetCreatedCallback petCreatedCallback);

    void createPetList(
            List<Pet> petList,
            int petDataLocation,
            PetListCreatedCallback petListCreateCallback);   // 2020-02-10

    void updatePet(
            Pet pet,
            int petDataLocation,
            PetUpdatedCallback petUpdatedCallback);

    void deletePet(
            Pet pet,
            int petDataLocation,
            PetDeletedCallback petDeletedCallback);

    void loadPets(
            int petDataLocation,
            final PetListCallback requestListCallback);

}

package pe.com.patadeperro.interactor.pet;

import java.util.List;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.repository.PetRepository;

public class PetInteractor {

    private final PetRepository petRepository;

    public PetInteractor(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void createPet(
            Pet pet,
            int petDataLocation,
            PetCreatedCallback petCreatedCallback
    ) {
        petRepository.createPet(
                pet,
                petDataLocation,
                petCreatedCallback);
    }

    public void createPetList(
            List<Pet> petList,
            int petDataLocation,
            PetListCreatedCallback petListCreatedCallback
    ) {
        petRepository.createPetList(
                petList,
                petDataLocation,
                petListCreatedCallback);
    }

    public void updatePet(
            Pet pet,
            int petDataLocation,
            PetUpdatedCallback petUpdatedCallback
    ) {
        petRepository.updatePet(
                pet,
                petDataLocation,
                petUpdatedCallback);
    }

    public void deletePet(
            Pet pet,
            int petDataLocation,
            PetDeletedCallback petDeletedCallback) {

        petRepository.deletePet(
                pet,
                petDataLocation,
                petDeletedCallback);
    }

    public void loadPets(
            int petDataLocation,
            PetListCallback petListCallback) {
        petRepository.loadPets(
                petDataLocation,
                petListCallback);
    }
}

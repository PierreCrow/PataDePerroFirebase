package pe.com.patadeperro.interactor.pet;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.repository.PetRepository;

public class PetInteractor {

    private final PetRepository petRepository;

    public PetInteractor(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public void createPet(
            Pet pet,
            PetCreatedCallback petCreatedCallback
    ) {
        petRepository.createPet(pet, petCreatedCallback);
    }

    public void loadPets(PetListCallback petListCallback) {
        petRepository.loadPets(petListCallback);
    }
}

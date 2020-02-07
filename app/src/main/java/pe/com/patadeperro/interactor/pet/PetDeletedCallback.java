package pe.com.patadeperro.interactor.pet;

import pe.com.patadeperro.domain.model.Pet;

public interface PetDeletedCallback {

    void onPetDeletedSuccess(Pet pet);

    void onPetDeletedError(String message);
}

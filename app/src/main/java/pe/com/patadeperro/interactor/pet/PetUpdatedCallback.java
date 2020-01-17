package pe.com.patadeperro.interactor.pet;

import pe.com.patadeperro.domain.model.Pet;

public interface PetUpdatedCallback {

    void onPetUpdatedSuccess(Pet pet);

    void onPetUpdatedError(String message);
}

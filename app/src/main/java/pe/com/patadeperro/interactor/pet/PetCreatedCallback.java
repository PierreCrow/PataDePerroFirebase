package pe.com.patadeperro.interactor.pet;

import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.domain.model.Pet;

public interface PetCreatedCallback {

    void onPetCreatedSuccess(Pet pet);

    void onPetCreatedError(String message);
}

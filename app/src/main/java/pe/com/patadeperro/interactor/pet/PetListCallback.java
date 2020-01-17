package pe.com.patadeperro.interactor.pet;

import java.util.List;

import pe.com.patadeperro.domain.model.Pet;

public interface PetListCallback {

    void onPetSuccess(List<Pet> petList);

    void onPetError(String message);
}

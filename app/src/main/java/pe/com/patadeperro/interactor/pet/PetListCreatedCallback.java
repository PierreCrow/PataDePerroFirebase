package pe.com.patadeperro.interactor.pet;

import java.util.List;

import pe.com.patadeperro.domain.model.Pet;

public interface PetListCreatedCallback {

    void onPetListCreateSuccess(List<Pet> petList);

    void onPetListCreateError(String message);
}

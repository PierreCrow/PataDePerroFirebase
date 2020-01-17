package pe.com.patadeperro.presentation.view;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Pet;

public interface PetView extends BaseView {

    void petCreated(Pet pet);
    void petUpdated(Pet pet);
    void petsListLoaded(ArrayList<Pet> pets);
    void showErrorMessage(String message);
}

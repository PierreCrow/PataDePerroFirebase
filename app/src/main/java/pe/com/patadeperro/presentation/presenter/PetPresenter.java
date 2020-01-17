package pe.com.patadeperro.presentation.presenter;

import java.util.List;

import pe.com.patadeperro.data.datasource.datastore.PetDataStoreFactory;
import pe.com.patadeperro.data.repository.PetDataRepository;
import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.repository.PetRepository;
import pe.com.patadeperro.interactor.pet.PetCreatedCallback;

import pe.com.patadeperro.interactor.pet.PetInteractor;
import pe.com.patadeperro.interactor.pet.PetListCallback;
import pe.com.patadeperro.interactor.pet.PetUpdatedCallback;
import pe.com.patadeperro.presentation.view.PetView;

public class PetPresenter implements
        Presenter<PetView>,
        PetCreatedCallback,

        PetUpdatedCallback,
        PetListCallback {

    private PetView petView;
    private PetInteractor petInteractor;


    public void createPet(Pet pet)
    {
        petInteractor.createPet(
                pet,this);
    }


    @Override
    public void addView(PetView view) {
        this.petView = view;
        PetRepository requestRepository = new PetDataRepository(new PetDataStoreFactory(this.petView.getContext()));
        petInteractor = new PetInteractor(requestRepository);
    }

    @Override
    public void removeView(PetView view) {

    }

    @Override
    public void onPetCreatedSuccess(Pet pet) {
        petView.petCreated(pet);
    }

    @Override
    public void onPetCreatedError(String message) {

    }

    @Override
    public void onPetUpdatedSuccess(Pet pet) {

    }

    @Override
    public void onPetUpdatedError(String message) {

    }

    @Override
    public void onPetSuccess(List<Pet> petList) {

    }

    @Override
    public void onPetError(String message) {

    }
}

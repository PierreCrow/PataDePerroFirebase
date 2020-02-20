package pe.com.patadeperro.data.datasource.datastore;

import java.util.List;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.repository.RepositoryCallback;

public interface PetDataStore {

    void createPet(Pet pet,
                   RepositoryCallback repositoryCallback);

    void createPetList(List<Pet> petList,
                       RepositoryCallback repositoryCallback);

    void updatePet(Pet pet,
                   RepositoryCallback repositoryCallback);

    void deletePet(Pet pet,
                   RepositoryCallback repositoryCallback);

    void petsList( RepositoryCallback repositoryCallback);

}

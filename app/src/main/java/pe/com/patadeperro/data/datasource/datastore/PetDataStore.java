package pe.com.patadeperro.data.datasource.datastore;

import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.repository.RepositoryCallback;

public interface PetDataStore {

    void createPet(Pet pet, RepositoryCallback repositoryCallback);

    void updatePet(Pet pet, RepositoryCallback repositoryCallback);

    void deletePet(Pet pet, RepositoryCallback repositoryCallback);

    void petsList(RepositoryCallback repositoryCallback);

}

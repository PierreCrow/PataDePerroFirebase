package pe.com.patadeperro.data.datasource.db.store;

import android.content.Context;

import pe.com.patadeperro.data.datasource.datastore.PetDataStore;
import pe.com.patadeperro.data.datasource.db.PdpDb;
import pe.com.patadeperro.data.datasource.db.dao.PetDAO;
import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.data.mapper.PetDataMapper;
import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.repository.RepositoryCallback;

import static com.google.firebase.firestore.core.UserData.Source.Update;

public class DbPetDataStore implements PetDataStore {

    PetDAO petDAO;

    public DbPetDataStore(Context context) {
        petDAO= PdpDb.getDatabase(context).petDAO();
    }

    @Override
    public void createPet(Pet pet, RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();
        DbPet dbPet=petDataMapper.transformToDb(pet);

        try {
            petDAO.InsertOnlyOne(dbPet);
            repositoryCallback.onSuccess(pet);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }

    }

    @Override
    public void updatePet(Pet pet, RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();
        DbPet dbPet=petDataMapper.transformToDb(pet);

        try {
            petDAO.Update(dbPet);
            repositoryCallback.onSuccess(pet);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }

    }

    @Override
    public void petsList(RepositoryCallback repositoryCallback) {

    }


}

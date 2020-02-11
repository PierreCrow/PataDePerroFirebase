package pe.com.patadeperro.data.datasource.db.store;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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

//        dbPet.setId( 0 ); // o null?

        try {
            pet.setId(
                    petDAO.InsertOnlyOne(dbPet).intValue()     // tipos... ver domain/model/pet
            );
            pet.dbIntCount += 1;
            repositoryCallback.onSuccess(pet);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }

    }

    @Override
    public void createPetList(
            List<Pet> petList,
            RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();
//        DbPet dbPet=petDataMapper.transformToDb(pet);

        List<DbPet> dbPetList = new ArrayList<>();
        for (int i=0; i<petList.size(); i++) {
            Pet wrkPet = petList.get(i);
            DbPet wrkDbPet = petDataMapper.transformToDb(wrkPet);
            dbPetList.add( wrkDbPet );
        }

//        dbPet.setId( 0 ); // o null?

        try {
            petDAO.InsertMultiple(dbPetList);     // tipos... ver domain/model/pet
            repositoryCallback.onSuccess(petList);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }

    }

    @Override
    public void updatePet(Pet pet, RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();
        DbPet dbPet=petDataMapper.transformToDb(pet);

        dbPet.setId( pet.getId() );

        try {
            petDAO.updateById(
                    dbPet.getId().toString(),
                    dbPet.getIdCloud(),
                    dbPet.getIdUser(),
                    dbPet.getName(),
                    dbPet.getRace(),
                    dbPet.getGender(),
                    dbPet.getAge(),
                    dbPet.getColor(),
                    dbPet.getQrCode()
                    );
            pet.dbIntCount += 1;
            repositoryCallback.onSuccess(pet);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }

    }

    @Override
    public void deletePet(Pet pet, RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();
        DbPet dbPet = petDataMapper.transformToDb(pet);

        try {
            if (dbPet.getIdCloud()=="*ALL") {       // 2020-02-10 ecv
                petDAO.deleteAll();
            } else {
                petDAO.deleteById(dbPet.getIdCloud());
            }
            pet.dbIntCount += 1;
            repositoryCallback.onSuccess(pet);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }
    }

    @Override
    public void petsList(RepositoryCallback repositoryCallback) {

        PetDataMapper petDataMapper= new PetDataMapper();
//        DbPet dbPet = petDataMapper.transformToDb(pet);

        List<Pet> pets = new ArrayList<>();
        Pet pet;
        DbPet dbPet;

        try {
            List<DbPet> dbPets = petDAO.listAllQ();

            for (int i = 0; i < dbPets.size(); i++) {
//                System.out.println(crunchifyList.get(i));
                dbPet = dbPets.get(i);
                pet = petDataMapper.transformFromDb(dbPet);
                pets.add(pet);
            }
            repositoryCallback.onSuccess(pets);

        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }
    }


}

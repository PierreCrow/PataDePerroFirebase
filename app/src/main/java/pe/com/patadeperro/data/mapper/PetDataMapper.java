package pe.com.patadeperro.data.mapper;

import pe.com.patadeperro.data.datasource.cloud.model.CloudPet;
import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.domain.model.Pet;

public class PetDataMapper {

    public PetDataMapper(){
    }

    public CloudPet transformToCloud(Pet pet)
    {
        CloudPet cloudPet= new CloudPet(
                pet.getId().toString(),
                pet.getIdCloud(),
                pet.getIdUser(),
                pet.getName(),
                pet.getRace(),
                pet.getGender(),
                pet.getAge(),
                pet.getColor(),
                pet.getQrCode()
        );
        return cloudPet;
    }

    public Pet transformFromCloud(CloudPet cloudPet)
    {
        String stringId = cloudPet.getId();
        Integer integerId = 0;

        if (stringId!="") {
            integerId = Integer.parseInt(stringId);   // <-- ojo, para new Pet
        }

        Pet pet= new Pet(
                integerId,
                cloudPet.getIdCloud(),
                cloudPet.getIdUser(),
                cloudPet.getName(),
                cloudPet.getRace(),
                cloudPet.getGender(),
                cloudPet.getAge(),
                cloudPet.getColor(),
                cloudPet.getQrCode()
        );
        return pet;
    }

    public DbPet transformToDb(Pet pet)
    {
        DbPet dbPet= new DbPet(
//                pet.getId(),
                pet.getIdCloud(),
                pet.getIdUser(),
                pet.getName(),
                pet.getRace(),
                pet.getGender(),
                pet.getAge(),
                pet.getColor(),
                pet.getQrCode()
                );
        return dbPet;
    }

    public Pet transformFromDb(DbPet dbPet)
    {
        Pet pet= new Pet(
                dbPet.getId(),
                dbPet.getIdCloud(),
                dbPet.getIdUser(),
                dbPet.getName(),
                dbPet.getRace(),
                dbPet.getGender(),
                dbPet.getAge(),
                dbPet.getColor(),
                dbPet.getQrCode()
        );
        return pet;
    }


}

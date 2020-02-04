package pe.com.patadeperro.data.mapper;

import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.domain.model.Pet;

public class PetDataMapper {

    public PetDataMapper(){
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
}

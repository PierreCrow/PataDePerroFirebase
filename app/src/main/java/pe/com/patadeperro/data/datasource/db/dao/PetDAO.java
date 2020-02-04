package pe.com.patadeperro.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.ArrayList;

import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.domain.model.Pet;


@Dao
public interface PetDAO {

    @Insert
    void InsertOnlyOne(DbPet dbPet);

    @Update
    void Update(DbPet dbPet);

    @Delete
    void Delete(DbPet dbPet);

    @Insert
    void InsertMultiple(ArrayList<Pet> pets);

}

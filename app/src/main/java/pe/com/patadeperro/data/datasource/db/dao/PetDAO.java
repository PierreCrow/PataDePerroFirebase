package pe.com.patadeperro.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.domain.model.Pet;


@Dao
public interface PetDAO {

    @Insert
    Long InsertOnlyOne(DbPet dbPet);
    // 2020-02-05 ECV: Usamos "long" para obtener id

    @Update
    void Update(DbPet dbPet);

    // Method 2: Update by Id
    @Query(
            "UPDATE DbPet " +
            "SET " +
                "idUser = :idUser, "+
                "name = :name, "+
                "race = :race, "+
                "gender = :gender, "+
                "age = :age, "+
                "color = :color, "+
                "qrCode = :qrCode "+
            "WHERE idCloud = :idCloud " +
                "and id = :id"
    )
    void updateById(
            //<editor-fold desc="fields">
            String id,
            String idCloud,
            String idUser,
            String name,
            String race,
            String gender,
            String age,
            String color,
            String qrCode
            //</editor-fold>
    );

    @Delete
    void Delete(DbPet dbPet);

    // Method 2 for DELETE by query
    @Query(
            "DELETE from DbPet " +
            "WHERE idCloud = :idCloud "
    )
    void deleteById(
            String idCloud
    );

    @Insert
    void InsertMultiple(ArrayList<Pet> pets);

    // Method 2: List *all
    //    public List<User> getAllUser(); <-- https://www.vogella.com/tutorials/AndroidSQLite/article.html
    @Query("SELECT * FROM DbPet WHERE :sWhere")
    List<DbPet> listAllQ(String sWhere);


}

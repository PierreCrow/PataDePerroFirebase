package pe.com.patadeperro.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.db.model.DbLost;
import pe.com.patadeperro.domain.model.Lost;

@Dao
public interface LostDAO {

    @Insert
    Long InsertOnlyOne(DbLost dbLost);

    @Insert
    void InsertMultiple(List<DbLost> lostList);

    @Update
    void Update(DbLost dbLost);

    // Method 2: Update by Id
    @Query(
            "UPDATE DbLost " +
            "SET " +
                    "idCloud = :idCloud, " +
                    "petName = :petName, " +
                    "race = :race, " +
                    "gender = :gender, " +
                    "color = :color, " +
                    "age = :age, " +
                    "contactPhoneNumber = :contactPhoneNumber, " +
                    "contactName = :contactName, " +
                    "description = :description, " +
                    "reward = :reward, " +
                    "rewardAmount = :rewardAmount, " +
                    "country = :country, " +
                    "state = :state, " +
                    "city = :city, " +
                    "urlImage = :urlImage, " +
                    "lat = :lat, " +
                    "lng = :lng, " +
                    "lostAddress = :lostAddress, " +
                    "found = :found " +
            "WHERE id = :id "
    )
    void updateById(
            //<editor-fold desc="fields">
            String id,
            String idCloud,
            String petName,
            String race,
            String gender,
            String color,
            String age,
            String contactPhoneNumber,
            String contactName,
            String description,
            String reward,
            String rewardAmount,
            String country,
            String state,
            String city,
            String urlImage,
            String lat,
            String lng,
            String lostAddress,
            String found
//</editor-fold>
    );

    @Delete
    void Delete(DbLost dbLost);

    // Method 2 for DELETE by query
    @Query(
            "DELETE from DbLost " +
                    "WHERE idCloud = :idCloud "
    )
    void deleteById(
            String idCloud
    );

    // Method *ALL, DELETE all rows
    @Query("DELETE from DbLost")
    public void deleteAll();

    @Insert
    void InsertMultiple(ArrayList<Lost> losts);

    // Method 2: List *all
    //    public List<User> getAllUser();
    //    <-- https://www.vogella.com/tutorials/AndroidSQLite/article.html
    //    @Query("SELECT * FROM DbPet WHERE :sWhere") <-- No funcionó con "true" en sWhere
    //
    @Query("SELECT * FROM DbLost")
    List<DbLost> listAllQ();

}

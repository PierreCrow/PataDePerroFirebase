package pe.com.patadeperro.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import pe.com.patadeperro.domain.model.Lost;

import java.util.ArrayList;


@Dao
public interface LostDAO {

    @Insert
    void InsertOnlyOne(Lost lost);

    @Insert
    void InsertMultiple(ArrayList<Lost> losts);

    @Update
    void Update(Lost lost);

    @Delete
    void Delete(Lost lost);

}

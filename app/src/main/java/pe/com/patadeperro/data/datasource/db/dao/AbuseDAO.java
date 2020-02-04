package pe.com.patadeperro.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Abuse;


@Dao
public interface AbuseDAO {

    @Insert
    void InsertOnlyOne(Abuse abuse);

    @Insert
    void InsertMultiple(ArrayList<Abuse> abuses);

    @Update
    void Update(Abuse abuse);

    @Delete
    void Delete(Abuse abuse);

}

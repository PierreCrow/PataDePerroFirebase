package pe.com.patadeperro.data.datasource.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.db.model.DbUsuario;
import pe.com.patadeperro.data.datasource.db.model.DbUsuario;
import pe.com.patadeperro.domain.model.Usuario;


@Dao
public interface UsuarioDAO {

    @Insert
    long InsertOnlyOne(DbUsuario usuario);
//    void InsertOnlyOne(DbUsuario usuario);

    @Update
    void Update(DbUsuario usuario);

    // Method 2: Update by Id
    @Query(
            "UPDATE DbUsuario " +
            "SET " +
                "name = :name, "+
                "phoneNumber = :phoneNumber, "+
                "email = :email, "+
                "lat = :lat, "+
                "lng = :lng, "+
                "logged = :logged, "+
                "active = :active, "+
                "created_at = :created_at, "+
                "notifications = :notifications "+
            "WHERE idCloud = :idCloud " +
                "and id = :id"
    )
    void updateById(
            //<editor-fold desc="fields">
            String id,
            String idCloud,
            String name,
            String phoneNumber,
            String email,
            String lat,
            String lng,
            String logged,
            String active,
            String created_at,
            String notifications
            //</editor-fold>
    );

    @Delete
    void Delete(DbUsuario usuario);

    // Method 2 for DELETE by query
    @Query(
            "DELETE from DbUsuario " +
            "WHERE idCloud = :idCloud "
    )
    void deleteById(
            String idCloud
    );

    @Insert
    void InsertMultiple(ArrayList<Usuario> usuarios);

    // Method 2: List *all
    //    public List<User> getAllUser(); <-- https://www.vogella.com/tutorials/AndroidSQLite/article.html
    @Query("SELECT * FROM DbUsuario WHERE :sWhere")
    List<DbUsuario> listAllQ(String sWhere);

}

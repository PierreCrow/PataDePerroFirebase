package pe.com.patadeperro.data.datasource.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import pe.com.patadeperro.data.datasource.db.dao.AbuseDAO;
import pe.com.patadeperro.data.datasource.db.dao.LostDAO;
import pe.com.patadeperro.data.datasource.db.dao.PetDAO;
import pe.com.patadeperro.data.datasource.db.dao.UsuarioDAO;
import pe.com.patadeperro.data.datasource.db.model.DbLost;
import pe.com.patadeperro.data.datasource.db.model.DbUsuario;
import pe.com.patadeperro.domain.model.Abuse;
import pe.com.patadeperro.data.datasource.db.model.DbPet;
import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.model.Usuario;

@Database(entities = {
        DbUsuario.class,
        DbPet.class,
        DbLost.class,
        Usuario.class,
        Pet.class,
        Lost.class,
        Abuse.class},
        version = 3 , exportSchema = false) // <-- ** version **

public abstract class PdpDb extends RoomDatabase {

    private static PdpDb INSTANCE;
    private static final String DB_NAME = "pdp.db";
    private static Context mContext;

    public static PdpDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PdpDb.class) {
                if (INSTANCE == null) {
                    mContext=context.getApplicationContext();
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PdpDb.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                   // Log.i(TAG, "populating with data...");
//                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract LostDAO lostDAO();
    public abstract AbuseDAO abuseDAO();
    public abstract PetDAO petDAO();
    public abstract UsuarioDAO usuarioDAO();

}

package pe.com.patadeperro.data.mapper;

import pe.com.patadeperro.data.datasource.cloud.model.CloudUsuario;
import pe.com.patadeperro.data.datasource.db.model.DbUsuario;
import pe.com.patadeperro.domain.model.Usuario;

public class UsuarioDataMapper {

    public UsuarioDataMapper(){
    }

    public CloudUsuario transformToCloud(Usuario usuario)
    {
        CloudUsuario cloudUsuario= new CloudUsuario(
                usuario.getId(),
                usuario.getIdCloud(),
                usuario.getUid(),
                usuario.getName(),
                usuario.getPhoneNumber(),
                usuario.getEmail(),
                usuario.getLat(),
                usuario.getLng(),
                usuario.isLogged(),
                usuario.isActive(),
                usuario.getCreated_at(),
                usuario.isNotifications()
                );
        return cloudUsuario;
    }

    public Usuario transformFromCloud(CloudUsuario cloudUsuario)
    {
        Usuario usuario= new Usuario(
                cloudUsuario.getId(),
                cloudUsuario.getIdCloud(),
                cloudUsuario.getUid(),
                cloudUsuario.getName(),
                cloudUsuario.getPhoneNumber(),
                cloudUsuario.getEmail(),
                cloudUsuario.getLat(),
                cloudUsuario.getLng(),
                cloudUsuario.isLogged(),
                cloudUsuario.isActive(),
                cloudUsuario.getCreated_at(),
                cloudUsuario.isNotifications()
        );
        return usuario;
    }


    public DbUsuario transformToDb(Usuario usuario)
    {
        DbUsuario dbUsuario= new DbUsuario(
//                usuario.getId(),
                usuario.getIdCloud(),
                usuario.getUid(),
                usuario.getName(),
                usuario.getPhoneNumber(),
                usuario.getEmail(),
                usuario.getLat(),
                usuario.getLng(),
                usuario.isLogged(),
                usuario.isActive(),
                usuario.getCreated_at(),
                usuario.isNotifications()
        );
        return dbUsuario;
    }

    public Usuario transformFromDb(DbUsuario dbUsuario)
    {
        Usuario usuario= new Usuario(
                dbUsuario.getId(),
                dbUsuario.getIdCloud(),
                dbUsuario.getUid(),
                dbUsuario.getName(),
                dbUsuario.getPhoneNumber(),
                dbUsuario.getEmail(),
                dbUsuario.getLat(),
                dbUsuario.getLng(),
                dbUsuario.isLogged(),
                dbUsuario.isActive(),
                dbUsuario.getCreated_at(),
                dbUsuario.isNotifications()
        );
        return usuario;
    }


}

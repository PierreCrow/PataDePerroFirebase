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
}

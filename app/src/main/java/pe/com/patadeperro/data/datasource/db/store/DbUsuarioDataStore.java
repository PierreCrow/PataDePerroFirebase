package pe.com.patadeperro.data.datasource.db.store;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.datastore.UsuarioDataStore;
import pe.com.patadeperro.data.datasource.db.PdpDb;
import pe.com.patadeperro.data.datasource.db.dao.UsuarioDAO;
import pe.com.patadeperro.data.datasource.db.model.DbUsuario;
import pe.com.patadeperro.data.mapper.UsuarioDataMapper;
import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.domain.repository.RepositoryCallback;

public class DbUsuarioDataStore implements UsuarioDataStore {

    UsuarioDAO usuarioDAO;

    public DbUsuarioDataStore(Context context) {
        usuarioDAO = PdpDb.getDatabase(context).usuarioDAO();
    }

    @Override
    public void createUsuario(Usuario usuario, RepositoryCallback repositoryCallback) {

        UsuarioDataMapper usuarioDataMapper = new UsuarioDataMapper();
        DbUsuario dbUsuario = usuarioDataMapper.transformToDb(usuario);

        try {
            usuario.setId(
                    (int) usuarioDAO.InsertOnlyOne(dbUsuario)
            );
            usuario.dbIntCount += 1;
            repositoryCallback.onSuccess(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }

    }

    @Override
    public void updateUsuario(Usuario usuario, RepositoryCallback repositoryCallback) {

        UsuarioDataMapper usuarioDataMapper= new UsuarioDataMapper();
        DbUsuario dbUsuario = usuarioDataMapper.transformToDb(usuario);

        try {
            usuarioDAO.updateById(
                    usuario.getId().toString(),
                    dbUsuario.getIdCloud(),
                    dbUsuario.getName(),
                    dbUsuario.getPhoneNumber(),
                    dbUsuario.getEmail(),
                    dbUsuario.getLat().toString(),
                    dbUsuario.getLng().toString(),
                    String.valueOf(dbUsuario.isLogged()),
                    String.valueOf(dbUsuario.isActive()),
                    dbUsuario.getCreated_at(),
                    String.valueOf(dbUsuario.isNotifications())
                    );
            usuario.dbIntCount += 1;
            repositoryCallback.onSuccess(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }
    }

    @Override
    public void deleteUsuario(Usuario usuario, RepositoryCallback repositoryCallback) {

        UsuarioDataMapper usuarioDataMapper= new UsuarioDataMapper();
        DbUsuario dbUsuario = usuarioDataMapper.transformToDb(usuario);

        try {
            usuarioDAO.deleteById(dbUsuario.getIdCloud());
            usuario.dbIntCount += 1;
            repositoryCallback.onSuccess(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }
    }

    @Override
    public void verifyUsuarioExist(String phone, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void usuariosList(RepositoryCallback repositoryCallback) {

        UsuarioDataMapper usuarioDataMapper= new UsuarioDataMapper();
//        DbUsuario dbUsuario = usuarioDataMapper.transformToDb(usuario);

        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario;
        DbUsuario dbUsuario;

        try {
            List<DbUsuario> dbUsuarios = usuarioDAO.listAllQ("true");

            for (int i = 0; i < dbUsuarios.size(); i++) {
//                System.out.println(crunchifyList.get(i));
                dbUsuario = dbUsuarios.get(i);
                usuario = usuarioDataMapper.transformFromDb(dbUsuario);
                usuarios.add(usuario);
            }
            repositoryCallback.onSuccess(usuarios);

        } catch (Exception e) {
            e.printStackTrace();
            repositoryCallback.onError(e);
        }
    }


}

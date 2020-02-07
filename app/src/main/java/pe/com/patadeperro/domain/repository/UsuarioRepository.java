package pe.com.patadeperro.domain.repository;

import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.interactor.usuario.UsuarioCreatedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioUpdatedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioDeletedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioExistCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioListCallback;

public interface UsuarioRepository {

    void createUsuario(
            Usuario usuario,
            int usuarioDataLocation,
            UsuarioCreatedCallback usuarioCreatedCallback);

    void updateUsuario(
            Usuario usuario,
            int usuarioDataLocation,
            UsuarioUpdatedCallback usuarioUpdatedCallback);

    void deleteUsuario(
            Usuario usuario,
            int usuarioDataLocation,
            UsuarioDeletedCallback usuarioDeletedCallback);

    void verifyUserExist(String phone, UsuarioExistCallback usuarioExistCallback);

    void loadUsuarios(
            int usuarioDataLocation,
            final UsuarioListCallback requestListCallback);

}

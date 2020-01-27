package pe.com.patadeperro.domain.repository;

import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.interactor.usuario.UsuarioCreatedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioUpdatedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioDeletedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioExistCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioListCallback;


public interface UsuarioRepository {

    void createUsuario(Usuario usuario, UsuarioCreatedCallback usuarioCreatedCallback);

    void updateUsuario(Usuario usuario, UsuarioUpdatedCallback usuarioUpdatedCallback);

    void deleteUsuario(Usuario usuario, UsuarioDeletedCallback usuarioDeletedCallback);

    void verifyUserExist(String phone, UsuarioExistCallback usuarioExistCallback);

    void loadUsuarios(final UsuarioListCallback requestListCallback);

}

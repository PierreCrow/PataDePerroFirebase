package pe.com.patadeperro.interactor.usuario;

import pe.com.patadeperro.domain.model.Usuario;

public interface UsuarioDeletedCallback {

    void onUserDeletedSuccess(Usuario usuario);

    void onUserDeletedError(String message);
}

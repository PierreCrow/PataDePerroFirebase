package pe.com.patadeperro.interactor.usuario;

import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.domain.repository.UsuarioRepository;

public class UsuarioInteractor {

    private final UsuarioRepository usuarioRepository;

    public UsuarioInteractor(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void createUsuario(
            Usuario usuario,
            UsuarioCreatedCallback usuarioCreatedCallback
    ) {
        usuarioRepository.createUsuario(usuario, usuarioCreatedCallback);
    }

    public void updateUsuario(
            Usuario usuario,
            UsuarioUpdatedCallback usuarioUpdatedCallback
    ) {
        usuarioRepository.updateUsuario(usuario, usuarioUpdatedCallback);
    }

    public void deleteUsuario(
            Usuario usuario,
            UsuarioDeletedCallback usuarioDeletedCallback
    ) {
        usuarioRepository.deleteUsuario(usuario, usuarioDeletedCallback);
    }


    public void loadUsuarios(UsuarioListCallback usuarioListCallback) {
        usuarioRepository.loadUsuarios(usuarioListCallback);
    }

}

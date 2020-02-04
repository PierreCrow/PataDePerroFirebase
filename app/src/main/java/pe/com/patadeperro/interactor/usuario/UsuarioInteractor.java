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
            int usuarioDataLocation,
            UsuarioCreatedCallback usuarioCreatedCallback
    ) {
        usuarioRepository.createUsuario(usuario,
                usuarioDataLocation,
                usuarioCreatedCallback);
    }

    public void updateUsuario(
            Usuario usuario,
            int usuarioDataLocation,
            UsuarioUpdatedCallback usuarioUpdatedCallback
    ) {
        usuarioRepository.updateUsuario(
                usuario,
                usuarioDataLocation,
                usuarioUpdatedCallback);
    }

    public void deleteUsuario(
            Usuario usuario,
            int usuarioDataLocation,
            UsuarioDeletedCallback usuarioDeletedCallback
    ) {
        usuarioRepository.deleteUsuario(
                usuario,
                usuarioDataLocation,
                usuarioDeletedCallback);
    }


    public void loadUsuarios(UsuarioListCallback usuarioListCallback) {
        usuarioRepository.loadUsuarios(usuarioListCallback);
    }

}

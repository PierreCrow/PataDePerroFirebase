package pe.com.patadeperro.presentation.presenter;

import pe.com.patadeperro.data.datasource.datastore.UsuarioDataStoreFactory;
import pe.com.patadeperro.data.repository.UsuarioDataRepository;
import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.domain.repository.UsuarioRepository;
import pe.com.patadeperro.interactor.usuario.UsuarioCreatedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioExistCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioInteractor;
import pe.com.patadeperro.interactor.usuario.UsuarioListCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioUpdatedCallback;
import pe.com.patadeperro.presentation.view.UsuarioView;

import java.util.List;

public class UsuarioPresenter implements
        Presenter<UsuarioView>,
        UsuarioCreatedCallback,
        UsuarioExistCallback,
        UsuarioUpdatedCallback,
        UsuarioListCallback {

    private UsuarioView usuarioView;
    private UsuarioInteractor usuarioInteractor;


    public void createUsuario(Usuario usuario)
    {
        usuarioInteractor.createUsuario(
                usuario,this);
    }


    @Override
    public void addView(UsuarioView view) {
        this.usuarioView = view;
        UsuarioRepository requestRepository =
                new UsuarioDataRepository(
                new UsuarioDataStoreFactory(this.usuarioView.getContext())
                );
        usuarioInteractor = new UsuarioInteractor(requestRepository);
    }

    @Override
    public void removeView(UsuarioView view) {

    }

    @Override
    public void onUserCreatedSuccess(Usuario usuario) {
        usuarioView.userCreated(usuario);
    }

    @Override
    public void onUserExistSuccess(Usuario user) {

    }

    @Override
    public void onUserCreatedError(String message) {

    }

    @Override
    public void onUsersSuccess(List<Usuario> escortList) {

    }

    @Override
    public void onUsersError(String message) {

    }

    @Override
    public void onUserUpdatedSuccess(Usuario usuario) {

    }

    @Override
    public void onUserUpdatedError(String message) {

    }
}

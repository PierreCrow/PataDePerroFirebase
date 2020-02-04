package pe.com.patadeperro.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.cloud.store.CloudUsuarioDataStore;
import pe.com.patadeperro.data.datasource.datastore.UsuarioDataStoreFactory;
import pe.com.patadeperro.data.repository.UsuarioDataRepository;
import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.domain.repository.UsuarioRepository;
import pe.com.patadeperro.interactor.usuario.UsuarioCreatedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioUpdatedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioDeletedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioExistCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioInteractor;
import pe.com.patadeperro.interactor.usuario.UsuarioListCallback;

import pe.com.patadeperro.presentation.view.UsuarioView;

import static pe.com.patadeperro.presentation.utils.Constants.CLOUD;

public class UsuarioPresenter implements
        Presenter<UsuarioView>,
        UsuarioCreatedCallback,
        UsuarioUpdatedCallback,
        UsuarioDeletedCallback,
        UsuarioExistCallback,
        UsuarioListCallback {

    private UsuarioView usuarioView;
    private UsuarioInteractor usuarioInteractor;
//    private int dataLocation = CLOUD;       //2020-01/27 ECV

    public void createUsuario(Usuario usuario, int usuarioDataLocation) {
        usuarioInteractor.createUsuario(
                usuario,
                usuarioDataLocation,
                this);
    }

    public void updateUsuario(Usuario usuario, int usuarioDataLocation) {
        usuarioInteractor.updateUsuario(
                usuario,
                usuarioDataLocation,
                this);
    }

    public void deleteUsuario(Usuario usuario, int usuarioDataLocation) {

        usuarioInteractor.deleteUsuario(
                usuario,
                usuarioDataLocation,
                this);
    }

    public void loadUsuarios() {
        usuarioInteractor.loadUsuarios(
                this);
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
    public void onUserCreatedError(String message) {

    }

    @Override
    public void onUserExistSuccess(Usuario user) {

    }

    @Override
    public void onUserUpdatedSuccess(Usuario usuario) {
        usuarioView.userUpdated(usuario);
    }

    @Override
    public void onUserUpdatedError(String message) {

    }

    @Override
    public void onUsersSuccess(List<Usuario> usuarios) {
        usuarioView.usersListLoaded((ArrayList<Usuario>) usuarios);
        //    lista

    }

    @Override
    public void onUsersError(String message) {

    }

    @Override
    public void onUserDeletedSuccess(Usuario usuario) {
        usuarioView.userDeleted(usuario);
    }

    @Override
    public void onUserDeletedError(String message) {

    }
}

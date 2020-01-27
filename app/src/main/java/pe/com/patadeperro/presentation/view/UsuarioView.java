package pe.com.patadeperro.presentation.view;

import pe.com.patadeperro.domain.model.Usuario;

import java.util.ArrayList;

public interface UsuarioView extends BaseView {

    void userCreated(Usuario usuario);
    void userUpdated(Usuario usuario);
    void userDeleted(Usuario usuario);
    void usersListLoaded(ArrayList<Usuario> usuarios);
    void showErrorMessage(String message);
}

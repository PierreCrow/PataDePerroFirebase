package pe.com.patadeperro.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import pe.com.patadeperro.data.datasource.datastore.UsuarioDataStore;
import pe.com.patadeperro.data.datasource.datastore.UsuarioDataStoreFactory;
import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.domain.repository.RepositoryCallback;
import pe.com.patadeperro.domain.repository.UsuarioRepository;
import pe.com.patadeperro.interactor.usuario.UsuarioCreatedCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioExistCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioListCallback;
import pe.com.patadeperro.interactor.usuario.UsuarioUpdatedCallback;

public class UsuarioDataRepository implements UsuarioRepository {

    private final UsuarioDataStoreFactory usuarioDataStoreFactory;

    public UsuarioDataRepository(UsuarioDataStoreFactory usuarioDataStoreFactory) {
        this.usuarioDataStoreFactory = usuarioDataStoreFactory;
    }

    @Override
    public void createUsuario(Usuario usuario, UsuarioCreatedCallback usuarioCreatedCallback) {

        final UsuarioDataStore usuarioDataStore = usuarioDataStoreFactory.create(
                usuarioDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());

        usuarioDataStore.createUsuario(usuario, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                usuarioCreatedCallback.onUserCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                Usuario newUser = (Usuario) object;
                usuarioCreatedCallback.onUserCreatedSuccess(newUser);
            }
        });
    }

    @Override
    public void verifyUserExist(String phone, UsuarioExistCallback usuarioExistCallback) {

    }

    @Override
    public void updateUsuario(Usuario usuario, UsuarioUpdatedCallback usuarioUpdatedCallback) {

    }

    @Override
    public void loadUsuarios(UsuarioListCallback requestListCallback) {

        final UsuarioDataStore usuarioDataStore = usuarioDataStoreFactory.create(
                usuarioDataStoreFactory.CLOUD, FirebaseFirestore.getInstance());

        usuarioDataStore.usuariosList(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                requestListCallback.onUsersError(message);
            }

            @Override
            public void onSuccess(Object object) {

                ArrayList<Usuario> UserList = (ArrayList<Usuario>) object;
                requestListCallback.onUsersSuccess(UserList);
            }
        });

    }

}

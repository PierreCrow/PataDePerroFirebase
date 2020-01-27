package pe.com.patadeperro.data.datasource.datastore;

import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.domain.repository.RepositoryCallback;

public interface UsuarioDataStore {

    void createUsuario(Usuario usuario, RepositoryCallback repositoryCallback);

    void updateUsuario(Usuario usuario, RepositoryCallback repositoryCallback);

    void deleteUsuario(Usuario usuario, RepositoryCallback repositoryCallback);

    void verifyUsuarioExist(String phone, RepositoryCallback repositoryCallback);

    void usuariosList(RepositoryCallback repositoryCallback);

}

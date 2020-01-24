package pe.com.patadeperro.data.datasource.datastore;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.RepositoryCallback;

public interface LostDataStore {

    void createLost(Lost lost, RepositoryCallback repositoryCallback);

    void updateLost(Lost lost, RepositoryCallback repositoryCallback);

    //void verifyLostExist(String phone, RepositoryCallback repositoryCallback);

    void lostsList(RepositoryCallback repositoryCallback);

}

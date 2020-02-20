package pe.com.patadeperro.data.datasource.datastore;

import java.util.List;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.RepositoryCallback;

public interface LostDataStore {

    void createLost(Lost lost,
                    RepositoryCallback repositoryCallback);

    void createLostList(List<Lost> lostList,
                        RepositoryCallback repositoryCallback);

    void updateLost(Lost lost,
                    RepositoryCallback repositoryCallback);

    void deleteLost(Lost lost,
                    RepositoryCallback repositoryCallback);

    void lostsList( RepositoryCallback repositoryCallback);

}

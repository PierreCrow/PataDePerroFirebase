package pe.com.patadeperro.data.datasource.datastore;

import pe.com.patadeperro.domain.model.Abuse;
import pe.com.patadeperro.domain.repository.RepositoryCallback;

public interface AbuseDataStore {

    void createAbuse(Abuse abuse, RepositoryCallback repositoryCallback);

    void updateAbuse(Abuse abuse, RepositoryCallback repositoryCallback);

    //void verifyAbuseExist(String phone, RepositoryCallback repositoryCallback);

    void abusesList(RepositoryCallback repositoryCallback);

}

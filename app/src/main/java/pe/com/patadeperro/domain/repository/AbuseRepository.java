package pe.com.patadeperro.domain.repository;

import pe.com.patadeperro.domain.model.Abuse;
import pe.com.patadeperro.interactor.abuse.AbuseCreatedCallback;
import pe.com.patadeperro.interactor.abuse.AbuseListCallback;
import pe.com.patadeperro.interactor.abuse.AbuseUpdatedCallback;

public interface AbuseRepository {

    void createAbuse(Abuse abuse, AbuseCreatedCallback abuseCreatedCallback);

    void updateAbuse(Abuse abuse, AbuseUpdatedCallback abuseUpdatedCallback);

    void loadAbuses(final AbuseListCallback requestListCallback);

}

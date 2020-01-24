package pe.com.patadeperro.interactor.abuse;

import pe.com.patadeperro.domain.model.Abuse;
import pe.com.patadeperro.domain.repository.AbuseRepository;
import pe.com.patadeperro.interactor.abuse.AbuseCreatedCallback;
import pe.com.patadeperro.interactor.abuse.AbuseListCallback;
import pe.com.patadeperro.interactor.abuse.AbuseUpdatedCallback;

public class AbuseInteractor {

    private final AbuseRepository abuseRepository;

    public AbuseInteractor(AbuseRepository abuseRepository) {
        this.abuseRepository = abuseRepository;
    }

    public void createAbuse(
            Abuse abuse,
            AbuseCreatedCallback abuseCreatedCallback
    ) {
        abuseRepository.createAbuse(abuse, abuseCreatedCallback);
    }

    public void updateAbuse(
            Abuse abuse,
            AbuseUpdatedCallback abuseUpdatedCallback
    ) {
        abuseRepository.updateAbuse(abuse, abuseUpdatedCallback);
    }

    public void loadAbuses(AbuseListCallback abuseListCallback) {
        abuseRepository.loadAbuses(abuseListCallback);
    }
}

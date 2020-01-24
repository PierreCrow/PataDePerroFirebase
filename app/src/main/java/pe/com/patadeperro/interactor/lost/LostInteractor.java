package pe.com.patadeperro.interactor.lost;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.LostRepository;
import pe.com.patadeperro.interactor.lost.LostCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostListCallback;

public class LostInteractor {

    private final LostRepository lostRepository;

    public LostInteractor(LostRepository lostRepository) {
        this.lostRepository = lostRepository;
    }

    public void createLost(
            Lost lost,
            LostCreatedCallback lostCreatedCallback
    ) {
        lostRepository.createLost(lost, lostCreatedCallback);
    }

    public void updateLost(
            Lost lost,
            LostUpdatedCallback lostUpdatedCallback
    ) {
        lostRepository.updateLost(lost, lostUpdatedCallback);
    }

    public void loadLosts(LostListCallback lostListCallback) {
        lostRepository.loadLosts(lostListCallback);
    }
}

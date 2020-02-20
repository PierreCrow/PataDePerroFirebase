package pe.com.patadeperro.interactor.lost;

import java.util.List;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.LostRepository;

public class LostInteractor {

    private final LostRepository lostRepository;

    public LostInteractor(LostRepository lostRepository) {
        this.lostRepository = lostRepository;
    }

    public void createLost(
            Lost lost,
            int lostDataLocation,
            LostCreatedCallback lostCreatedCallback
    ) {
        lostRepository.createLost(
                lost,
                lostDataLocation,
                lostCreatedCallback);
    }

    public void createLostList(
            List<Lost> lostList,
            int lostDataLocation,
            LostListCreatedCallback lostListCreatedCallback
    ) {
        lostRepository.createLostList(
                lostList,
                lostDataLocation,
                lostListCreatedCallback);
    }

    public void updateLost(
            Lost lost,
            int lostDataLocation,
            LostUpdatedCallback lostUpdatedCallback
    ) {
        lostRepository.updateLost(
                lost,
                lostDataLocation,
                lostUpdatedCallback);
    }

    public void deleteLost(
            Lost lost,
            int lostDataLocation,
            LostDeletedCallback lostDeletedCallback) {

        lostRepository.deleteLost(
                lost,
                lostDataLocation,
                lostDeletedCallback);
    }

    public void loadLosts(
            int lostDataLocation,
            LostListCallback lostListCallback) {
        lostRepository.loadLosts(
                lostDataLocation,
                lostListCallback);
    }
}

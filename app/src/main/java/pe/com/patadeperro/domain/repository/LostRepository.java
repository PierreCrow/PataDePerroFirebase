package pe.com.patadeperro.domain.repository;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.interactor.lost.LostCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostListCallback;
import pe.com.patadeperro.interactor.lost.LostUpdatedCallback;

public interface LostRepository {

    void createLost(Lost lost, LostCreatedCallback lostCreatedCallback);

    void updateLost(Lost lost, LostUpdatedCallback lostUpdatedCallback);

    void loadLosts(final LostListCallback requestListCallback);

}

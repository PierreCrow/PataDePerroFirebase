package pe.com.patadeperro.domain.repository;

import java.util.List;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.interactor.lost.LostCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostDeletedCallback;
import pe.com.patadeperro.interactor.lost.LostListCallback;
import pe.com.patadeperro.interactor.lost.LostListCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostUpdatedCallback;

public interface LostRepository {

    void createLost(
            Lost lost,
            int lostDataLocation,
            LostCreatedCallback lostCreatedCallback);

    void createLostList(
            List<Lost> lostList,
            int lostDataLocation,
            LostListCreatedCallback lostListCreateCallback);   // 2020-02-10

    void updateLost(
            Lost lost,
            int lostDataLocation,
            LostUpdatedCallback lostUpdatedCallback);

    void deleteLost(
            Lost lost,
            int lostDataLocation,
            LostDeletedCallback lostDeletedCallback);

    void loadLosts(
            int lostDataLocation,
            final LostListCallback requestListCallback);

}

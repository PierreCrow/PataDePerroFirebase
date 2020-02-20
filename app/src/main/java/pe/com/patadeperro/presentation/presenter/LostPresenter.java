package pe.com.patadeperro.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.datastore.LostDataStoreFactory;
import pe.com.patadeperro.data.repository.LostDataRepository;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.LostRepository;
import pe.com.patadeperro.interactor.lost.LostCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostDeletedCallback;
import pe.com.patadeperro.interactor.lost.LostInteractor;
import pe.com.patadeperro.interactor.lost.LostListCallback;
import pe.com.patadeperro.interactor.lost.LostListCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostUpdatedCallback;
import pe.com.patadeperro.presentation.view.LostView;

public class LostPresenter implements
        Presenter<LostView>,
        LostCreatedCallback,
        LostListCreatedCallback,
        LostUpdatedCallback,
        LostDeletedCallback,
        LostListCallback {

    private LostView lostView;
    private LostInteractor lostInteractor;

    public void createLost(Lost lost, int lostDataLocation) {
        lostInteractor.createLost(
                lost,
                lostDataLocation,
                this);
    }

    public void createLostList(List<Lost> lostList, int lostDataLocation) {
        lostInteractor.createLostList(
                lostList,
                lostDataLocation,
                this);
    }

    public void updateLost(Lost lost, int lostDataLocation) {
        lostInteractor.updateLost(
                lost,
                lostDataLocation,
                this);
    }

    public void deleteLost(Lost lost, int lostDataLocation) {

        lostInteractor.deleteLost(
                lost,
                lostDataLocation,
                this);
    }

    public void loadLosts(int lostDataLocation) {
        lostInteractor.loadLosts(
                lostDataLocation,
                this);
    }

    @Override
    public void addView(LostView view) {
        this.lostView = view;
        LostRepository requestRepository =
                new LostDataRepository(
                        new LostDataStoreFactory(this.lostView.getContext())
                );
        lostInteractor = new LostInteractor(requestRepository);
    }

    @Override
    public void removeView(LostView view) {

    }

    @Override
    public void onLostCreatedSuccess(Lost lost) {
        lostView.lostCreated(lost);
    }

    @Override
    public void onLostCreatedError(String message) {

    }

    @Override
    public void onLostUpdatedSuccess(Lost lost) {
        lostView.lostUpdated(lost);
    }

    @Override
    public void onLostUpdatedError(String message) {

    }

    @Override
    public void onLostSuccess(List<Lost> lostList) {
        lostView.lostsListLoaded((ArrayList<Lost>) lostList);
    }

    @Override
    public void onLostError(String message) {

    }

    @Override
    public void onLostDeletedSuccess(Lost lost) {
        lostView.lostDeleted(lost);
    }

    @Override
    public void onLostDeletedError(String message) {

    }

    @Override
    public void onLostListCreateSuccess(List<Lost> lostList) {
        lostView.lostCreatedList(lostList);
    }

    @Override
    public void onLostListCreateError(String message) {

    }
}

package pe.com.patadeperro.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.datastore.LostDataStoreFactory;
import pe.com.patadeperro.data.repository.LostDataRepository;
import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.domain.repository.LostRepository;
import pe.com.patadeperro.interactor.lost.LostCreatedCallback;
import pe.com.patadeperro.interactor.lost.LostInteractor;
import pe.com.patadeperro.interactor.lost.LostListCallback;
import pe.com.patadeperro.interactor.lost.LostUpdatedCallback;
import pe.com.patadeperro.presentation.view.LostView;

public class LostPresenter implements
        Presenter<LostView>,
        LostCreatedCallback,

        LostUpdatedCallback,
        LostListCallback {

    private LostView lostView;
    private LostInteractor lostInteractor;

    public void createLost(Lost lost) {
        lostInteractor.createLost(
                lost,this);
    }

    public void updateLost(Lost lost) {
        lostInteractor.updateLost(
                lost,this);
    }

    public void loadLosts() {
        lostInteractor.loadLosts(
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
        lostView.lostUpdated(lost); //2020-01-22 ECV

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
}

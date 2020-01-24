package pe.com.patadeperro.presentation.presenter;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.datastore.AbuseDataStoreFactory;
import pe.com.patadeperro.data.repository.AbuseDataRepository;
import pe.com.patadeperro.domain.model.Abuse;
import pe.com.patadeperro.domain.repository.AbuseRepository;
import pe.com.patadeperro.interactor.abuse.AbuseCreatedCallback;
import pe.com.patadeperro.interactor.abuse.AbuseInteractor;
import pe.com.patadeperro.interactor.abuse.AbuseListCallback;
import pe.com.patadeperro.interactor.abuse.AbuseUpdatedCallback;
import pe.com.patadeperro.presentation.view.AbuseView;

public class AbusePresenter implements
        Presenter<AbuseView>,
        AbuseCreatedCallback,

        AbuseUpdatedCallback,
        AbuseListCallback {

    private AbuseView abuseView;
    private AbuseInteractor abuseInteractor;

    public void createAbuse(Abuse abuse) {
        abuseInteractor.createAbuse(
                abuse,this);
    }

    public void updateAbuse(Abuse abuse) {
        abuseInteractor.updateAbuse(
                abuse,this);
    }

    public void loadAbuses() {
        abuseInteractor.loadAbuses(
                this);
    }

    @Override
    public void addView(AbuseView view) {
        this.abuseView = view;
        AbuseRepository requestRepository =
                new AbuseDataRepository(
                        new AbuseDataStoreFactory(this.abuseView.getContext())
                );
        abuseInteractor = new AbuseInteractor(requestRepository);
    }

    @Override
    public void removeView(AbuseView view) {

    }

    @Override
    public void onAbuseCreatedSuccess(Abuse abuse) {
        abuseView.abuseCreated(abuse);
    }

    @Override
    public void onAbuseCreatedError(String message) {

    }

    @Override
    public void onAbuseUpdatedSuccess(Abuse abuse) {
        abuseView.abuseUpdated(abuse); //2020-01-22 ECV

    }

    @Override
    public void onAbuseUpdatedError(String message) {

    }

    @Override
    public void onAbuseSuccess(List<Abuse> abuseList) {
        abuseView.abusesListLoaded((ArrayList<Abuse>) abuseList);

    }

    @Override
    public void onAbuseError(String message) {

    }
}

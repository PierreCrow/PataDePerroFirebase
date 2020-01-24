package pe.com.patadeperro.presentation.view;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Abuse;

public interface AbuseView extends BaseView {

    void abuseCreated(Abuse abuse);
    void abuseUpdated(Abuse abuse);
    void abusesListLoaded(ArrayList<Abuse> abuses);
    void showErrorMessage(String message);
}

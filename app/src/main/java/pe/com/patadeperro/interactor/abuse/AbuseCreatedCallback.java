package pe.com.patadeperro.interactor.abuse;

import pe.com.patadeperro.domain.model.Abuse;

public interface AbuseCreatedCallback {

    void onAbuseCreatedSuccess(Abuse abuse);

    void onAbuseCreatedError(String message);
}

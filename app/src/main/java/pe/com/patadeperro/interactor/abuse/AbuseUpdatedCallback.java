package pe.com.patadeperro.interactor.abuse;

import pe.com.patadeperro.domain.model.Abuse;

public interface AbuseUpdatedCallback {

    void onAbuseUpdatedSuccess(Abuse abuse);

    void onAbuseUpdatedError(String message);
}

package pe.com.patadeperro.interactor.lost;

import pe.com.patadeperro.domain.model.Lost;

public interface LostCreatedCallback {

    void onLostCreatedSuccess(Lost lost);

    void onLostCreatedError(String message);
}

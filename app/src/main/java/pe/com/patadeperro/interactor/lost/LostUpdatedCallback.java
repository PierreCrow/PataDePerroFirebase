package pe.com.patadeperro.interactor.lost;

import pe.com.patadeperro.domain.model.Lost;

public interface LostUpdatedCallback {

    void onLostUpdatedSuccess(Lost lost);

    void onLostUpdatedError(String message);
}

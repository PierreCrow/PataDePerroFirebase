package pe.com.patadeperro.interactor.lost;

import pe.com.patadeperro.domain.model.Lost;

public interface LostDeletedCallback {

    void onLostDeletedSuccess(Lost lost);

    void onLostDeletedError(String message);
}

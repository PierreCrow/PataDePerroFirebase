package pe.com.patadeperro.interactor.lost;

import java.util.List;

import pe.com.patadeperro.domain.model.Lost;

public interface LostListCallback {

    void onLostSuccess(List<Lost> lostList);

    void onLostError(String message);
}

package pe.com.patadeperro.interactor.lost;

import java.util.List;

import pe.com.patadeperro.domain.model.Lost;

public interface LostListCreatedCallback {

    void onLostListCreateSuccess(List<Lost> lostList);

    void onLostListCreateError(String message);
}

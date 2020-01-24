package pe.com.patadeperro.presentation.view;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Lost;

public interface LostView extends BaseView {

    void lostCreated(Lost lost);
    void lostUpdated(Lost lost);
    void lostsListLoaded(ArrayList<Lost> losts);
    void showErrorMessage(String message);
}

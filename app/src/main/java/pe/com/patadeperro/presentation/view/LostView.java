package pe.com.patadeperro.presentation.view;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.domain.model.Lost;

public interface LostView extends BaseView {

    void lostCreated(Lost lost);
    void lostCreatedList(List<Lost> lostList);
    void lostUpdated(Lost lost);
    void lostDeleted(Lost lost);
    void lostsListLoaded(ArrayList<Lost> losts);
    void showErrorMessage(String message);
}

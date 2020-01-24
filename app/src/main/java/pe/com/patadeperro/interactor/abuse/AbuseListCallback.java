package pe.com.patadeperro.interactor.abuse;

import java.util.List;

import pe.com.patadeperro.domain.model.Abuse;

public interface AbuseListCallback {

    void onAbuseSuccess(List<Abuse> abuseList);

    void onAbuseError(String message);
}

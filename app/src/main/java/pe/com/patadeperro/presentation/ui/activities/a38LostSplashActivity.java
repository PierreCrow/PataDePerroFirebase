package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.com.patadeperro.R;
import com.google.firebase.firestore.ListenerRegistration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.presentation.presenter.LostPresenter;
import pe.com.patadeperro.presentation.utils.Constants;
import pe.com.patadeperro.presentation.view.LostView;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static pe.com.patadeperro.presentation.ui.activities.a00MainActivity.EXTRA_MESSAGE;
import static pe.com.patadeperro.presentation.ui.activities.a30LostAddListActivity.lost;

public class a38LostSplashActivity
        extends BaseActivity
        implements LostView {

    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    private Long t0Long = System.currentTimeMillis();
    private Long t1Long = System.currentTimeMillis();

    public static Boolean flagLostsListLoaded = false;
    public static ListenerRegistration lostsList_listenerRegistration;

    public static LostPresenter lostPresenterS;
    public static ArrayList listaLost = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a38_lost_splash);

        t0Long = System.currentTimeMillis();    // toma el tiempo, t0 aquí

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
//                Intent mainIntent = new Intent(
//                        a28LostSplashActivity.this,
//                        a20LostAddListActivity.class);
//                a28LostSplashActivity.this.startActivity(mainIntent);
//                a28LostSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

        lostPresenterS = new LostPresenter();
        lostPresenterS.addView(this);

        flagLostsListLoaded = false;     // 2020-02-21 ecv: control para activar
        lostPresenterS.loadLosts(Constants.CLOUD);    // carga, sigue en lostListLoaded()

    }

    @Override
    public void lostCreated(Lost lost) {

    }

    @Override
    public void lostCreatedList(List<Lost> lostList) {

    }

    @Override
    public void lostUpdated(Lost lost) {

    }

    @Override
    public void lostDeleted(Lost lost) {

    }

    @Override
    public void lostsListLoaded(ArrayList<Lost> losts) {


        if (!flagLostsListLoaded) {

            listaLost = losts;

            flagLostsListLoaded = true;     // 2020-02-20 ecv: control para destruir y anular
            lostPresenterS.loadLosts(Constants.CLOUD);    // 2020-02-21 ecv: self-destruction of agent
//            return;
        }


        Bundle bundle = new Bundle();
        bundle.putSerializable("listaLost", (Serializable) listaLost);

        //** Y envía el paquete a siguiente pantlla...
        Intent intent = new Intent(this, MapLocActivity.class);
        intent.putExtra("listaLost", bundle);

        t1Long = System.currentTimeMillis();    // y aquí para, y calcula si debe esperar
        Long txLong = t1Long-t0Long;            // tiempo transcurrido
        Long tzLong = SPLASH_DISPLAY_LENGTH - txLong;   // lo que falta para SPLASH time
        int intSecondsToWaitSplash = tzLong.intValue()/1000;
        if(tzLong>0){
            this.Sleep(intSecondsToWaitSplash);
        }

        startActivity(intent);


    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;                // <-- ojo, por default puede salir null ¿? Cambie a "this"
    }

    protected void Sleep(int seconds) {
        try {
            sleep(seconds * 1000);
        } catch (InterruptedException e) {
            currentThread().interrupt();
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        t1Long = System.currentTimeMillis();    // y aquí para, y calcula si debe esperar
        Long txLong = t1Long-t0Long;            // tiempo transcurrido
        Long tzLong = SPLASH_DISPLAY_LENGTH - txLong;   // lo que falta para SPLASH time
        int intSecondsToWaitSplash = tzLong.intValue()/1000;
        if(tzLong>0){
            this.Sleep(intSecondsToWaitSplash);
        }


    }
    @Override
    protected void onPause() {
        super.onPause();

//        finish();
        // 2020-02-19 ECV: Datos cargados? Ya no regresa.
        // 2020-02-21 ECV: Con finish(), si se vuelve a llamar, se queda congelado.


    }

}

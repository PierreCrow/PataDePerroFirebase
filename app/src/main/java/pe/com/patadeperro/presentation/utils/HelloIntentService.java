package pe.com.patadeperro.presentation.utils;

//region Cómo usar
// Llamar así:
// Intent intent = new Intent(this, HelloService.class);
// startService(intent);
//
// Detener:
// stopService(new Intent(this, HelloService.class));
//endregion

import android.app.AlertDialog;
import android.app.IntentService;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import org.w3c.dom.Text;

import pe.com.patadeperro.presentation.ui.fragments.MyDialogFragment;

import static java.lang.Thread.sleep;

public class HelloIntentService extends IntentService {

    Handler mHandler;
    Context context;
    Toast toast;

    /**
     * A constructor is required, and must call the super
     * <code><a href="/reference/android/app/IntentService.html#IntentService(java.lang.String)">IntentService(String)</a></code>
     * constructor with a name for the worker thread.
     */
    public HelloIntentService() {
        super("HelloIntentService");
        mHandler = new Handler();
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.

//        context = this;
        HelloIntentServiceSleep(5);

//        mHandler.post(new DisplayToast(this, "Hello World!"));


//        Toast.makeText(context,"onHandle...", Toast.LENGTH_LONG).show();
        HelloAlert("onHandle ", context);

        int duration1 = Toast.LENGTH_SHORT;
        int duration2 = Toast.LENGTH_LONG;


        Netwrk netwrk = new Netwrk();
        boolean nw_status0 = netwrk.isConnected(this);
        boolean nw_status1 = nw_status0;    // para control

//        HelloIntentServiceToast(duration2,
//                "Conexión red: " + Boolean.toString(nw_status0),
//                context);

        HelloAlert("Conexión red: " + Boolean.toString(nw_status0),
                context);


        int max_vueltas = 7;
        int vueltas = max_vueltas;
        while (nw_status0 = nw_status1) {

            HelloIntentServiceSleep(3);
            nw_status1 = netwrk.isConnected(this);

            vueltas = vueltas - 1;
            if (vueltas < 0) break;
        }

        if (nw_status0 != nw_status1) {
            HelloAlert("Cambio estado a Conexión: " + Boolean.toString(nw_status1),
                    context);
        } else {
            HelloAlert("Muchas vueltas... Contador: " + Integer.toString(vueltas),
                    context);
        }

        HelloIntentServiceSleep(10);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Hello service starting", Toast.LENGTH_SHORT).show();
        context = this;
        //        HelloAlert("Hello starts here",this);
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        HelloIntentServiceSleep(5);
        Toast.makeText(this, "Hello service stopped", Toast.LENGTH_LONG).show();
    }

    protected void HelloIntentServiceSleep (int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    protected void HelloIntentServiceToast (int duration, CharSequence message, Context context) {

        //Toast.makeText(this, message, duration).show();
//        context = HelloIntentService.this;

        Toast toast = Toast.makeText(context, message, duration);
//        toast.setGravity(Gravity.CENTER|Gravity.LEFT, 0, 0);
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();       // toast

    }

    protected void HelloAlert (String message, Context context){

//        DialogFragment dialog = new MyDialogFragment();
//        dialog.show(getSupportFragmentManager(), "MyDialogFragmentTag");

//        HelloIntentServiceToast(Toast.LENGTH_LONG, message, context);

        mHandler.post(new DisplayToast(context, message));

    }

}





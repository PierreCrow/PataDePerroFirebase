package pe.com.patadeperro.presentation.utils;

//<editor-fold desc="LocalService Description">
// LocalService
// Basado en ejemplo del manual Android Developers Guide
//
// Aquí lo usaremos para verificar la conexión de red, Internet.
//
// Programador: Enrique Contreras (ecv)
//
// 2020-01-29 ecv: creado
//</editor-fold>

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class LocalService extends Service {
    // Binder given to clients
    private final IBinder binder = (IBinder) new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}



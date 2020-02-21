package pe.com.patadeperro.presentation.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.com.patadeperro.R;

public class a00MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE =
            "pe.com.patadeperro.presentation.ui.activities.a00MainActivity" ;

    protected String TAG = "a00MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a00_main_activity);

        // setContentView() and other activity setup
        if (
                getIntent() != null &&
                getIntent().getExtras() != null &&
                getIntent().getExtras().size() > 0) {

            Log.d(TAG, "Received extras in onCreate()");
            Bundle extras = getIntent().getExtras();
            for (String key : extras.keySet()) {
                Log.d(TAG, "Extra '" + key + "': '" + extras.getString(key) + "'");
            }
        }
        if (!this.isTaskRoot()) {
            Log.d(TAG, "Activity is not root task. Finishing.");
            this.finish();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setContentView(R.layout.a00_main_activity);
    }

    public void onBtnUsuarioClicked(View v) {

        // toast
        Context context = getApplicationContext();
        CharSequence text =
                "Click Botón Usuario"
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, a10UserAddListActivity.class);
        String message = "algún dato de usuario";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    } // onItemClicked usuario

    public void onBtnPetClicked(View v) {
        // toast
        Context context = getApplicationContext();
        CharSequence text = "Click Botón Mascota";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, a28PetSplashActivity.class);
        String message = "algún dato de mascota";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    } // onItemClicked mascota

    public void onBtnLostClicked(View v) {
        // toast
        Context context = getApplicationContext();
        CharSequence text =
                "Click Botón Perdidos"
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, a38LostSplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);


        String message = "algún dato de la mascota perdida";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    } // onItemClicked perdido

    public void onBtnAbuseClicked(View v) {
        // toast
        Context context = getApplicationContext();
        CharSequence text =
                "Click Botón Abuso"
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, a40AbuseAddListActivity.class);
        String message = "algún dato de la mascota abusada";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    } // onItemClicked abuso

    public void onBtnMapboxClicked(View v) {
        // toast
        Context context = getApplicationContext();
        CharSequence text =
                "Click Botón Mapbox"
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, MapboxActivity.class);
        String message = "algún dato para Mapbox";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    } // onItemClicked

    public void onBtnMapLocClicked(View v) {
        // toast
        Context context = getApplicationContext();
        CharSequence text =
                "Click Botón MapLoc"
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, MapLocActivity.class);
        String message = "algún dato para MapLoc";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    } // onItemClicked

}

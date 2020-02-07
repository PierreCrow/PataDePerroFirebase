package pe.com.patadeperro.presentation.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.com.patadeperro.R;

public class a00MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE =
            "pe.com.patadeperro.presentation.ui.activities.a00MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a00_main_activity);

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
        CharSequence text =
                "Click Botón Mascota"
                ;
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

        Intent intent = new Intent(this, a30LostAddListActivity.class);
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

}

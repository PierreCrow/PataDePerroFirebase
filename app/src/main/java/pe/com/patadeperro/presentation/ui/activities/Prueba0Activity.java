package pe.com.patadeperro.presentation.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.com.patadeperro.R;

public class Prueba0Activity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE =
            "pe.com.patadeperro.presentation.ui.activities.Prueba0Activity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba0_activity);
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

        Intent intent = new Intent(this, PruebaActivity.class);
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

        Intent intent = new Intent(this, Prueba2Activity.class);
        String message = "algún dato de mascota";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    } // onItemClicked mascota

}

package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.com.patadeperro.R;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Abuse;
import pe.com.patadeperro.presentation.presenter.AbusePresenter;
import pe.com.patadeperro.presentation.view.AbuseView;

import static pe.com.patadeperro.presentation.ui.activities.Prueba00MainActivity.EXTRA_MESSAGE;

/**
 * Clase ** Prueba42AbuseUpdDelActivity ****************************
 */
public class Prueba42AbuseUpdDelActivity
        extends BaseActivity
        implements AbuseView
{

    /*****************************************
    Variables, definición de objetos
         */
    AbusePresenter AbusePresenter;
    Abuse abuse42;
    // int position12;

    /*********************************************************************************** 
    método onPause
    */
    @Override
    public void onPause() {
        super.onPause();
    }

    /************************************************************************
    método onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba42_abuse_upd_del_activity);

        /**
         * // Get the Intent that started this activity and extract the string
        // ... antes obtenía así: Abuse = (Abuse) listaAbuse.get(position);
 */

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("objetoAbuse");
        abuse42 = (Abuse) bundle.getSerializable("objetoAbuse");

        String message = intent.getStringExtra( EXTRA_MESSAGE );
        String s_position = message;   // posición del arreglo

        // Conecta los campos de pantalla
        TextView et42id = findViewById(R.id.et42id);
        TextView et42idCloud = findViewById(R.id.et42idCloud);
        TextView et42petName = findViewById(R.id.et42petName);
        TextView et42race = findViewById(R.id.et42race);
        TextView et42gender = findViewById(R.id.et42gender);
        TextView et42color = findViewById(R.id.et42color);
        TextView et42age = findViewById(R.id.et42age);
        TextView et42contactPhoneNumber = findViewById(R.id.et42contactPhoneNumber);
        TextView et42contactName = findViewById(R.id.et42contactName);
        TextView et42description = findViewById(R.id.et42description);
        TextView et42country = findViewById(R.id.et42country);
        TextView et42state = findViewById(R.id.et42state);
        TextView et42city = findViewById(R.id.et42city);
        TextView et42urlImage = findViewById(R.id.et42urlImage);
        TextView et42lat = findViewById(R.id.et42lat);
        TextView et42lng = findViewById(R.id.et42lng);
        TextView et42abuseAddress = findViewById(R.id.et42abuseAddress);
        TextView et42complaint = findViewById(R.id.et42complaint);

        TextView tv42position = findViewById(R.id.tv42position);

        // carga los campos de la pantalla
        et42id.setText(abuse42.getId());
        et42idCloud.setText(abuse42.getIdCloud());
        et42petName.setText(abuse42.getPetName());
        et42race.setText(abuse42.getRace());
        et42gender.setText(abuse42.getGender());
        et42color.setText(abuse42.getColor());
        et42age.setText(abuse42.getAge());
        et42contactPhoneNumber.setText(abuse42.getContactPhoneNumber());
        et42contactName.setText(abuse42.getContactName());
        et42description.setText(abuse42.getDescription());
        et42country.setText(abuse42.getCountry());
        et42state.setText(abuse42.getState());
        et42city.setText(abuse42.getCity());
        et42urlImage.setText(abuse42.getUrlImage());
        et42lat.setText(abuse42.getLat());
        et42lng.setText(abuse42.getLng());
        et42abuseAddress.setText(abuse42.getAbuseAddress());
        et42complaint.setText(abuse42.getComplaint());

        tv42position.setText(s_position);   // posición en el arreglo

    }   // onCreate

    /**
     * Called when ... taps the OK btn
     */
    public void UpdateAndFinish(View view) {

        // actualiza arreglo correspondiente
        // antes, conexión a los campos en pantalla

        // Conecta los campos de pantalla
        TextView et42id = findViewById(R.id.et42id);
        TextView et42idCloud = findViewById(R.id.et42idCloud);
        TextView et42petName = findViewById(R.id.et42petName);
        TextView et42race = findViewById(R.id.et42race);
        TextView et42gender = findViewById(R.id.et42gender);
        TextView et42color = findViewById(R.id.et42color);
        TextView et42age = findViewById(R.id.et42age);
        TextView et42contactPhoneNumber = findViewById(R.id.et42contactPhoneNumber);
        TextView et42contactName = findViewById(R.id.et42contactName);
        TextView et42description = findViewById(R.id.et42description);
        TextView et42country = findViewById(R.id.et42country);
        TextView et42state = findViewById(R.id.et42state);
        TextView et42city = findViewById(R.id.et42city);
        TextView et42urlImage = findViewById(R.id.et42urlImage);
        TextView et42lat = findViewById(R.id.et42lat);
        TextView et42lng = findViewById(R.id.et42lng);
        TextView et42abuseAddress = findViewById(R.id.et42abuseAddress);
        TextView et42complaint = findViewById(R.id.et42complaint);

        TextView tv42position = findViewById(R.id.tv42position);

        // actualiza el item (objeto) de la lista
//        abuse42.setId(et42id.getText().toString());
        abuse42.setIdCloud(et42idCloud.getText().toString());
        abuse42.setPetName(et42petName.getText().toString());
        abuse42.setRace(et42race.getText().toString());
        abuse42.setGender(et42gender.getText().toString());
        abuse42.setColor(et42color.getText().toString());
        abuse42.setAge(et42age.getText().toString());
        abuse42.setContactPhoneNumber(et42contactPhoneNumber.getText().toString());
        abuse42.setContactName(et42contactName.getText().toString());
        abuse42.setDescription(et42description.getText().toString());
        abuse42.setCountry(et42country.getText().toString());
        abuse42.setState(et42state.getText().toString());
        abuse42.setCity(et42city.getText().toString());
        abuse42.setUrlImage(et42urlImage.getText().toString());
        abuse42.setLat(et42lat.getText().toString());
        abuse42.setLng(et42lng.getText().toString());
        abuse42.setAbuseAddress(et42abuseAddress.getText().toString());
        abuse42.setComplaint(et42complaint.getText().toString());

        String message = tv42position.getText().toString();;

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Term.22 pos.: " + tv42position.getText().toString()
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast


        // lostPresenter.updateAbuse(pet12);  // <-- todavía no probar


/**
 * prueba Intent con return... no funcinó finishActivity ¿?

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",lost);
        setResult(RESULT_OK,returnIntent);

        // setResult(RESULT_CANCELED, returnIntent);    <-- si falla

        finishActivity(1);
*/

        // new intent
        Intent intent = new Intent(this, Prueba40AbuseAddListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "OK abuse " + message );
        startActivity(intent);

    } // clic en botón

    /*************************************************************************************-
    método lostCreated
    */
    @Override
    public void abuseCreated(Abuse lost) {

    }

    /*************************************************************************************-
    método abuseUpdated
    */
    @Override
    public void abuseUpdated(Abuse lost) {



    }

    /*************************************************************************************-
    método abuseListLoaded
    */
    @Override
    public void abusesListLoaded(ArrayList<Abuse> losts) {

    }

    /*************************************************************************************-
    método showErrorMessage
    */
    @Override
    public void showErrorMessage(String message) {

    }

    /*************************************************************************************-
    método getContext
    */
    @Override
    public Context getContext() {
        return this;
    }

}

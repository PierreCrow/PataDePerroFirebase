package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.com.patadeperro.R;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.presentation.presenter.UsuarioPresenter;
import pe.com.patadeperro.presentation.view.UsuarioView;

import static pe.com.patadeperro.presentation.ui.activities.Prueba00Activity.EXTRA_MESSAGE;

/**
 * Clase ** Prueba12Activity ****************************
 */
public class Prueba12Activity
        extends BaseActivity
        implements UsuarioView
{

    /*****************************************
    Variables, definición de objetos
         */
    UsuarioPresenter usuarioPresenter;
    Usuario user12;
    // int position12;

    TextView et32name;

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
        setContentView(R.layout.prueba12_activity);

        initUI();
        /**
         * // Get the Intent that started this activity and extract the string
        // ... antes obtenía así: Usuario = (Usuario) listaUsuario.get(position);
 */

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("objetoUser");
        user12 = (Usuario) bundle.getSerializable("objetoUser");

        String message = intent.getStringExtra( EXTRA_MESSAGE );
        String s_position = message;   // posición del arreglo

        // Conecta los campos de pantalla

        // TextView et32id = findViewById(R.id.et32id;
        // TextView et32idCloud = findViewById(R.id.et32idCloud);

        TextView et32phoneNumber = findViewById(R.id.et32phoneNumber);
        TextView et32email = findViewById(R.id.et32email);
        TextView et32lat = findViewById(R.id.et32lat);
        TextView et32lng = findViewById(R.id.et32lng);
        TextView et32logged = findViewById(R.id.et32logged);
        TextView et32active = findViewById(R.id.et32active);
        TextView et32created_at = findViewById(R.id.et32created_at);
        TextView et32notifications = findViewById(R.id.et32notifications);

        TextView tv32position = findViewById(R.id.tv32position);

        // carga los campos de la pantalla

        //et32id.setText(user12.getId());
        //et32idCloud.setText(user12.getIdCloud());
        et32name.setText(user12.getName());
        et32phoneNumber.setText(user12.getPhoneNumber());
        et32email.setText(user12.getEmail());
        et32lat.setText(user12.getLat().toString());  // nro.
        et32lng.setText(user12.getLng().toString());  // nro.
        et32logged.setText(String.valueOf(user12.isLogged()));    // boolean
        et32active.setText(String.valueOf(user12.isActive()));
        et32created_at.setText(user12.getCreated_at());
        et32notifications.setText(String.valueOf(user12.isNotifications()));

        tv32position.setText(s_position);   // posición en el arreglo

    }   // onCreate


    void initUI()
    {
        usuarioPresenter= new UsuarioPresenter();
        usuarioPresenter.addView(this);
        et32name = findViewById(R.id.et32name);
    }

    /**
     * Called when the user taps the OK btn
     */
    public void UpdateAndFinish(View view) {

        // Conecta los campos de pantalla
        TextView et32name = findViewById(R.id.et32name);
        TextView et32phoneNumber = findViewById(R.id.et32phoneNumber);
        TextView et32email = findViewById(R.id.et32email);
        TextView et32lat = findViewById(R.id.et32lat);
        TextView et32lng = findViewById(R.id.et32lng);
        TextView et32logged = findViewById(R.id.et32logged);
        TextView et32active = findViewById(R.id.et32active);
        TextView et32created_at = findViewById(R.id.et32created_at);
        TextView et32notifications = findViewById(R.id.et32notifications);

        TextView tv32position = findViewById(R.id.tv32position);

        // actualiza el item (objeto) de la lista
        user12.setName(et32name.getText().toString());
        user12.setPhoneNumber(et32phoneNumber.getText().toString());
        user12.setEmail(et32email.getText().toString());
        user12.setLat(Double.parseDouble(et32lat.getText().toString()));
        user12.setLng(Double.parseDouble(et32lng.getText().toString()));
        user12.setLogged(Boolean.parseBoolean(et32logged.getText().toString()));
        user12.setActive(Boolean.parseBoolean(et32active.getText().toString()));
        user12.setCreated_at(et32created_at.getText().toString());
        user12.setNotifications(Boolean.parseBoolean(et32notifications.getText().toString()));

        String message = tv32position.getText().toString();;

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Update12 pos.: " + tv32position.getText().toString()
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast


         usuarioPresenter.updateUsuario(user12);  // <-- test


/**
 * prueba Intent con return... no funcinó finishActivity ¿?

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",lost);
        setResult(RESULT_OK,returnIntent);

        // setResult(RESULT_CANCELED, returnIntent);    <-- si falla

        finishActivity(1);
*/

        // new intent
        Intent intent = new Intent(this, Prueba10Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "OK user " + message );
        startActivity(intent);

    } // clic en botón

    /**
     * Called when the user taps the DEL btn
     */
    public void DeleteAndFinish(View view) {

        // Conecta los campos de pantalla
        TextView et32name = findViewById(R.id.et32name);
        TextView et32phoneNumber = findViewById(R.id.et32phoneNumber);
        TextView et32email = findViewById(R.id.et32email);
        TextView et32lat = findViewById(R.id.et32lat);
        TextView et32lng = findViewById(R.id.et32lng);
        TextView et32logged = findViewById(R.id.et32logged);
        TextView et32active = findViewById(R.id.et32active);
        TextView et32created_at = findViewById(R.id.et32created_at);
        TextView et32notifications = findViewById(R.id.et32notifications);

        TextView tv32position = findViewById(R.id.tv32position);

        // actualiza el item (objeto) de la lista
        user12.setName(et32name.getText().toString());
        user12.setPhoneNumber(et32phoneNumber.getText().toString());
        user12.setEmail(et32email.getText().toString());
        user12.setLat(Double.parseDouble(et32lat.getText().toString()));
        user12.setLng(Double.parseDouble(et32lng.getText().toString()));
        user12.setLogged(Boolean.parseBoolean(et32logged.getText().toString()));
        user12.setActive(Boolean.parseBoolean(et32active.getText().toString()));
        user12.setCreated_at(et32created_at.getText().toString());
        user12.setNotifications(Boolean.parseBoolean(et32notifications.getText().toString()));

        String message = tv32position.getText().toString();;

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Delete12 pos.: " + tv32position.getText().toString()
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast


        usuarioPresenter.deleteUsuario(user12);  // <-- test


        // new intent
        Intent intent = new Intent(this, Prueba10Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "DEL user " + message );
        startActivity(intent);

    } // clic en botón

    /*************************************************************************************-
    método lostCreated
    */
    @Override
    public void userCreated(Usuario user12) {

    }

    /*************************************************************************************-
    método userUpdated
    */
    @Override
    public void userUpdated(Usuario user12) {



    }

    @Override
    public void userDeleted(Usuario usuario) {

    }

    /*************************************************************************************-
    método userListLoaded
    */
    @Override
    public void usersListLoaded(ArrayList<Usuario> users) {

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

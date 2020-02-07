package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.com.patadeperro.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.presentation.presenter.UsuarioPresenter;
import pe.com.patadeperro.presentation.utils.Constants;
import pe.com.patadeperro.presentation.utils.HelloIntentService;
import pe.com.patadeperro.presentation.utils.Netwrk;
import pe.com.patadeperro.presentation.view.UsuarioView;

import static pe.com.patadeperro.presentation.ui.activities.a00MainActivity.EXTRA_MESSAGE;

/**
 * Clase ** a12UserUpdDelActivity ** Usuario, detalles **
 */
public class a12UserUpdDelActivity
        extends BaseActivity
        implements UsuarioView
{

    /*****************************************
    Variables, definición de objetos
         */
    UsuarioPresenter usuarioPresenter;
    Usuario user12;

    //<editor-fold desc="@BindView de Butter Knife">
    // @BindView(R.id.tv32id) EditText tv_id;

    @BindView(R.id.et32name) EditText et_name;
    @BindView(R.id.et32phoneNumber) EditText et_phoneNumber;
    @BindView(R.id.et32email) EditText et_email;
    @BindView(R.id.et32lat) EditText et_lat;
    @BindView(R.id.et32lng) EditText et_lng;
    @BindView(R.id.et32logged) EditText et_logged;
    @BindView(R.id.et32active) EditText et_active;
    @BindView(R.id.et32created_at) EditText et_created_at;
    @BindView(R.id.et32notifications) EditText et_notifications;
    //</editor-fold>

    void initUI()
    {
        usuarioPresenter= new UsuarioPresenter();
        usuarioPresenter.addView(this);

//        et32name = findViewById(R.id.et32name);

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        stopService(new Intent(this, HelloIntentService.class));

    }

    /************************************************************************
    método onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a12_user_upd_del_activity);

        ButterKnife.bind(this);
        initUI();

        // Probando HelloService
        Intent intentSrv = new Intent(this, HelloIntentService.class);
        startService(intentSrv);


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

//        TextView et32name = findViewById(R.id.et32name);
//        TextView et32phoneNumber = findViewById(R.id.et32phoneNumber);
//        TextView et32email = findViewById(R.id.et32email);
//        TextView et32lat = findViewById(R.id.et32lat);
//        TextView et32lng = findViewById(R.id.et32lng);
//        TextView et32logged = findViewById(R.id.et32logged);
//        TextView et32active = findViewById(R.id.et32active);
//        TextView et32created_at = findViewById(R.id.et32created_at);
//        TextView et32notifications = findViewById(R.id.et32notifications);

        TextView tv32position = findViewById(R.id.tv32position);
        TextView tv32id = findViewById(R.id.tv32id);

        // carga los campos de la pantalla

        //et32id.setText(user12.getId());
        //et32idCloud.setText(user12.getIdCloud());
        et_name.setText(user12.getName());
        et_phoneNumber.setText(user12.getPhoneNumber());
        et_email.setText(user12.getEmail());
        et_lat.setText(user12.getLat().toString());  // nro.
        et_lng.setText(user12.getLng().toString());  // nro.
        et_logged.setText(String.valueOf(user12.isLogged()));    // boolean
        et_active.setText(String.valueOf(user12.isActive()));
        et_created_at.setText(user12.getCreated_at());
        et_notifications.setText(String.valueOf(user12.isNotifications()));

        tv32position.setText(s_position);   // posición en el arreglo
        tv32id.setText(  Integer.toString( user12.getId())  );

    }   // onCreate

    /**
     * Called when the user taps the OK btn
     */
    public void UpdateAndFinish(View view) {

//        // Conecta los campos de pantalla
//        TextView et32name = findViewById(R.id.et32name);
//        TextView et32phoneNumber = findViewById(R.id.et32phoneNumber);
//        TextView et32email = findViewById(R.id.et32email);
//        TextView et32lat = findViewById(R.id.et32lat);
//        TextView et32lng = findViewById(R.id.et32lng);
//        TextView et32logged = findViewById(R.id.et32logged);
//        TextView et32active = findViewById(R.id.et32active);
//        TextView et32created_at = findViewById(R.id.et32created_at);
//        TextView et32notifications = findViewById(R.id.et32notifications);

        TextView tv32position = findViewById(R.id.tv32position);
        TextView tv32id = findViewById(R.id.tv32id);

        // actualiza el item (objeto) de la lista
        user12.setName(et_name.getText().toString());
        user12.setPhoneNumber(et_phoneNumber.getText().toString());
        user12.setEmail(et_email.getText().toString());
        user12.setLat(Double.parseDouble(et_lat.getText().toString()));
        user12.setLng(Double.parseDouble(et_lng.getText().toString()));
        user12.setLogged(Boolean.parseBoolean(et_logged.getText().toString()));
        user12.setActive(Boolean.parseBoolean(et_active.getText().toString()));
        user12.setCreated_at(et_created_at.getText().toString());
        user12.setNotifications(Boolean.parseBoolean(et_notifications.getText().toString()));

        String message = tv32position.getText().toString();;
        // user12.setId( Integer.parseInt(tv32id.getText().toString()) );

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Update12 pos.: " + tv32position.getText().toString()
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast

        usuarioPresenter.updateUsuario(user12, Constants.CLOUD);  // <-- test


/**
 * prueba Intent con return... no funcinó finishActivity ¿?

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",lost);
        setResult(RESULT_OK,returnIntent);

        // setResult(RESULT_CANCELED, returnIntent);    <-- si falla

        finishActivity(1);
*/

        // new intent
        Intent intent = new Intent(this, a10UserAddListActivity.class);
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
        TextView et32id = findViewById(R.id.tv32id);

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

        CharSequence text;

        Netwrk netwrk = new Netwrk();
        if (netwrk.isConnected(getContext())) {
            text = "RED, Delete12 pos.: " + tv32position.getText().toString();

            usuarioPresenter.deleteUsuario(user12, Constants.CLOUD);  // <-- ejecuta DELETE

        } else  {
            text = "RED, no hay... No actualizará.";
        }


        // toast

        Context context = getApplicationContext();
//        CharSequence text = <-- cargado arriba
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast


//        usuarioPresenter.deleteUsuario(user12, Constants.CLOUD);  // <-- test


        // new intent
        Intent intent = new Intent(this, a10UserAddListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "DEL user " + message );
        startActivity(intent);

    } // clic en botón

    /*************************************************************************************-
    método userCreated
    */
    @Override
    public void userCreated(Usuario user) {
        user12 = user;
        usuarioPresenter.updateUsuario(user12, Constants.CLOUD);
    }

    /*************************************************************************************-
    método userUpdated
    */
    @Override
    public void userUpdated(Usuario user12) {

        if (user12.getId() == 0) {
            usuarioPresenter.createUsuario(user12, Constants.DB);
        } else if (user12.getCloudIntCount() > user12.getDbIntCount()) {
            usuarioPresenter.updateUsuario(user12, Constants.DB);
        } else if (user12.getCloudIntCount() < user12.getDbIntCount()) {
            usuarioPresenter.updateUsuario(user12, Constants.CLOUD);
        }
    }

    @Override
    public void userDeleted(Usuario usuario) {

        if (user12.getCloudIntCount() > user12.getDbIntCount()) {
            usuarioPresenter.deleteUsuario(user12, Constants.DB);
        } else if (user12.getCloudIntCount() < user12.getDbIntCount()) {
            usuarioPresenter.deleteUsuario(user12, Constants.CLOUD);
        }
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

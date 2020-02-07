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
import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.presentation.presenter.PetPresenter;
import pe.com.patadeperro.presentation.utils.Constants;
import pe.com.patadeperro.presentation.utils.HelloIntentService;
import pe.com.patadeperro.presentation.utils.Netwrk;
import pe.com.patadeperro.presentation.view.PetView;

import static pe.com.patadeperro.presentation.ui.activities.a00MainActivity.EXTRA_MESSAGE;

/**
 * Clase ** a12UserUpdDelActivity ** Pet, detalles **
 */
public class a22PetUpdDelActivity
        extends BaseActivity
        implements PetView
{

    /*****************************************
    Variables, definición de objetos
         */
    PetPresenter petPresenter;
    Pet pet22;
    // int position12;

    //<editor-fold desc="@BindView de Butter Knife">
    // @BindView(R.id.tv22id) EditText tv_id;

    @BindView(R.id.et22id) EditText et_id;
    @BindView(R.id.et22idCloud) EditText et_idCloud;
    @BindView(R.id.et22idUser) EditText et_idUser;
    @BindView(R.id.et22name) EditText et_name;
    @BindView(R.id.et22race) EditText et_race;
    @BindView(R.id.et22gender) EditText et_gender;
    @BindView(R.id.et22age) EditText et_age;
    @BindView(R.id.et22color) EditText et_color;
    @BindView(R.id.et22qrCode) EditText et_qrCode;
    //</editor-fold>

    void initUI()
    {
        petPresenter= new PetPresenter();
        petPresenter.addView(this);

//        et22name = findViewById(R.id.et22name);

    }

    /*********************************************************************************** 
    método onPause
    */
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Probando HelloService
        Intent intentSrv = new Intent(this, HelloIntentService.class);
        startService(intentSrv);

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
        setContentView(R.layout.a22_pet_upd_del_activity);

        ButterKnife.bind(this);
        initUI();

        /**
         * // Get the Intent that started this activity and extract the string
        // ... antes obtenía así: Pet = (Pet) listaPet.get(position);
 */

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("objetoPet");
        pet22 = (Pet) bundle.getSerializable("objetoPet");

        String message = intent.getStringExtra( EXTRA_MESSAGE );
        String s_position = message;   // posición del arreglo

//        // Conecta los campos de pantalla
//
//        // TextView et22id = findViewById(R.id.et22id;
//        // TextView et22idCloud = findViewById(R.id.et22idCloud);
//        TextView et22id = findViewById(R.id.et22id);
//        TextView et22idCloud = findViewById(R.id.et22idCloud);
//        TextView et22idUser = findViewById(R.id.et22idUser);
//        TextView et22name = findViewById(R.id.et22name);
//        TextView et22race = findViewById(R.id.et22race);
//        TextView et22gender = findViewById(R.id.et22gender);
//        TextView et22age = findViewById(R.id.et22age);
//        TextView et22color = findViewById(R.id.et22color);
//        TextView et22qrCode = findViewById(R.id.et22qrCode);

        TextView tv_position = findViewById(R.id.tv22position);

        // carga los campos de la pantalla

        et_id.setText(pet22.getId().toString());
        et_idCloud.setText(pet22.getIdCloud());
        et_idUser.setText(pet22.getIdUser());
        et_name.setText(pet22.getName());
        et_race.setText(pet22.getRace());
        et_gender.setText(pet22.getGender());
        et_age.setText(pet22.getAge());
        et_color.setText(pet22.getColor());
        et_qrCode.setText(pet22.getQrCode());

        tv_position.setText(s_position);   // posición en el arreglo

    }   // onCreate

    /**
     * Called when the pet taps the OK btn
     */
    public void UpdateAndFinish(View view) {

        // actualiza arreglo correspondiente
        // antes, conexión a los campos en pantalla

        // Conecta los campos de pantalla

        // TextView et22id = findViewById(R.id.et22id;
        // TextView et22idCloud = findViewById(R.id.et22idCloud);

//        TextView et22id = findViewById(R.id.et22id);
//        TextView et22idCloud = findViewById(R.id.et22idCloud);
//        TextView et22idUser = findViewById(R.id.et22idUser);
//        TextView et22name = findViewById(R.id.et22name);
//        TextView et22race = findViewById(R.id.et22race);
//        TextView et22gender = findViewById(R.id.et22gender);
//        TextView et22age = findViewById(R.id.et22age);
//        TextView et22color = findViewById(R.id.et22color);
//        TextView et22qrCode = findViewById(R.id.et22qrCode);

        TextView tv_position = findViewById(R.id.tv22position);

        // actualiza el item (objeto) de la lista

//        pet22.setId(et22id.getText().toString());
        pet22.setId(Integer.parseInt(et_id.getText().toString()));
        pet22.setIdCloud(et_idCloud.getText().toString());
        pet22.setIdUser(et_idUser.getText().toString());
        pet22.setName(et_name.getText().toString());
        pet22.setRace(et_race.getText().toString());
        pet22.setGender(et_gender.getText().toString());
        pet22.setAge(et_age.getText().toString());
        pet22.setColor(et_color.getText().toString());
        pet22.setQrCode(et_qrCode.getText().toString());

        String message = tv_position.getText().toString();;

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Term.22 pos.: " + tv_position.getText().toString()
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast


         petPresenter.updatePet(pet22, Constants.CLOUD);  // <-- probar


/**
 * prueba Intent con return... no funcionó finishActivity ¿?

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",pet22);
        setResult(RESULT_OK,returnIntent);

        // setResult(RESULT_CANCELED, returnIntent);    <-- si falla

        finishActivity(1);
*/

        // new intent
        Intent intent = new Intent(this, a20PetAddListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "OK pet " + message );
        startActivity(intent);

    } // clic en botón

    /**
     * Called when the user taps the DEL btn
     */
    public void DeleteAndFinish(View view) {

        // Conecta los campos de pantalla <-- ya no si tenemos Butter Knife
//        TextView et22name = findViewById(R.id.et22name);
//        TextView et22phoneNumber = findViewById(R.id.et22phoneNumber);
//        TextView et22email = findViewById(R.id.et22email);
//        TextView et22lat = findViewById(R.id.et22lat);
//        TextView et22lng = findViewById(R.id.et22lng);
//        TextView et22logged = findViewById(R.id.et22logged);
//        TextView et22active = findViewById(R.id.et22active);
//        TextView et22created_at = findViewById(R.id.et22created_at);
//        TextView et22notifications = findViewById(R.id.et22notifications);

        TextView tv_position = findViewById(R.id.tv22position);
//        TextView et_id = findViewById(R.id.tv22id);

        // actualiza el item (objeto) de la lista
        pet22.setId(Integer.parseInt(et_id.getText().toString()));
        pet22.setIdCloud(et_idCloud.getText().toString());
        pet22.setIdUser(et_idUser.getText().toString());
        pet22.setName(et_name.getText().toString());
        pet22.setRace(et_race.getText().toString());
        pet22.setGender(et_gender.getText().toString());
        pet22.setAge(et_age.getText().toString());
        pet22.setColor(et_color.getText().toString());
        pet22.setQrCode(et_qrCode.getText().toString());

        String message = tv_position.getText().toString();;

        CharSequence text;

        Netwrk netwrk = new Netwrk();
        if (netwrk.isConnected(getContext())) {
            text = "RED, Delete12 pos.: " + tv_position.getText().toString();

            petPresenter.deletePet(pet22, Constants.CLOUD);  // <-- ejecuta DELETE

        } else  {
            text = "RED, no hay... No actualizará.";
        }


        // toast

        Context context = getApplicationContext();
//        CharSequence text = <-- cargado arriba
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast


//        usuarioPresenter.deleteUsuario(pet22, Constants.CLOUD);  // <-- test


        // new intent
        Intent intent = new Intent(this, a20PetAddListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "DEL pet " + message );
        startActivity(intent);

    } // clic en botón

    /*************************************************************************************-
    método pet22Created
    */
    @Override
    public void petCreated(Pet pet) {
        pet22 = pet;
        petPresenter.updatePet(pet22, Constants.CLOUD);

    }

    /*************************************************************************************-
    * método petUpdated
    */
    @Override
    public void petUpdated(Pet pet22) {

        if (pet22.getId() == 0) {
            petPresenter.createPet(pet22, Constants.DB);
        } else if (pet22.getCloudIntCount() > pet22.getDbIntCount()) {
            petPresenter.updatePet(pet22, Constants.DB);
        } else if (pet22.getCloudIntCount() < pet22.getDbIntCount()) {
            petPresenter.updatePet(pet22, Constants.CLOUD);
        }
    }

    @Override
    public void petDeleted(Pet pet) {

        if (pet22.getCloudIntCount() > pet22.getDbIntCount()) {
            petPresenter.deletePet(pet22, Constants.DB);
        } else if (pet22.getCloudIntCount() < pet22.getDbIntCount()) {
            petPresenter.deletePet(pet22, Constants.CLOUD);
        }
    }

    /*************************************************************************************-
    método petListLoaded
    */
    @Override
    public void petsListLoaded(ArrayList<Pet> pet22s) {

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

package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.com.patadeperro.R;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.presentation.presenter.PetPresenter;
import pe.com.patadeperro.presentation.view.PetView;

import static pe.com.patadeperro.presentation.ui.activities.Prueba00Activity.EXTRA_MESSAGE;

/**
 * Clase ** Prueba12Activity ** Pet, detalles **************************
 */
public class Prueba22Activity
        extends BaseActivity
        implements PetView
{

    /*****************************************
    Variables, definición de objetos
         */
    PetPresenter petPresenter;
    Pet pet22;
    // int position12;

    void initUI()
    {
        petPresenter= new PetPresenter();
        petPresenter.addView(this);

//        et32name = findViewById(R.id.et32name);

    }



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
        setContentView(R.layout.prueba22_activity);

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

        // Conecta los campos de pantalla

        // TextView et32id = findViewById(R.id.et32id;
        // TextView et32idCloud = findViewById(R.id.et32idCloud);
        TextView et22id = findViewById(R.id.et22id);
        TextView et22idCloud = findViewById(R.id.et22idCloud);
        TextView et22idUser = findViewById(R.id.et22idUser);
        TextView et22name = findViewById(R.id.et22name);
        TextView et22race = findViewById(R.id.et22race);
        TextView et22gender = findViewById(R.id.et22gender);
        TextView et22age = findViewById(R.id.et22age);
        TextView et22color = findViewById(R.id.et22color);
        TextView et22qrCode = findViewById(R.id.et22qrCode);

        TextView tv22position = findViewById(R.id.tv22position);

        // carga los campos de la pantalla

        //et32id.setText(pet12.getId());
        //et32idCloud.setText(pet12.getIdCloud());
        et22id.setText(pet22.getId());
        et22idCloud.setText(pet22.getIdCloud());
        et22idUser.setText(pet22.getIdUser());
        et22name.setText(pet22.getName());
        et22race.setText(pet22.getRace());
        et22gender.setText(pet22.getGender());
        et22age.setText(pet22.getAge());
        et22color.setText(pet22.getColor());
        et22qrCode.setText(pet22.getQrCode());

        tv22position.setText(s_position);   // posición en el arreglo

    }   // onCreate

    /**
     * Called when the pet taps the OK btn
     */
    public void UpdateAndFinish(View view) {

        // actualiza arreglo correspondiente
        // antes, conexión a los campos en pantalla

        // Conecta los campos de pantalla

        // TextView et32id = findViewById(R.id.et32id;
        // TextView et32idCloud = findViewById(R.id.et32idCloud);
        TextView et22id = findViewById(R.id.et22id);
        TextView et22idCloud = findViewById(R.id.et22idCloud);
        TextView et22idUser = findViewById(R.id.et22idUser);
        TextView et22name = findViewById(R.id.et22name);
        TextView et22race = findViewById(R.id.et22race);
        TextView et22gender = findViewById(R.id.et22gender);
        TextView et22age = findViewById(R.id.et22age);
        TextView et22color = findViewById(R.id.et22color);
        TextView et22qrCode = findViewById(R.id.et22qrCode);

        TextView tv22position = findViewById(R.id.tv22position);

        // actualiza el item (objeto) de la lista

        pet22.setId(et22id.getText().toString());
        pet22.setIdCloud(et22idCloud.getText().toString());
        pet22.setIdUser(et22idUser.getText().toString());
        pet22.setName(et22name.getText().toString());
        pet22.setRace(et22race.getText().toString());
        pet22.setGender(et22gender.getText().toString());
        pet22.setAge(et22age.getText().toString());
        pet22.setColor(et22color.getText().toString());
        pet22.setQrCode(et22qrCode.getText().toString());

        String message = tv22position.getText().toString();;

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Term.22 pos.: " + tv22position.getText().toString()
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast


         petPresenter.updatePet(pet22);  // <-- probar


/**
 * prueba Intent con return... no funcionó finishActivity ¿?

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",pet22);
        setResult(RESULT_OK,returnIntent);

        // setResult(RESULT_CANCELED, returnIntent);    <-- si falla

        finishActivity(1);
*/

        // new intent
        Intent intent = new Intent(this, Prueba20Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "OK pet " + message );
        startActivity(intent);

    } // clic en botón

    /*************************************************************************************-
    método pet22Created
    */
    @Override
    public void petCreated(Pet pet22) {

    }

    /*************************************************************************************-
    método petUpdated
    */
    @Override
    public void petUpdated(Pet pet22) {



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

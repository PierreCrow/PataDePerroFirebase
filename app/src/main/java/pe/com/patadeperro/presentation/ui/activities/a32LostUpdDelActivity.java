package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.com.patadeperro.R;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.presentation.presenter.LostPresenter;
import pe.com.patadeperro.presentation.view.LostView;

import static pe.com.patadeperro.presentation.ui.activities.a00MainActivity.EXTRA_MESSAGE;
import static pe.com.patadeperro.presentation.ui.activities.a30LostAddListActivity.lost;

/**
 * Clase ** a10UserAddListActivity ****************************
 */
public class a32LostUpdDelActivity
        extends BaseActivity
        implements LostView
{

    /*****************************************
    Variables, definición de objetos
         */
    LostPresenter lostPresenter;

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
        setContentView(R.layout.a32_los_upd_del_activity);

        // Get the Intent that started this activity and extract the string
        // ... antes obtenía así: lost = (Lost) listaLost.get(position);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("objetoLost");
        Lost lost = (Lost) bundle.getSerializable("objetoLost");

        String message = intent.getStringExtra( EXTRA_MESSAGE );
        int position = Integer.parseInt(message);   // posición del arreglo

        // Conecta los campos de pantalla

        // TextView et32id = findViewById(R.id.et32id;
        // TextView et32idCloud = findViewById(R.id.et32idCloud);
        TextView et32petName = findViewById(R.id.et32petName);
        TextView et32race = findViewById(R.id.et32race);
        TextView et32gender = findViewById(R.id.et32gender);
        TextView et32color = findViewById(R.id.et32color);
        TextView et32age = findViewById(R.id.et32age);
        TextView et32contactPhoneNumber = findViewById(R.id.et32contactPhoneNumber);
        TextView et32contactName = findViewById(R.id.et32contactName);
        TextView et32description = findViewById(R.id.et32description);
        TextView et32reward = findViewById(R.id.et32reward);
        TextView et32rewardAmount = findViewById(R.id.et32rewardAmount);
        TextView et32country = findViewById(R.id.et32country);
        TextView et32state = findViewById(R.id.et32state);
        TextView et32city = findViewById(R.id.et32city);
        TextView et32urlImage = findViewById(R.id.et32urlImage);
        TextView et32lat = findViewById(R.id.et32lat);
        TextView et32lng = findViewById(R.id.et32lng);
        TextView et32lostAddress = findViewById(R.id.et32lostAddress);
        TextView et32found = findViewById(R.id.et32found);

        TextView tv32position = findViewById(R.id.tv32position);

        // carga los campos de la pantalla

        //et32id.setText(lost.getId());
        //et32idCloud.setText(lost.getIdCloud());
        et32petName.setText(lost.getPetName());
        et32race.setText(lost.getRace());
        et32gender.setText(lost.getGender());
        et32color.setText(lost.getColor());
        et32age.setText(lost.getAge());
        et32contactPhoneNumber.setText(lost.getContactPhoneNumber());
        et32contactName.setText(lost.getContactName());
        et32description.setText(lost.getDescription());
        et32reward.setText(lost.getReward());
        et32rewardAmount.setText(lost.getRewardAmount());
        et32country.setText(lost.getCountry());
        et32state.setText(lost.getState());
        et32city.setText(lost.getCity());
        et32urlImage.setText(lost.getUrlImage());
        et32lat.setText(lost.getLat());
        et32lng.setText(lost.getLng());
        et32lostAddress.setText(lost.getLostAddress());
        et32found.setText(lost.getFound());

        tv32position.setText(message);

    }

    /**
     * Called when the user taps the OK btn
     */
    public void UpdateAndFinish(View view) {

        // actualiza arreglo correspondiente
        // antes, conexión a los campos en pantalla

        TextView et32petName = findViewById(R.id.et32petName);
        TextView et32race = findViewById(R.id.et32race);
        TextView et32gender = findViewById(R.id.et32gender);
        TextView et32color = findViewById(R.id.et32color);
        TextView et32age = findViewById(R.id.et32age);
        TextView et32contactPhoneNumber = findViewById(R.id.et32contactPhoneNumber);
        TextView et32contactName = findViewById(R.id.et32contactName);
        TextView et32description = findViewById(R.id.et32description);
        TextView et32reward = findViewById(R.id.et32reward);
        TextView et32rewardAmount = findViewById(R.id.et32rewardAmount);
        TextView et32country = findViewById(R.id.et32country);
        TextView et32state = findViewById(R.id.et32state);
        TextView et32city = findViewById(R.id.et32city);
        TextView et32urlImage = findViewById(R.id.et32urlImage);
        TextView et32lat = findViewById(R.id.et32lat);
        TextView et32lng = findViewById(R.id.et32lng);
        TextView et32lostAddress = findViewById(R.id.et32lostAddress);
        TextView et32found = findViewById(R.id.et32found);

        TextView tv32position = findViewById(R.id.tv32position);

        // actualiza el item (objeto) de la lista

        lost.setPetName(et32petName.getText().toString());
        lost.setRace(et32race.getText().toString());
        lost.setGender(et32gender.getText().toString());
        lost.setColor(et32color.getText().toString());
        lost.setAge(et32age.getText().toString());
        lost.setContactPhoneNumber(et32contactPhoneNumber.getText().toString());
        lost.setContactName(et32contactName.getText().toString());
        lost.setDescription(et32description.getText().toString());
        lost.setReward(et32reward.getText().toString());
        lost.setRewardAmount(et32rewardAmount.getText().toString());
        lost.setCountry(et32country.getText().toString());
        lost.setState(et32state.getText().toString());
        lost.setCity(et32city.getText().toString());
        lost.setUrlImage(et32urlImage.getText().toString());
        lost.setLat(et32lat.getText().toString());
        lost.setLng(et32lng.getText().toString());
        lost.setLostAddress(et32lostAddress.getText().toString());
        lost.setFound(et32found.getText().toString());

        String message = tv32position.getText().toString();;

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Term.32 pos.: " + tv32position.getText().toString()
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast


        // lostPresenter.updateLost(lost);  // <-- todavía no probar


/**
 * prueba Intent con return... no funcinó finishActivity ¿?

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",lost);
        setResult(RESULT_OK,returnIntent);

        // setResult(RESULT_CANCELED, returnIntent);    <-- si falla

        finishActivity(1);
*/

        // new intent
        Intent intent = new Intent(this, a30LostAddListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "OK lost " + message );
        startActivity(intent);

    } // retornar

    /*************************************************************************************-
    método lostCreated
    */
    @Override
    public void lostCreated(Lost lost) {

    }

    /*************************************************************************************-
    método lostUpdated
    */
    @Override
    public void lostUpdated(Lost lost) {



    }

    /*************************************************************************************-
    método lostListLoaded
    */
    @Override
    public void lostsListLoaded(ArrayList<Lost> losts) {

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

package pe.com.patadeperro.presentation.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.com.patadeperro.R;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.presentation.presenter.LostPresenter;
import pe.com.patadeperro.presentation.utils.Constants;
import pe.com.patadeperro.presentation.utils.ImageUtil;
import pe.com.patadeperro.presentation.view.LostView;

import static pe.com.patadeperro.presentation.ui.activities.MapLocActivity.MY_PERMISSIONS_REQUEST_CAMERA;
import static pe.com.patadeperro.presentation.ui.activities.MapLocActivity.REQUEST_IMAGE_CAPTURE;
import static pe.com.patadeperro.presentation.ui.activities.a00MainActivity.EXTRA_MESSAGE;
import static pe.com.patadeperro.presentation.ui.activities.a30LostAddListActivity.lost;

public class a32LostUpdDelActivity
        extends BaseActivity
        implements LostView {

    LostPresenter lostPresenter;
    Lost lost = new Lost();

    ImageView iv32petImage;
    TextView tv32petImage;

    ;

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a32_los_upd_del_activity);

        lostPresenter = new LostPresenter();
        lostPresenter.addView(this);


        // Get the Intent that started this activity and extract the string
        // ... antes obtenía así: lost = (Lost) listaLost.get(position);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("objetoLost");
        this.lost = (Lost) bundle.getSerializable("objetoLost");

        String message = intent.getStringExtra(EXTRA_MESSAGE);
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

        // para la foto
        tv32petImage = findViewById(R.id.tv32petImage);
        iv32petImage = findViewById(R.id.iv32petImage);


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

        tv32petImage.setText( lost.getDescription() );
        iv32petImage.setImageBitmap( ImageUtil.convert(lost.getDescription()) );
        // 2020-02-21 ecv: Por ahora, guarda aquí la foto en B64


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

//        String message = tv32position.getText().toString();

        lost.setDescription(tv32petImage.getText().toString());
        // 2020-02-21 ecv: Por ahora, guarda aquí la foto en B64

        ;

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Term.32 pos.: " + tv32position.getText().toString();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();       // toast


        lostPresenter.updateLost(lost, Constants.CLOUD);  // <-- probando


/**
 * prueba Intent con return... no funcionó finishActivity ¿?

 Intent returnIntent = new Intent();
 returnIntent.putExtra("result",lost);
 setResult(RESULT_OK,returnIntent);

 // setResult(RESULT_CANCELED, returnIntent);    <-- si falla

 finishActivity(1);
 */


    } // retornar

    /**
     * Called when the user taps the DEL btn
     */
    public void DeleteAndFinish(View view) {

        // Conecta los campos de pantalla <-- ya no si tenemos Butter Knife
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

//        TextView et_id = findViewById(R.id.tv22id);

        // actualiza el item (objeto) de la lista
//        lost.setId(Integer.parseInt(et32id.getText().toString()));
//        lost.setIdCloud(et32idCloud.getText().toString());

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

        String message = tv32position.getText().toString();
        ;


        lostPresenter.deleteLost(lost, Constants.CLOUD);  // <-- test


    } // clic en botón

    @Override
    public void lostCreated(Lost lost) {

    }

    @Override
    public void lostCreatedList(List<Lost> lostList) {

    }

    @Override
    public void lostUpdated(Lost lost) {

        Log.e("a32", lost.toString());

        // new intent
        Intent intent = new Intent(this, a30LostAddListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "OK lost UPDATE");
        startActivity(intent);

    }

    @Override
    public void lostDeleted(Lost lost) {

        // new intent
        Intent intent = new Intent(this, a30LostAddListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(
                EXTRA_MESSAGE,
                "DEL pet OK");
        startActivity(intent);

    }

    @Override
    public void lostsListLoaded(ArrayList<Lost> losts) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    public void onImgClicked(View v) {

        CharSequence text = "Clic imagen... ";   // validar primero
        Context thisActivity = this;

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            text = text + "cámara no disponible... ";
        }

        if (ContextCompat.checkSelfPermission(
                thisActivity, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            text = text + "cámara sin permiso";


            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

//                WaitAMomentPls(0, "Necesitamos la cámara por favor");
                // toast solicita camera
                Context context = getApplicationContext();
                CharSequence charSequence = "Click Imagen, necesitamos la cámara por favor";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, charSequence, duration);
                toast.show();


            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }


        }

        if (text != "Clic imagen... ") {
            // toast no camera
            Context context = getApplicationContext();
//            CharSequence text = "Click Imagen, cámara no disponible";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }

        // todo_ ok, acción

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    } // on clic

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            iv32petImage.setImageBitmap(imageBitmap);
            tv32petImage.setText( ImageUtil.convert(imageBitmap) );
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // CAMERA-related task you need to do.

                    Context context = getApplicationContext();
                    CharSequence text = "Listo! Vuelve a intentar la foto";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // otros casos

        } // fin casos

    } // fin permisos

} // fin clase

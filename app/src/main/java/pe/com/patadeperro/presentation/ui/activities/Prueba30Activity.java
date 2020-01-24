package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.com.patadeperro.R;

import java.util.ArrayList;
import java.util.Arrays;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.presentation.presenter.LostPresenter;
import pe.com.patadeperro.presentation.view.LostView;

import static pe.com.patadeperro.presentation.ui.activities.Prueba00Activity.EXTRA_MESSAGE;

/**
 * Clase Prueba10Activity *****************************************************************
 */
public class Prueba30Activity
        extends BaseActivity
        implements LostView,
        ListAdapterLost.OnItemClickListener {

    //** Variables, definición de objetos **********************************
    EditText etNameLostPet;
    EditText etAddressLostPet;

    Button btnLostPet;
    String nombre, address, idCloudGen;

    LostPresenter lostPresenter;
    RecyclerView rvlistadoLost;

    public static Lost lost = new
            Lost(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    );
    // sin datos

    /**
     *  solamente 1 por ahora ...
     */
    static Lost lost1 = new
            Lost("",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "");

    /**
     * otros, 2, 3, ya no
    static Lost lost2 = new
            Lost( "" , "",
            "Milo", "labrador",
            "male", "", "brown", "");

    static Lost lost3 = new
            Lost( "" , "",
            "Jack", "terrier",
            "male", "", "b&w", "");
    */

    public static ArrayList listaLost = new ArrayList<>(
            //... (Arrays.asList(lost1, lost2, lost3)); <-- sin datos ahora
            Arrays.asList(lost1)    // bueno, 1 necesitamos ¿?
    );

    private ListAdapterLost adapterLost;
    private ListAdapterLost.OnItemClickListener mlistenerLost;
    private View v;

    // <-- definición de objetos y var. // definición de objetos y var.

    /**
     * método onPause **
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /** ************************************************************************************-
    método onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba30_activity);

        etNameLostPet = (EditText) findViewById(R.id.etNameLostPet);
        etAddressLostPet = (EditText) findViewById(R.id.etAddressLostPet);

        btnLostPet = (Button) findViewById(R.id.btnLostPet);

        lostPresenter = new LostPresenter();
        lostPresenter.addView(this);

        // lista
        mlistenerLost = this;

        rvlistadoLost = findViewById(R.id.rvListadoLost);
        rvlistadoLost.setLayoutManager(
                new LinearLayoutManager(
                        getApplicationContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        lostPresenter.loadLosts();        // cuando se ejecuta, carga adapterLost new
        /*
        adapterLost = new ListAdapterLost(
                mlistenerLost,
                getApplicationContext(),
                listaLost);
        */

        rvlistadoLost.setAdapter(adapterLost);

        btnLostPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = etNameLostPet.getText().toString();
                address = etAddressLostPet.getText().toString();

                idCloudGen = String.valueOf(listaLost.size());


                Lost lost = new
                        Lost(idCloudGen,
                        nombre,
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        address,
                        "");

                lostPresenter.createLost(lost);

                // toast
                Context context = getApplicationContext();
                CharSequence text =
                        "Click Botón crear perdida";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                adapterLost.notifyDataSetChanged();

            }
        });

    }

    /** ************************************************************************************-
    método lostCreated
    */
    @Override
    public void lostCreated(Lost lost) {

    }

    /** ************************************************************************************-
    método lostUpdated
    */
    @Override
    public void lostUpdated(Lost lost) {

        this.lost = lost;   // chk

    }

    /** ************************************************************************************-
    método lostsListLoaded
    */
    @Override
    public void lostsListLoaded(ArrayList<Lost> losts) {

        listaLost = losts;
        // adapterLost.notifyDataSetChanged();

        adapterLost = new ListAdapterLost(
                mlistenerLost,
                getApplicationContext(),
                listaLost);
        rvlistadoLost.setAdapter(adapterLost);

    }

    /** ************************************************************************************-
    método showErrorMessage
    */
    @Override
    public void showErrorMessage(String message) {

    }

    /** ************************************************************************************-
    método showErrorMessage
    */
    @Override
    public Context getContext() {
        return this;
    }

    /** ************************************************************************************-
    método onItemClicked
    */
    @Override
    public void onItemClicked(View v, Lost lost) {

        this.v = v;

        // toast
        TextView tv_position = v.findViewById(R.id.tv_position);

        Context context = getApplicationContext();
        CharSequence text =
                "Click pos.: " + tv_position.getText().toString();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

                // toast

        // intent

        // Empaqueta objeto "lost" recibido...
        Bundle bundle = new Bundle();
        bundle.putSerializable("objetoLost", lost);

        // Y envía el paquete a siguiente pantlla...
        Intent intent = new Intent(this, Prueba32Activity.class);
        intent.putExtra("objetoLost", bundle);

        String message = tv_position.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);      // intent

    }

/**
 * método onActivityResult,,, no usado por ahora
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                //String result=data.getStringExtra("result");

                lostPresenter.updateLost(lost);

                // toast
                Context context = getApplicationContext();
                CharSequence text =
                        "Actualizando "
                        ;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                adapterLost.notifyDataSetChanged();

            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

 */

}

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
import java.util.List;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.presentation.presenter.LostPresenter;
import pe.com.patadeperro.presentation.utils.Constants;
import pe.com.patadeperro.presentation.view.LostView;

import static pe.com.patadeperro.presentation.ui.activities.a00MainActivity.EXTRA_MESSAGE;
import static pe.com.patadeperro.presentation.ui.activities.a38LostSplashActivity.flagLostsListLoaded;

/**
 * Clase a10UserAddListActivity ************************************************
 */
public class a30LostAddListActivity
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

    public static Lost lost = new Lost();
    // sin datos

    /**
     *  solamente 1 por ahora ...
     */
    static Lost lost1 = new Lost();

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

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a30_lost_add_list_activity);

        Intent intent = getIntent();
        Bundle intentBundleExtra = intent.getBundleExtra("listaLost");
        if (intentBundleExtra!= null) {
            this.listaLost = (ArrayList<Lost>) intentBundleExtra.getSerializable("listaLost");
        }   // Recibe la lista de Lost

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

        if (intentBundleExtra!=null) {
            adapterLost = new ListAdapterLost(
                    mlistenerLost,
                    getApplicationContext(),
                    listaLost);

            rvlistadoLost.setAdapter(adapterLost);
        } else {

            flagLostsListLoaded=false;
            lostPresenter.loadLosts(Constants.CLOUD);        // cuando se ejecuta, carga adapterLost new

        }

        /**
         * botón para crear ítem
         */

        btnLostPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = etNameLostPet.getText().toString();
                address = etAddressLostPet.getText().toString();

//                idCloudGen = String.valueOf(listaLost.size());

                Lost lost = new Lost();
                lost.setPetName(nombre);
                lost.setLostAddress(address);

                lostPresenter.createLost(lost, Constants.CLOUD);

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

    @Override
    public void lostCreated(Lost lost) {

    }

    @Override
    public void lostCreatedList(List<Lost> lostList) {

    }

    @Override
    public void lostUpdated(Lost lost) {

        etNameLostPet.setText("");
        etAddressLostPet.setText("");

        etNameLostPet.requestFocus();

        listaLost.add(lost);

        //        this.lostsListLoaded(listaLost);

        adapterLost = new ListAdapterLost(
                mlistenerLost,
                getApplicationContext(),
                listaLost);

        rvlistadoLost.setAdapter(adapterLost);

    }

    @Override
    public void lostDeleted(Lost lost) {

    }

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

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }

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
        Intent intent = new Intent(this, a32LostUpdDelActivity.class);
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

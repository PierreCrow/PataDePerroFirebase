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

import pe.com.patadeperro.domain.model.Abuse;
import pe.com.patadeperro.presentation.presenter.AbusePresenter;
import pe.com.patadeperro.presentation.view.AbuseView;

import static pe.com.patadeperro.presentation.ui.activities.a00MainActivity.EXTRA_MESSAGE;

/**
 * Clase ** a40AbuseAddListActivity *****************************************************************
 */
public class a40AbuseAddListActivity
        extends BaseActivity
        implements AbuseView,
        ListAdapterAbuse.OnItemClickListener {

    /** --------- ---------- ------- --------
    Variables, definición de objetos
         */
    EditText etNameAbusePet;
    EditText etAbuseContactPhone;
    EditText etAbuseContactName;

    Button btnAbusePet;
    String petName, contactPhone, contactName;
    String idCloudGen;

    AbusePresenter abusePresenter;
    RecyclerView rvlistadoAbuse;

    public static Abuse abuse = new
        Abuse(
             "",
             "",
             ""    );
    // sin datos

    /**
     * solamente 1 por ahora
         */
    static Abuse abuse1 = new
            Abuse( "",
            "",
            "");

    /**
     * omitiendo 2, 3, ...
    static Abuse abuse2 = new
            Abuse( "" , "",
            "Milo", "labrador",
            "male", "", "brown", "");

    static Abuse abuse3 = new
            Abuse( "" , "",
            "Jack", "terrier",
            "male", "", "b&w", "");
    */

    public static ArrayList listaAbuse = new ArrayList<>(
            //... (Arrays.asList(abuse1, abuse2, abuse3)); <-- sin datos ahora
            Arrays.asList(abuse1)    // bueno, 1 necesitamos ¿?
    );

    private ListAdapterAbuse adapterAbuse;
    private ListAdapterAbuse.OnItemClickListener mlistenerAbuse;
    private View v;

    /** -------------------------------------------------------------------------------------
    método onPause
    */
    @Override
    public void onPause() {
        super.onPause();
    }

    /** -------------------------------------------------------------------------------------
    método onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a40_abuse_add_list_activity);

        etNameAbusePet =(EditText)findViewById(R.id.etNameAbusePet);
        etAbuseContactPhone=(EditText)findViewById(R.id.etAbuseContactPhone);
        etAbuseContactName=(EditText)findViewById(R.id.etAbuseContactName);

        btnAbusePet=(Button) findViewById(R.id.btnLostPet);

        abusePresenter= new AbusePresenter();
        abusePresenter.addView(this);

        // lista
        mlistenerAbuse = this;

        rvlistadoAbuse = findViewById(R.id.rvListadoAbuse);
        rvlistadoAbuse.setLayoutManager(
                new LinearLayoutManager(
                        getApplicationContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        abusePresenter.loadAbuses();        // cuando se ejecuta, carga adapterAbuse new
        /*
        adapterAbuse = new ListAdapterAbuse(
                mlistenerAbuse,
                getApplicationContext(),
                listaAbuse);
        */

        rvlistadoAbuse.setAdapter(adapterAbuse);

        btnAbusePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                petName = etNameAbusePet.getText().toString();
                contactPhone = etAbuseContactPhone.getText().toString();
                contactName = etAbuseContactName.getText().toString();

                idCloudGen = String.valueOf(listaAbuse.size());


                Abuse abuse= new
                        Abuse( petName,
                        contactPhone,
                        contactName);

                abusePresenter.createAbuse(abuse);
                
                // toast
                Context context = getApplicationContext();
                CharSequence text =
                        "Click Botón crear perdida"
                        ;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                adapterAbuse.notifyDataSetChanged();

            }
        });

    }

    /** -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void abuseCreated(Abuse abuse) {

    }

    /** -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void abuseUpdated(Abuse abuse) {

        this.abuse = abuse;   // chk

    }

    /** -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void abusesListLoaded(ArrayList<Abuse> abuses) {

        listaAbuse = abuses;
        // adapterAbuse.notifyDataSetChanged();

        adapterAbuse = new ListAdapterAbuse(
                mlistenerAbuse,
                getApplicationContext(),
                listaAbuse);
        rvlistadoAbuse.setAdapter(adapterAbuse);

    }

    /** -------------------------------------------------------------------------------------
        método
        */
    @Override
    public void showErrorMessage(String message) {

    }

    /** -------------------------------------------------------------------------------------
    método
    */
    @Override
    public Context getContext() {
        return this;
    }

    /** -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void onItemClicked(View v, Abuse abuse) {

        // this.v = v;
        TextView tv_position = v.findViewById(R.id.tv_position);

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Click pos.: " + tv_position.getText().toString()
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        /**
         * intent
         */

        //** Empaqueta objeto "user" ...
        Bundle bundle = new Bundle();
        bundle.putSerializable("objetoAbuse", abuse);

        //** Y envía el paquete a siguiente pantlla...
        Intent intent = new Intent(this, a42AbuseUpdDelActivity.class);
        intent.putExtra("objetoAbuse", bundle);

        String message = tv_position.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);      // <-- intent

    }

}

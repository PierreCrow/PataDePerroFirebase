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

import static pe.com.patadeperro.presentation.ui.activities.Prueba00Activity.EXTRA_MESSAGE;

/* Clase Prueba10Activity *****************************************************************
 */
public class Prueba40Activity
        extends BaseActivity
        implements AbuseView,
        ListAdapterAbuse.OnItemClickListener {

    /* --------- ---------- ------- --------
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

    /* ... solamente 1 por ahora
         */
    static Abuse abuse1 = new
            Abuse( "",
            "",
            "");

    /*
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

    /* -------------------------------------------------------------------------------------
    método onPause
    */
    @Override
    public void onPause() {
        super.onPause();
    }

    /* -------------------------------------------------------------------------------------
    método onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba40_activity);

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

    /* -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void abuseCreated(Abuse abuse) {

    }

    /* -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void abuseUpdated(Abuse abuse) {

        this.abuse = abuse;   // chk

    }

    /* -------------------------------------------------------------------------------------
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

    /* -------------------------------------------------------------------------------------
        método
        */
    @Override
    public void showErrorMessage(String message) {

    }

    /* -------------------------------------------------------------------------------------
    método
    */
    @Override
    public Context getContext() {
        return this;
    }

    /* -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void onItemClicked(View v) {

        this.v = v;
        // v.findViewById(R.id.tv_nombre)
        /*
        TextView tv_petName = v.findViewById(R.id.tv_nombre);
        TextView et_petName = findViewById(R.id.et_nombre);
        et_petName.setText(
                tv_petName.getText().toString()
                );

        TextView tv_dni_trabajador = v.findViewById(R.id.tv_dni);
        TextView et_dni_trabajador = findViewById(R.id.et_dni);
        et_dni_trabajador.setText(
                tv_dni_trabajador.getText().toString()
        */

        // toast

        TextView tv_position = v.findViewById(R.id.tv_position);

        Context context = getApplicationContext();
        CharSequence text =
                "Click pos.: " + tv_position.getText().toString()
                ;
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // intent
/* ** stop por ahora //2020-01-23 ecv

        Intent intent = new Intent(this, Prueba32Activity.class);
            String message = tv_position.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);

         // startActivity(intent);
        startActivityForResult(intent, 1);
*/
    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                //String result=data.getStringExtra("result");

                abusePresenter.updateAbuse(abuse);

                // toast
                Context context = getApplicationContext();
                CharSequence text =
                        "Actualizando "
                        ;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                adapterAbuse.notifyDataSetChanged();

            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

 */

}

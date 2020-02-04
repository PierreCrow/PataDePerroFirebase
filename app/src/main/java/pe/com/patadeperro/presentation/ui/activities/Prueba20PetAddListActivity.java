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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.presentation.presenter.PetPresenter;
import pe.com.patadeperro.presentation.view.PetView;

import static pe.com.patadeperro.presentation.ui.activities.Prueba00MainActivity.EXTRA_MESSAGE;

/**
 * Clase ** Prueba20PetAddListActivity ** Pet **************************************************************
 */
public class Prueba20PetAddListActivity
        extends BaseActivity
        implements PetView,
        ListAdapterPet.OnItemClickListener {

    /* --------- ---------- ------- --------
    Variables, definición de objetos
         */

    EditText etNameCreatePet;
    EditText etRaceCreatePet;

    Button btnCreatePet;
    String nombre, race;

    PetPresenter petPresenter;
    RecyclerView rvlistadoPet;

    public static Pet pet = new
            Pet( "" , "",
                     "Rambo", "bulldog",
                     "male", "", "white", "");
    // sin datos

    /*  carga algunos valores de prueba */
    static Pet pet1 = new
            Pet( "" , "",
            "Bella", "salchicha",
            "female", "", "black", "");

    static Pet pet2 = new
            Pet( "" , "",
            "Milo", "labrador",
            "male", "", "brown", "");

    static Pet pet3 = new
            Pet( "" , "",
            "Jack", "terrier",
            "male", "", "b&w", "");

    public static ArrayList listaPet = new ArrayList<>(
            Arrays.asList(pet1, pet2, pet3));
    // con datos de prueba por ahora

    private ListAdapterPet adapterPet;
    private ListAdapterPet.OnItemClickListener mlistenerPet;
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
        setContentView(R.layout.prueba20_pet_add_list_activity);

        etNameCreatePet =(EditText)findViewById(R.id.etNameCreatePet);
        etRaceCreatePet=(EditText)findViewById(R.id.etRaceCreatePet);

        btnCreatePet=(Button) findViewById(R.id.btnCreatePet);

        petPresenter= new PetPresenter();
        petPresenter.addView(this);

        // lista
        mlistenerPet = this;

        rvlistadoPet = findViewById(R.id.rvListadoPet);
        rvlistadoPet.setLayoutManager(
                new LinearLayoutManager(
                        getApplicationContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        petPresenter.loadPets();        // cuando se ejecuta, carga adapterPet new
        /*
        adapterPet = new ListAdapterPet(
                mlistenerPet,
                getApplicationContext(),
                listaPet);
        */

        rvlistadoPet.setAdapter(adapterPet);

        btnCreatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre= etNameCreatePet.getText().toString();
                race=etRaceCreatePet.getText().toString();

                Pet pet = new
                        Pet( "" , "",
                        nombre, race,
                        "", "", "", "");

                petPresenter.createPet(pet);
                
                // toast
                Context context = getApplicationContext();
                CharSequence text =
                        "Click Botón crear mascota"
                        ;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                adapterPet.notifyDataSetChanged();

            }
        });

    }

    /* -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void petCreated(Pet pet) {

    }

    /* -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void petUpdated(Pet pet) {

    }

    /* -------------------------------------------------------------------------------------
    método
    */
    @Override
    public void petsListLoaded(ArrayList<Pet> pets) {

        listaPet = pets;
        // adapterPet.notifyDataSetChanged();

        adapterPet = new ListAdapterPet(
                mlistenerPet,
                getApplicationContext(),
                listaPet);
        rvlistadoPet.setAdapter(adapterPet);

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

    /** -------------------------------------------------------------------------------------
    método onItemClicked
    */
    @Override
    public void onItemClicked(View v, Pet pet) {

        // this.v = v;
        TextView tv_position = v.findViewById(R.id.tv_position);

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Click pos.: " + tv_position.getText().toString();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        /**
         * intent
         */

        //** Empaqueta objeto "pet" ...
        Bundle bundle = new Bundle();
        bundle.putSerializable("objetoPet", (Serializable) pet);

        //** Y envía el paquete a siguiente pantlla...
        Intent intent = new Intent(this, Prueba22PetUpdDelActivity.class);
        intent.putExtra("objetoPet", bundle);

        String message = tv_position.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);      // <-- intent

    }

}

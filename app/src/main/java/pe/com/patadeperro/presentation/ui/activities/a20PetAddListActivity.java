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
import java.util.List;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.presentation.presenter.PetPresenter;
import pe.com.patadeperro.presentation.utils.Constants;
import pe.com.patadeperro.presentation.utils.HelloIntentService;
import pe.com.patadeperro.presentation.view.PetView;

import static pe.com.patadeperro.presentation.ui.activities.a00MainActivity.EXTRA_MESSAGE;

/**
 * Clase ** a20PetAddListActivity ** Pet **
 */
public class a20PetAddListActivity
        extends BaseActivity
        implements PetView,
        ListAdapterPet.OnItemClickListener {

    /**
     * PETS - lista, y opción nuevo reg.
     *
     * ECV = Enrique Contreras V.
     *
     * 2020-01-07 ecv: Desarrollado bajo la dirección de Pierre
     * 2020-02-10 ecv: Probando trabajar con base local - Db.
     *
     */
    /**
     * --------- ---------- ------- --------
     * Variables, definición de objetos
     */
    EditText etNameCreatePet;
    EditText etRaceCreatePet;

    Button btnCreatePet;
    String nombre, race;

    Boolean ctrlDb = false;
    Boolean ctrlCloud = false;

    PetPresenter petPresenter;
    RecyclerView rvlistadoPet;

    public static Pet pet = new
            Pet(0, "",
            "Juan", "Rambo",
            "bulldog", "", "7", "", "");
    // sin datos

    /*  carga algunos valores de prueba */
    static Pet pet1 = new
            Pet(0, "",
            "Juan", "Rambo",
            "bulldog", "", "7", "", "");

    static Pet pet2 = new
            Pet(0, "",
            "Juan", "Rambo",
            "bulldog", "", "7", "", "");

    static Pet pet3 = new
            Pet(0, "",
            "Juan", "Rambo",
            "bulldog", "", "7", "", "");

    public static ArrayList listaPet = new ArrayList<>(
            Arrays.asList(pet1, pet2, pet3));
    // con datos de prueba por ahora

    private ListAdapterPet adapterPet;
    private ListAdapterPet.OnItemClickListener mlistenerPet;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a20_pet_add_list_activity);

        etNameCreatePet = (EditText) findViewById(R.id.etNameCreatePet);
        etRaceCreatePet = (EditText) findViewById(R.id.etRaceCreatePet);

        btnCreatePet = (Button) findViewById(R.id.btnCreatePet);

        petPresenter = new PetPresenter();
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

//        petPresenter.loadPets(Constants.CLOUD);        // cuando se ejecuta, carga adapterPet new
        petPresenter.loadPets(Constants.DB);        // 2020-02-10 Prueba con DB

        /**
         * En el retorno de *.load...() creamos el adaptador
         * incluyendo la acción setAdapter

         adapterPet = new ListAdapterPet(
         mlistenerPet,
         getApplicationContext(),
         listaPet);


         rvlistadoPet.setAdapter(adapterPet);
         */

        /**
         * botón para crear reg.
         */
        btnCreatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = etNameCreatePet.getText().toString();
                race = etRaceCreatePet.getText().toString();
                //<editor-fold desc="new item code">
                Pet pet = new
                        Pet(0, "",
                        "", nombre,
                        race, "", "", "", "");
                //</editor-fold>

                ctrlCloud = false;
                ctrlDb = false;
//                petPresenter.createPet(pet, Constants.CLOUD);
                petPresenter.createPet(pet, Constants.DB);   // 2020-02-10 prueba con DB

//                adapterPet.notifyDataSetChanged();    // <-- no muestra los cambios??

                //<editor-fold desc="Toast show">
                Context context = getApplicationContext();
                CharSequence text =
                        "Click Botón crear mascota";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                //</editor-fold>

            }
        });

    }

    @Override
    public void petCreated(Pet pet) {

        if (pet.getCloudIntCount() > pet.getDbIntCount()) {
            ctrlDb = true;
            petPresenter.createPet(pet, Constants.DB);
        } else if (pet.getCloudIntCount() < pet.getDbIntCount()) {
            ctrlCloud = true;
            petPresenter.createPet(pet, Constants.CLOUD);
        } else if (!ctrlCloud) {
            ctrlCloud = true;
            petPresenter.updatePet(pet, Constants.CLOUD);
        } else if (!ctrlDb) {
            ctrlDb = true;
            petPresenter.updatePet(pet, Constants.DB);
        }

    }

    @Override
    public void petCreatedList(List<Pet> petList) {

    }

    @Override
    public void petUpdated(Pet pet) {

//        adapterPet.notifyDataSetChanged();    // <-- no muestra los cambios??

        etNameCreatePet.setText("");
        etRaceCreatePet.setText("");

        etNameCreatePet.requestFocus();

//        etNameCreatePet.setSelection(0, 0);   // nop?

        // adapterPet.notifyDataSetChanged();      // 2020-02-11 ecv
        // adapterPet.notifyItemInserted(listaPet.size()-1);

        listaPet.add(pet);

        //        this.petsListLoaded(listaPet);

        adapterPet = new ListAdapterPet(
                mlistenerPet,
                getApplicationContext(),
                listaPet);

        rvlistadoPet.setAdapter(adapterPet);

    }

    @Override
    public void petDeleted(Pet pet) {

    }

    @Override
    public void petsListLoaded(ArrayList<Pet> pets) {

        listaPet = pets;

//        if (adapterPet==null) {     // 2020-02-05 Crear si no existe solamente
        if (adapterPet == null || (ctrlCloud && ctrlDb)) { // Si hizo _todo lo esperado, refresca
            adapterPet = new ListAdapterPet(
                    mlistenerPet,
                    getApplicationContext(),
                    listaPet);

            rvlistadoPet.setAdapter(adapterPet);
        }

//             adapterPet.notifyDataSetChanged();


    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClicked(View v, Pet pet) {

        // this.v = v;
        TextView tv_position = v.findViewById(R.id.tv_position);

        //<editor-fold desc="Toast show">

        Context context = getApplicationContext();
        CharSequence text =
                "Click pos.: " + tv_position.getText().toString();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        //</editor-fold>

        //<editor-fold desc="Intent to item details">
        //** Empaqueta objeto "pet" ...
        Bundle bundle = new Bundle();
        bundle.putSerializable("objetoPet", (Serializable) pet);

        //** Y envía el paquete a siguiente pantlla...
        Intent intent = new Intent(this, a22PetUpdDelActivity.class);
        intent.putExtra("objetoPet", bundle);

        String message = tv_position.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);      // <-- intent
        //</editor-fold>

    }

}

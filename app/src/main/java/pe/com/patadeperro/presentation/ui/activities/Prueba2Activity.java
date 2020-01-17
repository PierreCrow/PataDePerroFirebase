package pe.com.patadeperro.presentation.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.com.patadeperro.R;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.presentation.presenter.PetPresenter;
import pe.com.patadeperro.presentation.view.PetView;


public class Prueba2Activity extends BaseActivity implements PetView {

    EditText etNameCrearePet;
    EditText etRaceCreatePet;
    Button btnCreatePet;
    String nombre, race;

    PetPresenter petPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba2_activity);

        etNameCrearePet=(EditText)findViewById(R.id.etNameCreatePet);
        etRaceCreatePet=(EditText)findViewById(R.id.etRaceCreatePet);
        btnCreatePet=(Button) findViewById(R.id.btnCreatePet);

        petPresenter= new PetPresenter();
        petPresenter.addView(this);

        btnCreatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre=etNameCrearePet.getText().toString();
                race=etRaceCreatePet.getText().toString();

                Pet pet= new Pet(
                        "", "", "", nombre, race,
                        "", ""
                        );

                petPresenter.createPet(pet);

            }
        });

    }

    @Override
    public void petCreated(Pet pet) {

    }

    @Override
    public void petUpdated(Pet pet) {

    }

    @Override
    public void petsListLoaded(ArrayList<Pet> pets) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}

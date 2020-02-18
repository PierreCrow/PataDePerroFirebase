package pe.com.patadeperro.presentation.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.com.patadeperro.R;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.data.datasource.cloud.store.CloudPetDataStore;
import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.presentation.presenter.PetPresenter;
import pe.com.patadeperro.presentation.utils.Constants;
import pe.com.patadeperro.presentation.view.PetView;

import static java.lang.Thread.*;

public class a28PetSplashActivity
        extends BaseActivity
        implements PetView {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    PetPresenter petPresenterS;
    public static ArrayList listaPet = new ArrayList<> ();
    public static Boolean flagPetsListLoaded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a28_pet_splash);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
//                Intent mainIntent = new Intent(
//                        a28PetSplashActivity.this,
//                        a20PetAddListActivity.class);
//                a28PetSplashActivity.this.startActivity(mainIntent);
//                a28PetSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

        petPresenterS= new PetPresenter();
        petPresenterS.addView(this);
//        petPresenterS.loadPets(Constants.CLOUD);


        Pet pet = new Pet(); pet.setIdCloud("*ALL");   // nuke trigger
        petPresenterS.deletePet(pet, Constants.DB);    // ejecuta DELETE *ALL from local
        // al terminar, en petDeleted(), lanza loadPets()

    }

    @Override
    public void petCreated(Pet pet) {

    }

    @Override
    public void petCreatedList(List<Pet> petList) {

        petPresenterS.removeView(this);

        // _todo_ listo, aquí se podría lanzar la siguiente pantalla
        //
        // Sleep(SPLASH_DISPLAY_LENGTH);
        //
        // Create an Intent that will start the Menu-Activity.
        Intent mainIntent = new Intent(
                a28PetSplashActivity.this,
                a20PetAddListActivity.class);
        a28PetSplashActivity.this.startActivity(mainIntent);

        a28PetSplashActivity.this.finish();

    }

    @Override
    public void petUpdated(Pet pet) {

    }

    @Override
    public void petDeleted(Pet pet) {

        flagPetsListLoaded = false;
        petPresenterS.loadPets(Constants.CLOUD);        // sigue en petsListLoaded()

    }

    @Override
    public void petsListLoaded(ArrayList<Pet> pets) {

        if (!flagPetsListLoaded) {
            petPresenterS.createPetList(pets, Constants.DB);    // copiado a DB, sige petCreatedList()
        }
        flagPetsListLoaded = true;

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;                // <-- ojo, por default puede salir null ¿? Cambie a "this"
    }

    protected void Sleep (int seconds) {
        try {
            sleep(seconds*1000);
        } catch (InterruptedException e) {
            currentThread().interrupt();
            e.printStackTrace();
        }
    }
}

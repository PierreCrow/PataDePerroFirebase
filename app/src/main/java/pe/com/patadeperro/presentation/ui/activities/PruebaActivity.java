package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.com.patadeperro.R;

import java.util.ArrayList;

import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.presentation.presenter.UsuarioPresenter;
import pe.com.patadeperro.presentation.view.UsuarioView;

public class PruebaActivity extends BaseActivity implements UsuarioView {

    EditText etNameCreareUser;
    EditText etMailCreateUser;
    Button btnCreateUser;
    String nombre,email;

    UsuarioPresenter usuarioPresenter;


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_activity);

        etNameCreareUser=(EditText)findViewById(R.id.etNameCreateUser);
        etMailCreateUser=(EditText)findViewById(R.id.etMailCreateUser);
        btnCreateUser=(Button) findViewById(R.id.btnCreateUser);

        usuarioPresenter= new UsuarioPresenter();
        usuarioPresenter.addView(this);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre=etNameCreareUser.getText().toString();
                email=etMailCreateUser.getText().toString();

                Usuario usuario= new Usuario( "", "", nombre, "", email,
                        0.0, 0.0, false,
                false, "", false);

                usuarioPresenter.createUsuario(usuario);

            }
        });

    }



    @Override
    public void userCreated(Usuario usuario) {

    }

    @Override
    public void userUpdated(Usuario usuario) {

    }

    @Override
    public void usersListLoaded(ArrayList<Usuario> usuarios) {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
    }


}
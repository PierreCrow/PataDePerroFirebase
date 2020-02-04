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

import pe.com.patadeperro.domain.model.Usuario;
import pe.com.patadeperro.presentation.presenter.UsuarioPresenter;
import pe.com.patadeperro.presentation.utils.Constants;
import pe.com.patadeperro.presentation.view.UsuarioView;

import static pe.com.patadeperro.presentation.ui.activities.Prueba00MainActivity.EXTRA_MESSAGE;

/**
 * Clase ** Prueba10UserAddListActivity ** Usuario *********************************************************
 */
public class Prueba10UserAddListActivity
        extends BaseActivity
        implements UsuarioView,
        ListAdapterUsuario.OnItemClickListener {

    /**
    Variables, definición de objetos --------- ---------- ------- --------
         */
    EditText etNameCreateUser;
    EditText etMailCreateUser;

    Button btnCreateUser;
    String nombre, email;

    UsuarioPresenter usuarioPresenter;
    RecyclerView rvlistadoUsuario;

    public static Usuario usuario = new
            Usuario(
                 0,
                "",
                "",
                "Juan",
                "555-1",
                "juan@xmail",
                0.0,
                0.0,
                false,
                true,
                "2020/01/21",
                true
                );

    // sin datos

    /**
     *  carga algunos valores de prueba */
    static Usuario usuario1 = new
            Usuario(
                     0,
            "",
            "",
            "Pedro",
            "555-1",
            "pedro@zmail",
            0.0,
            0.0,
            false,
            true,
            "2020/01/21",
            true
    );

    static Usuario usuario2 = new
            Usuario(
                     0,
            "",
            "",
            "Mario",
            "555-2",
            "mmjz@tmpmail",
            0.0,
            0.0,
            false,
            true,
            "2020/01/21",
            true);

    static Usuario usuario3 = new
            Usuario(
                     0,
            "",
            "",
            "Teresa",
            "555-3",
            "mt123@tmpmail",
            0.0,
            0.0,
            false,
            true,
            "2020/01/21",
            true);


    public static ArrayList listaUsuario = new ArrayList<>(
            Arrays.asList(usuario1, usuario2, usuario3));
    // con datos de prueba por ahora

    private ListAdapterUsuario adapterUsuario;
    private ListAdapterUsuario.OnItemClickListener mlistenerUsuario;
    private View v;

    /**
    método onPause --------------------------------------------------
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
        setContentView(R.layout.prueba10_user_add_list_activity);

        etNameCreateUser = (EditText) findViewById(R.id.etNameCreateUser);
        etMailCreateUser = (EditText) findViewById(R.id.etMailCreateUser);

        btnCreateUser = (Button) findViewById(R.id.btnCreateUser);

        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);

        // lista
        mlistenerUsuario = this;

        rvlistadoUsuario = findViewById(R.id.rvListadoUsuario);
        rvlistadoUsuario.setLayoutManager(
                new LinearLayoutManager(
                        getApplicationContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                )
        );

        usuarioPresenter.loadUsuarios();        // cuando se ejecuta, carga adapterUsuario new
        /**
        adapterUsuario = new ListAdapterUsuario(
                mlistenerUsuario,
                getApplicationContext(),
                listaUsuario);
        */

        rvlistadoUsuario.setAdapter(adapterUsuario);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = etNameCreateUser.getText().toString();
                email = etMailCreateUser.getText().toString();

                Usuario usuario = new
                        Usuario (
                         0,
                        "",
                        "",
                        nombre,
                        "555-1",
                        email,
                        0.0,
                        0.0,
                        false,
                        true,
                        "2020/01/21",
                        true
                );

                usuarioPresenter.createUsuario(usuario, Constants.CLOUD);

                // toast
                Context context = getApplicationContext();
                CharSequence text =
                        "Click Botón crear usuario"
                        ;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                adapterUsuario.notifyDataSetChanged();

            }
        });

    }

    /** -------------------------------------------------------------------------------------
    método userCreated
    */
    @Override
    public void userCreated(Usuario usuario) {

        if (usuario.getCloudIntCount() > usuario.getDbIntCount()) {
            usuarioPresenter.createUsuario(usuario, Constants.DB);
        } else if (usuario.getCloudIntCount() < usuario.getDbIntCount()) {
            usuarioPresenter.createUsuario(usuario, Constants.CLOUD);
        }

    }

    /** -------------------------------------------------------------------------------------
    método userUpdated
    */
    @Override
    public void userUpdated(Usuario usuario) {

    }

    @Override
    public void userDeleted(Usuario usuario) {

    }

    /** -------------------------------------------------------------------------------------
    método usersListLoaded
    */
    @Override
    public void usersListLoaded(ArrayList<Usuario> usuarios) {

        listaUsuario = usuarios;
        // adapterUsuario.notifyDataSetChanged();

        adapterUsuario = new ListAdapterUsuario(
                mlistenerUsuario,
                getApplicationContext(),
                listaUsuario);
        rvlistadoUsuario.setAdapter(adapterUsuario);

    }

    /** -------------------------------------------------------------------------------------
    método showErrorMessage
    */
    @Override
    public void showErrorMessage(String message) {

    }

    /** -------------------------------------------------------------------------------------
    método getContext
    */
    @Override
    public Context getContext() {
        return this;
    }

    /** -------------------------------------------------------------------------------------
    método onItemClicked
    */
    @Override
    public void onItemClicked(View v, Usuario user) {

        // this.v = v;
        TextView tv_position = v.findViewById(R.id.tv_position);

        // toast

        Context context = getApplicationContext();
        CharSequence text =
                "Click pos.: " + tv_position.getText().toString();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // <-- toast    // <-- toast

        // intent

        //** Empaqueta objeto "user" ...
        Bundle bundle = new Bundle();
        bundle.putSerializable("objetoUser", user);

        //** Y envía el paquete a siguiente pantlla...
        Intent intent = new Intent(this, Prueba12UserUpdDelActivity.class);
        intent.putExtra("objetoUser", bundle);

        String message = tv_position.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);      // <-- intent


    }

}
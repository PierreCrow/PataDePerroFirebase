package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.com.patadeperro.R;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.domain.model.Usuario;

public class ListAdapterUsuario
        extends
        RecyclerView.Adapter<ListAdapterUsuario.UsuarioViewHolder> {

    public OnItemClickListener mlistener;
    private Context mContext;
    private List<Usuario> items = new ArrayList<>();   // items, definición solamente

    public ListAdapterUsuario(
            OnItemClickListener listener,
            Context context,
            ArrayList<Usuario> item
    )     {

        this.mlistener = listener;
        this.mContext = context;
        this.items = item;

    }

    public interface OnItemClickListener {
        void onItemClicked(View v);
    }
    public void add(Usuario item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }
    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.ly_item_usuario, parent, false);

        UsuarioViewHolder rvMainAdapterViewHolder = new UsuarioViewHolder(view);

        return rvMainAdapterViewHolder;
    }
    @Override
    public void onBindViewHolder(final UsuarioViewHolder holder, int position) {

        Usuario Usuario = items.get(position);

        holder.name.setText(Usuario.getName());
        holder.phone.setText(Usuario.getPhoneNumber());
        holder.email.setText(Usuario.getEmail());

    }
    @Override
    public int getItemCount() {

        return items.size();
    }

    class UsuarioViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView name;
        TextView phone;
        TextView email;
        

        public UsuarioViewHolder(View v) {
            super(v);

            name=(TextView)v.findViewById(R.id.tv_name);
            phone=(TextView)v.findViewById(R.id.tv_phone);
            email=(TextView)v.findViewById(R.id.tv_email);

            v.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {

      /*    SharedPreferences preferencias =
                v.getContext().getSharedPreferences("TareaSeleccionada", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("id_tarea", id_tarea.getText().toString());
            editor.putString("detalleTarea", detalleTarea.getText().toString());
            editor.putString("fechaTarea", fechaTarea.getText().toString());
            editor.putString("nameTarea", nameTarea.getText().toString());
            editor.putString("terminada", terminada.getText().toString());
            editor.commit();
*/
            mlistener.onItemClicked(v );

        }
    }

}


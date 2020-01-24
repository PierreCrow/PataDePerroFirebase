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

/** 
 * Clase ** ListAdapterUsuario ***************************
 */
public class ListAdapterUsuario
        extends
        RecyclerView.Adapter<ListAdapterUsuario.UsuarioViewHolder> {

    public OnItemClickListener mlistener;
    private Context mContext;
    private List<Usuario> items = new ArrayList<>();   // items, definición solamente

    /**
     * Constructor  
    */
    public ListAdapterUsuario(
            OnItemClickListener listener,
            Context context,
            ArrayList<Usuario> item
    )     {

        this.mlistener = listener;
        this.mContext = context;
        this.items = item;

    }

    /**
     * Interface <-- ojo --
     */
    public interface OnItemClickListener {
        void onItemClicked(View v, Usuario user);
    }

    /**
     * método add
     * @param item
     */
    public void add(Usuario item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    /**
     * método onCreateViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.ly_item_usuario, parent, false);

        UsuarioViewHolder rvMainAdapterViewHolder = new UsuarioViewHolder(view);

        return rvMainAdapterViewHolder;
    }

    /**
     * método onBindViewHolder
     * @param holder
     * @param position
     */
    @Override // <-- onBind... definir campos aquí
    public void onBindViewHolder(final UsuarioViewHolder holder, int position) {

        Usuario usuario = items.get(position);

        holder.position.setText(Integer.toString(position));     // guarda la posición
        holder.name.setText(usuario.getName());
        holder.phone.setText(usuario.getPhoneNumber());
        holder.email.setText(usuario.getEmail());

    }

    /**
     * método getItemCount
     * @return
     */
    @Override
    public int getItemCount() {

        return items.size();
    }

    // subclase... definir aquí también campos <-- tiene métodos *ViewHolder y onClick
    /**
     * subclase UsuarioViewHolder
     */
    class UsuarioViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView position;  // para guardar position
        TextView name;
        TextView phone;
        TextView email;


        /**
         * método UsuarioViewHolder
         * @param v
         */
        public UsuarioViewHolder(View v) {
            super(v);

            position=(TextView)v.findViewById(R.id.tv_position);
            name=(TextView)v.findViewById(R.id.tv_name);
            phone=(TextView)v.findViewById(R.id.tv_phone);
            email=(TextView)v.findViewById(R.id.tv_email);

            v.setOnClickListener(this);

        }

        /**
         * método onClick
         * @param v
         */
        @Override
        public void onClick(View v) {

            int i = Integer.parseInt(position.getText().toString());
            Usuario usuario = items.get(i);
            mlistener.onItemClicked(v, usuario);

        }
    }

}


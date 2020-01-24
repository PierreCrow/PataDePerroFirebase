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

import pe.com.patadeperro.domain.model.Abuse;

public class ListAdapterAbuse
        extends 
        RecyclerView.Adapter<ListAdapterAbuse.AbuseViewHolder> {

    public ListAdapterAbuse.OnItemClickListener mlistener;
    private Context mContext;
    private List<Abuse> items = new ArrayList<>();   // items, definición solamente

    public ListAdapterAbuse(
            ListAdapterAbuse.OnItemClickListener listener,
            Context context,
            ArrayList<Abuse> item
    )     {

        this.mlistener = listener;
        this.mContext = context;
        this.items = item;

    }

    public interface OnItemClickListener {
        void onItemClicked(View v);
    }
    public void add(Abuse item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }
    @Override
    public ListAdapterAbuse.AbuseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from
                (parent.getContext()).inflate(
                        R.layout.ly_item_abuse,
                        parent,
                        false);

        ListAdapterAbuse.AbuseViewHolder
                rvMainAdapterViewHolder =
                new ListAdapterAbuse.AbuseViewHolder(view);

        return rvMainAdapterViewHolder;
    }
    @Override
    public void onBindViewHolder(
            final ListAdapterAbuse.AbuseViewHolder holder,
            int position) {

        Abuse Abuse = items.get(position);

        holder.position.setText(Integer.toString(position));     // guarda la posición

        holder.petName.setText(Abuse.getPetName());
        holder.contactPhone.setText(Abuse.getContactPhoneNumber());
        holder.contactName.setText(Abuse.getContactName());

    }
    @Override
    public int getItemCount() {

        return items.size();
    }

    class AbuseViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView position;   // para guardar la pos.

        TextView petName;
        TextView contactPhone;
        TextView contactName;

        public AbuseViewHolder(View v) {
            super(v);

            position=(TextView)v.<View>findViewById(R.id.tv_position);

            petName=(TextView)v.findViewById(R.id.tv_petName);
            contactPhone=(TextView)v.findViewById(R.id.tv_contactPhone);
            contactName=(TextView)v.findViewById(R.id.tv_contactName);

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


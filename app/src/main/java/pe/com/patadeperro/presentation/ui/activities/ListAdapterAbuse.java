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

/**
 * Clase ** ListAdapterAbuse ***************************
 */
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

    /**
     * Interface <-- ojo --
     */
    public interface OnItemClickListener {
        void onItemClicked(View v, Abuse abuse);
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

    /**
     * método onBindViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(
            final ListAdapterAbuse.AbuseViewHolder holder,
            int position) {

        Abuse Abuse = items.get(position);

        holder.position.setText(String.valueOf(position));     // guarda la posición
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

        /**
         * método PetViewHolder
         * @param v
         */
        public AbuseViewHolder(View v) {
            super(v);

            position=(TextView)v.findViewById(R.id.tv_position);
            petName=(TextView)v.findViewById(R.id.tv_petName);
            contactPhone=(TextView)v.findViewById(R.id.tv_contactPhone);
            contactName=(TextView)v.findViewById(R.id.tv_contactName);

            v.setOnClickListener(this);

        }


        /**
         * método onClick
         * @param v
         */
        @Override
        public void onClick(View v) {

            int i = Integer.parseInt(position.getText().toString());
            Abuse abuse = items.get(i);
            mlistener.onItemClicked(v, abuse );

        }
    }

}


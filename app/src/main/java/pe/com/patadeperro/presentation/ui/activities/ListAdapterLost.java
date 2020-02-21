package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.com.patadeperro.R;

import java.util.ArrayList;
import java.util.List;

import pe.com.patadeperro.domain.model.Lost;
import pe.com.patadeperro.presentation.utils.ImageUtil;

public class ListAdapterLost
        extends 
        RecyclerView.Adapter<ListAdapterLost.LostViewHolder> {

    public ListAdapterLost.OnItemClickListener mlistener;
    private Context mContext;
    private List<Lost> items = new ArrayList<>();   // items, definición solamente


    public ListAdapterLost(
            ListAdapterLost.OnItemClickListener listener,
            Context context,
            ArrayList<Lost> item
    )     {

        this.mlistener = listener;
        this.mContext = context;
        this.items = item;

    }

    public interface OnItemClickListener {
        void onItemClicked(View v, Lost lost);
    }

    public void add(Lost item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    @Override
    public ListAdapterLost.LostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from
                (parent.getContext()).inflate(
                        R.layout.ly_item_lost,
                        parent,
                        false);

        ListAdapterLost.LostViewHolder
                rvMainAdapterViewHolder =
                new ListAdapterLost.LostViewHolder(view);

        return rvMainAdapterViewHolder;
    }

    @Override   // <-- onBind... definir campos aquí
    public void onBindViewHolder(
            final ListAdapterLost.LostViewHolder holder,
            int position) {

        Lost lost = items.get(position);    // carga objeto LOST( position )

        holder.position.setText(Integer.toString(position));     // guarda la posición
        holder.image.setImageBitmap( ImageUtil.convert(lost.getDescription()) );
        holder.name.setText(lost.getPetName());
        holder.race.setText(lost.getRace());
        holder.address.setText(lost.getLostAddress());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // subclase... definir aquí también campos
    class LostViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView position;   // para guardar la pos.
        ImageView image;
        TextView name;
        TextView race;
        TextView address;


        public LostViewHolder(View v) {
            super(v);

            position=(TextView)v.findViewById(R.id.tv_position);
            image=(ImageView)v.findViewById(R.id.iv_petImage);
            name=(TextView)v.findViewById(R.id.tv_name);
            race=(TextView)v.findViewById(R.id.tv_itmRace);
            address=(TextView)v.findViewById(R.id.tv_address);

            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int i = Integer.parseInt(position.getText().toString());
            Lost lost = items.get(i);

            mlistener.onItemClicked(v, lost);

        }
    }

}


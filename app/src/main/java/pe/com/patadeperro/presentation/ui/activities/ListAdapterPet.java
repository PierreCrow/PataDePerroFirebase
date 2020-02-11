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

import pe.com.patadeperro.domain.model.Pet;

public class ListAdapterPet
        extends RecyclerView.Adapter<ListAdapterPet.PetViewHolder> {

    public ListAdapterPet.OnItemClickListener mlistener;
    private Context mContext;
    private List<Pet> items = new ArrayList<>();   // items, definición solamente

    public ListAdapterPet(
            ListAdapterPet.OnItemClickListener listener,
            Context context,
            ArrayList<Pet> item
    )     {

        this.mlistener = listener;
        this.mContext = context;
        this.items = item;

    }

    /**
     * Interface <-- ojo --
     */
    public interface OnItemClickListener {
        void onItemClicked(View v, Pet pet);
    }

    public void add(Pet item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    @Override
    public ListAdapterPet.PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from
                (parent.getContext()).inflate(R.layout.ly_item_pet, parent, false);

        ListAdapterPet.PetViewHolder rvMainAdapterViewHolder = new ListAdapterPet.PetViewHolder(view);

        return rvMainAdapterViewHolder;
    }

    /**
     * método onBindViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(
            final ListAdapterPet.PetViewHolder holder,
            int position) {

        Pet Pet = items.get(position);

        String s_position = String.valueOf(position);
        holder.position.setText(s_position);     // guarda la posición
        holder.name.setText(Pet.getName());
        holder.race.setText(Pet.getRace());
        holder.gender.setText(Pet.getGender());

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    class PetViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView position;  // para guardar position
        TextView name;
        TextView race;
        TextView gender;

        /**
         * método PetViewHolder
         * @param v
         */
        public PetViewHolder(View v) {
            super(v);

            position=(TextView)v.findViewById(R.id.tv_position);
            name=(TextView)v.findViewById(R.id.tv_name);
            race=(TextView)v.findViewById(R.id.tv_race);
            gender=(TextView)v.findViewById(R.id.tv_gender);

            v.setOnClickListener(this);

        }

        /**
         * método onClick
         * @param v
         */
        @Override
        public void onClick(View v) {

            int i = Integer.parseInt(position.getText().toString());
            Pet pet = items.get(i);
            mlistener.onItemClicked(v, pet);

        }
    }


}


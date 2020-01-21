package pe.com.patadeperro.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.com.patadeperro.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pe.com.patadeperro.domain.model.Pet;
import pe.com.patadeperro.domain.model.Pet;

public class ListAdapterPet
        extends 
        RecyclerView.Adapter<ListAdapterPet.PetViewHolder> {

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

    public interface OnItemClickListener {
        void onItemClicked(View v);
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
    @Override
    public void onBindViewHolder(final ListAdapterPet.PetViewHolder holder, int position) {

        Pet Pet = items.get(position);

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

        TextView name;
        TextView race;
        TextView gender;


        public PetViewHolder(View v) {
            super(v);

            name=(TextView)v.findViewById(R.id.tv_name);
            race=(TextView)v.findViewById(R.id.tv_race);
            gender=(TextView)v.findViewById(R.id.tv_gender);

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


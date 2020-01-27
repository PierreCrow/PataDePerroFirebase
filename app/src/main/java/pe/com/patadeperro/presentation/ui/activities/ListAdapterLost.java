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

import pe.com.patadeperro.domain.model.Lost;

public class ListAdapterLost
        extends 
        RecyclerView.Adapter<ListAdapterLost.LostViewHolder> {

    public ListAdapterLost.OnItemClickListener mlistener;
    private Context mContext;
    private List<Lost> items = new ArrayList<>();   // items, definición solamente
    // definición de objetos, variables

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

        Lost lost = items.get(position);
        holder.position.setText(Integer.toString(position));     // guarda la posición

        // campos visibles de item en pantalla
        holder.name.setText(lost.getPetName());
        holder.race.setText(lost.getRace());
        holder.address.setText(lost.getLostAddress());

/*
        // campos invisibles (gone)...
        //holder.g_id.setText(lost.getId());
        holder.g_idCloud.setText(lost.getIdCloud());
        holder.g_petName.setText(lost.getPetName());
        holder.g_race.setText(lost.getRace());
        holder.g_gender.setText(lost.getGender());
        holder.g_color.setText(lost.getColor());
        holder.g_age.setText(lost.getAge());
        holder.g_contactPhoneNumber.setText(lost.getContactPhoneNumber());
        holder.g_contactName.setText(lost.getContactName());
        holder.g_description.setText(lost.getDescription());
        holder.g_reward.setText(lost.getReward());
        holder.g_rewardAmount.setText(lost.getRewardAmount());
        holder.g_country.setText(lost.getCountry());
        holder.g_state.setText(lost.getState());
        holder.g_city.setText(lost.getCity());
        holder.g_urlImage.setText(lost.getUrlImage());
        holder.g_lat.setText(lost.getLat());
        holder.g_lng.setText(lost.getLng());
        holder.g_lostAddress.setText(lost.getLostAddress());
        holder.g_found.setText(lost.getFound());
*/

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

        // campos visibles
        TextView name;
        TextView race;
        TextView address;

        // campos invisibles
        TextView g_id;
        TextView g_idCloud;
        TextView g_petName;
        TextView g_race;
        TextView g_gender;
        TextView g_color;
        TextView g_age;
        TextView g_contactPhoneNumber;
        TextView g_contactName;
        TextView g_description;
        TextView g_reward;
        TextView g_rewardAmount;
        TextView g_country;
        TextView g_state;
        TextView g_city;
        TextView g_urlImage;
        TextView g_lat;
        TextView g_lng;
        TextView g_lostAddress;
        TextView g_found;


        public LostViewHolder(View v) {
            super(v);

            position=(TextView)v.<View>findViewById(R.id.tv_position);

            // visibles
            name=(TextView)v.findViewById(R.id.tv_name);
            race=(TextView)v.findViewById(R.id.tv_itmRace);
            address=(TextView)v.findViewById(R.id.tv_address);

        /*    // invisibles
            // g_id=(TextView)v.findViewById(R.id.tv_id);
            g_idCloud=(TextView)v.findViewById(R.id.tv_idCloud);
            g_petName=(TextView)v.findViewById(R.id.tv_petName);
            g_race=(TextView)v.findViewById(R.id.tv_race);
            g_gender=(TextView)v.findViewById(R.id.tv_gender);
            g_color=(TextView)v.findViewById(R.id.tv_color);
            g_age=(TextView)v.findViewById(R.id.tv_age);
            g_contactPhoneNumber=(TextView)v.findViewById(R.id.tv_contactPhoneNumber);
            g_contactName=(TextView)v.findViewById(R.id.tv_contactName);
            g_description=(TextView)v.findViewById(R.id.tv_description);
            g_reward=(TextView)v.findViewById(R.id.tv_reward);
            g_rewardAmount=(TextView)v.findViewById(R.id.tv_rewardAmount);
            g_country=(TextView)v.findViewById(R.id.tv_country);
            g_state=(TextView)v.findViewById(R.id.tv_state);
            g_city=(TextView)v.findViewById(R.id.tv_city);
            g_urlImage=(TextView)v.findViewById(R.id.tv_urlImage);
            g_lat=(TextView)v.findViewById(R.id.tv_lat);
            g_lng=(TextView)v.findViewById(R.id.tv_lng);
            g_lostAddress=(TextView)v.findViewById(R.id.tv_lostAddress);
            g_found=(TextView)v.findViewById(R.id.tv_found);
*/
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

         /*   Lost lost= new Lost(
                  // g_id.getText().toString(), // <- debería ser autogenerado
                  g_idCloud.getText().toString(),
                  g_petName.getText().toString(),
                  g_race.getText().toString(),
                  g_gender.getText().toString(),
                  g_color.getText().toString(),
                  g_age.getText().toString(),
                  g_contactPhoneNumber.getText().toString(),
                  g_contactName.getText().toString(),
                  g_description.getText().toString(),
                  g_reward.getText().toString(),
                  g_rewardAmount.getText().toString(),
                  g_country.getText().toString(),
                  g_state.getText().toString(),
                  g_city.getText().toString(),
                  g_urlImage.getText().toString(),
                  g_lat.getText().toString(),
                  g_lng.getText().toString(),
                  g_lostAddress.getText().toString(),
                  g_found.getText().toString()

                  );
*/
            /**
             * otra forma puede ser:
*/

            int i = Integer.parseInt(position.getText().toString());
            Lost lost = items.get(i);
//*/

            mlistener.onItemClicked(v, lost);

        }
    }

}


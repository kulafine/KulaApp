package appr.kulaf.com.kulaapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Prince on 1/20/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.Food_item_Holder>{

    private List<Fooditem> foods;
    private Context context;

    public FoodAdapter(List<Fooditem> foods, Context context) {
        this.foods = foods;
        this.context = context;
    }

    @Override
    public Food_item_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return new Food_item_Holder(v);
    }

    @Override
    public void onBindViewHolder(Food_item_Holder holder, int position) {

        Fooditem fooditem = foods.get(position);
        holder.t_name.setText(fooditem.getName());
        holder.t_desc.setText(fooditem.getDesc());
        holder.t_price.setText(fooditem.getPrice());

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class Food_item_Holder extends RecyclerView.ViewHolder{

        public TextView t_name,t_desc,t_price;

        public Food_item_Holder(View itemView) {
            super(itemView);

            t_name = (TextView)itemView.findViewById(R.id.fname);
            t_desc = (TextView)itemView.findViewById(R.id.fword);
            t_price = (TextView)itemView.findViewById(R.id.fprice);

        }
    }

}

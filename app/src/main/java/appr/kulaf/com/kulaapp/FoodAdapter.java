package appr.kulaf.com.kulaapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Fiston on 1/18/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.Fooditem_Holder>{


    private List<Food_model> foods;
    private Context context;

    public FoodAdapter(List<Food_model> foods, Context context) {
        this.foods = foods;
        this.context = context;
    }

    @Override
    public Fooditem_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return new Fooditem_Holder(v);
    }

    @Override
    public void onBindViewHolder(Fooditem_Holder holder, int position) {

        Food_model food_model = foods.get(position);
        holder.fname.setText(food_model.getName());
        holder.fword.setText(food_model.getWord());
        holder.fprice.setText(food_model.getPrice());

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class Fooditem_Holder extends RecyclerView.ViewHolder{

        public TextView fname,fword,fprice;
        public ImageView fimg;

        public Fooditem_Holder(View itemView) {
            super(itemView);

            fname = (TextView) itemView.findViewById(R.id.fname);
            fword = (TextView) itemView.findViewById(R.id.fword);
            fprice = (TextView) itemView.findViewById(R.id.fprice);
            fimg = (ImageView) itemView.findViewById(R.id.img);

        }
    }

}

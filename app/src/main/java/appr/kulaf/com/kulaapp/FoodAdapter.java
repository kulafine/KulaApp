package appr.kulaf.com.kulaapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prince on 1/20/2017.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.Food_item_Holder>{

    private List<Fooditem> foods;
    private Context context;

    public FoodAdapter(List<Fooditem> foods, Context c) {
        this.foods = foods;
        this.context = c;
    }

    @Override
    public Food_item_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        Food_item_Holder food_item_holder = new Food_item_Holder(v, context, foods);

        return food_item_holder;
    }

    @Override
    public void onBindViewHolder(Food_item_Holder holder, int position) {

        Fooditem fooditem = foods.get(position);
        holder.t_name.setText(fooditem.getName());
        holder.t_word.setText(fooditem.getWord());
        holder.t_price.setText(fooditem.getPrice());

        Picasso
                .with(context)
                .load(fooditem.getUrl())
                .resize(380,150)
                .centerCrop()
                .into(holder.t_image);


    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class Food_item_Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView t_name,t_word,t_price;
        public ImageView t_image;
        Context ctx;
        List<Fooditem> fo = new ArrayList<Fooditem>();

        public Food_item_Holder(View itemView, Context c, List<Fooditem> fs) {
            super(itemView);
            this.ctx = c;
            this.fo = fs;
            itemView.setOnClickListener(this);


            t_name = (TextView)itemView.findViewById(R.id.fname);
            t_image = (ImageView)itemView.findViewById(R.id.img);
            t_price = (TextView)itemView.findViewById(R.id.fprice);
            t_word = (TextView)itemView.findViewById(R.id.fword);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Fooditem food = this.fo.get(position);
            Intent i = new Intent(this.ctx, OrderActivity.class);
            i.putExtra("im",food.getUrl());
            this.ctx.startActivity(i);

        }
    }

}

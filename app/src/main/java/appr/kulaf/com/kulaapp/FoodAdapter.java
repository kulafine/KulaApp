package appr.kulaf.com.kulaapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
        Food_item_Holder food_item_holder = new Food_item_Holder(v);

        return food_item_holder;
    }

    @Override
    public void onBindViewHolder(Food_item_Holder holder, int position) {

        final Fooditem fooditem = foods.get(position);
        holder.t_name.setText(fooditem.getName());
        holder.t_word.setText(fooditem.getWord());
        holder.t_price.setText(fooditem.getPrice());

        Picasso
                .with(context)
                .load(fooditem.getUrl())
                .resize(380,150)
                .centerCrop()
                .into(holder.t_image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked" + fooditem.getWord(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class Food_item_Holder extends RecyclerView.ViewHolder{

        public TextView t_name,t_word,t_price;
        public ImageView t_image;
        public LinearLayout linearLayout;


        public Food_item_Holder(View itemView) {
            super(itemView);


            t_name = (TextView)itemView.findViewById(R.id.fname);
            t_image = (ImageView)itemView.findViewById(R.id.img);
            t_price = (TextView)itemView.findViewById(R.id.fprice);
            t_word = (TextView)itemView.findViewById(R.id.fword);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linear);

        }


    }

}

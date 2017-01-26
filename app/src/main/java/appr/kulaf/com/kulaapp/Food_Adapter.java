package appr.kulaf.com.kulaapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Prince on 1/22/2017.
 */

public class Food_Adapter extends RecyclerView.Adapter<Food_Adapter.Fooditem_Holder>{

    private ArrayList<Fooditem> fooditems = new ArrayList<Fooditem>();
    private Context ct;


    public Food_Adapter(ArrayList<Fooditem> fooditems, Context c) {
        this.fooditems = fooditems;
        this.ct = c;
    }

    @Override
    public Fooditem_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        Fooditem_Holder fooditem_holder = new Fooditem_Holder(v);
        return fooditem_holder;
    }

    @Override
    public void onBindViewHolder(Fooditem_Holder holder, int position) {

        Fooditem fooditem = fooditems.get(position);
        holder.fname.setText(fooditem.getName());
        holder.fprice.setText(fooditem.getPrice());
        Picasso
                .with(ct)
                .load(fooditem.getImage_url())
                .centerCrop()
                .resize(100,100)
                .placeholder(R.mipmap.images)
                .into(holder.fimage);
    }

    @Override
    public int getItemCount() {
        return fooditems.size();
    }

    public static class Fooditem_Holder extends RecyclerView.ViewHolder{

        ImageView fimage;
        TextView fname,fprice;



        public Fooditem_Holder(View itemView) {
            super(itemView);

            fimage = (ImageView) itemView.findViewById(R.id.img);
            fname = (TextView)itemView.findViewById(R.id.fname);
            fprice = (TextView)itemView.findViewById(R.id.fprice);

        }


    }


}

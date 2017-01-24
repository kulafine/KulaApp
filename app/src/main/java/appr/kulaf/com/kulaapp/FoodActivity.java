package appr.kulaf.com.kulaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    private static final String url = "http://192.168.40.215/kulafine/scripts/loadJson.php";
    private ArrayList<Fooditem> fooditems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fooditems = new ArrayList<>();

        loadMenu();

    }

    private void loadMenu(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Menu...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("foods");

                    for (int i  = 0 ; i < jsonArray.length() ; i++)
                    {
                        JSONObject o = jsonArray.getJSONObject(i);
                        Fooditem fooditem = new Fooditem(
                                o.getString("image"),
                                o.getString("name"),
                                o.getString("price"),
                                o.getString("word"),
                                o.getString("desc")
                        );

                        fooditems.add(fooditem);
                    }

                    adapter = new Food_Adapter(fooditems,FoodActivity.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void OnitemClick(View view, int position) {


                            Fooditem food = fooditems.get(position);
                            Intent i = new Intent(getApplicationContext(), OrderActivity.class);
                            i.putExtra("im",food.getImage_url());
                            i.putExtra("desc",food.getDesc());
                            startActivity(i);

                        }
                    }));


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        Server.getServer_instance(this).addRequest(stringRequest);

    }


}

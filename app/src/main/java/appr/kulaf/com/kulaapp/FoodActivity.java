package appr.kulaf.com.kulaapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private String URL = "http://192.168.40.215/kulafine/loadJson.php";
    private List<Food_model> foods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foods = new ArrayList<Food_model>();

        loadMenu();


    }

    private void loadMenu(){

        final ProgressDialog loading = new ProgressDialog(this);
        loading.setMessage("Loading Menu...");
        loading.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                loading.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Foods");

                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject o = jsonArray.getJSONObject(i);
                        Food_model food = new Food_model(
                                o.getString("name"),
                                o.getString("image"),
                                o.getString("word"),
                                o.getString("price"));

                        foods.add(food);
                    }

                    adapter = new FoodAdapter(foods, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loading.dismiss();
                Toast.makeText(getApplicationContext(), "No internet Connection", Toast.LENGTH_LONG).show();

            }
        });

        Server.getServer_instance(getApplicationContext()).addRequest(stringRequest);

    }
}

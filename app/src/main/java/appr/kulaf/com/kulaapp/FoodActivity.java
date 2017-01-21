package appr.kulaf.com.kulaapp;

import android.app.ProgressDialog;
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
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Fooditem> fooditems;
    private static final String url = "http://192.168.40.215/kulafine/scripts/loadJson.php";


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
        final ProgressDialog loader = new ProgressDialog(this);
        loader.setMessage("Loading Menu...");
        loader.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                loader.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("foods");

                    for (int i  = 0 ; i < array.length() ; i++){
                        JSONObject object = array.getJSONObject(i);
                        Fooditem fooditem = new Fooditem(object.getString("name"),object.getString("image"),object.getString("word"),object.getString("price"));
                        fooditems.add(fooditem);

                    }

                    adapter = new FoodAdapter(fooditems, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loader.dismiss();
                Toast.makeText(getApplicationContext(), "Error  : "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Server.getServer_instance(getApplicationContext()).addRequest(stringRequest);
    }


    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }
        return false;
    }



    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

        if (isLastItemDisplaying(recyclerView)) {

            loadMenu();
        }
    }


}

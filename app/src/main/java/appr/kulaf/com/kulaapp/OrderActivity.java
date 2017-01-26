package appr.kulaf.com.kulaapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    private ImageView ims;
    private TextView desc;
    private String url = "http://192.168.40.215/kulafine/scripts/order.php";
    Button btn_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ims = (ImageView)findViewById(R.id.it_img);
        desc = (TextView)findViewById(R.id.it_desc);
        btn_order = (Button)findViewById(R.id.btn);

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order_Item();
            }
        });

        loadData();

    }

    public void loadData(){

        Picasso.with(getApplicationContext())
                .load(getIntent().getStringExtra("im"))
                .resize(350,250).into(ims);
        desc.setText(getIntent().getStringExtra("desc"));

    }

    public void Order_Item(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Placing your Order...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String name = getIntent().getStringExtra("name");
                String price = getIntent().getStringExtra("price");

                Map<String, String> params = new HashMap<String, String>();

                params.put("name",name);
                params.put("price",price);

                return params;
            }
        };

        Server.getServer_instance(getApplicationContext()).addRequest(stringRequest);

    }
}

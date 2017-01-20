package appr.kulaf.com.kulaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class Login extends AppCompatActivity {
    public EditText username;
    public EditText password;
    public Button login;
    public  String Url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginbtn);

    }
    public void login(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            try {
                String name = response.getString("result");

                if (name == "200"){
                    Toast.makeText(getApplicationContext(),"successful loogein",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"fail to gin",Toast.LENGTH_LONG).show();
                }

            }catch (JSONException E){

            }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Map<String, String> params = new Hashtable<String, String>();



    }
}

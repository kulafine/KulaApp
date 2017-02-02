package appr.kulaf.com.kulaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    public EditText telephone;
    public TextView countryCode;
    public EditText password;
    public Button login;
    public TextView regiser;
    public String number,Url = "google.com";
    public static final String KEY_TELEPHONE = "telephone";
    public static final String KEY_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        countryCode = (TextView)findViewById(R.id.CountryTv) ;
       telephone = (EditText)findViewById(R.id.mobilenumber);
        password = (EditText)findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        regiser  =(TextView)findViewById(R.id.registration);
        number = countryCode.getText().toString()+telephone.getText().toString();
        regiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            login();
            }
        });

    }
    public void login(){
        JSONObject jsonObject = new JSONObject();
        JSONObject header  = new JSONObject();
        try {
            header.put("action","login");
            header.put("user",jsonObject);
            jsonObject.put(KEY_PASSWORD,password.getText().toString().trim());
            jsonObject.put(KEY_TELEPHONE,number.trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,Url, header, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String reponse = response.toString();
                Toast.makeText(getApplicationContext(),reponse,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error while parsing "+error,Toast.LENGTH_LONG).show();
            }
        });


        Server.getServer_instance(getApplicationContext()).addRequest(jsonObjectRequest);


    }
}

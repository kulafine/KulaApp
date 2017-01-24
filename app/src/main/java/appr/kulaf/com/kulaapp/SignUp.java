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

public class SignUp extends AppCompatActivity {

    private static final String Url ="http://kongosms.com/android/register.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_PASSWORD="password";

  private   EditText username;
  private EditText email;
    private EditText password;
    public Button signup;
    public Button signin;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = (EditText)findViewById(R.id.username);
        email = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);

        signup = (Button)findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);
        textView = (TextView)findViewById(R.id.text);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
              startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerTheUser();
            }
        });



    }
    private void registerTheUser(){
        final String EDusername = username.getText().toString().trim();
        final  String EDpassword = password.getText().toString().trim();
        final String EDmail = email.getText().toString().trim();
       JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(KEY_USERNAME,EDusername);
            jsonObject.put(KEY_PASSWORD,EDpassword);
            jsonObject.put(KEY_EMAIL,EDmail);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,Url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                textView.setText(response.toString());
//               try {
//                   String result = response.getString("result").toString();
//                   if (result == "200"){
//                       Intent intent = new Intent(getApplicationContext(),Login.class);
//                       startActivity(intent);
//                   }else if(result == "0") {
//                       Toast.makeText(getApplicationContext(),"the user exist",Toast.LENGTH_LONG).show();
//                   }
//               }catch (JSONException e){
//                   Toast.makeText(getApplicationContext(),"error while parsing ",Toast.LENGTH_LONG).show();
//               }
//                da
//
//
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error while parsing "+error,Toast.LENGTH_LONG).show();
            }
       });
// {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> params = new HashMap<String, String>();
//                params.put(KEY_USERNAME, EDusername);
//                params.put(KEY_PASSWORD,EDpassword);
//                params.put(KEY_EMAIL,EDmail);
//                return params;
//            }
//        };
        Server.getServer_instance(getApplicationContext()).addRequest(jsonObjectRequest);


    }
}

package appr.kulaf.com.kulaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private static final String Url ="www.google.com ";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_PASSWORD="password";

  private   EditText username;
  private EditText email;
    private EditText password;
    public Button signup;
    public Button signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = (EditText)findViewById(R.id.username);
        email = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);

        signup = (Button)findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
    private void registerTheUser(){
        final String EDusername = username.getText().toString().trim();
        final  String EDpassword = password.getText().toString().trim();
        final String EDmail = email.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(SignUp.this,response,Toast.LENGTH_LONG).show();
            }
            },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUp.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
        }){
            @Override
            protected Map<String,String>getParams(){
                Map<String,String>Params = new HashMap<String, String>();
                Params.put(KEY_EMAIL,EDmail);
                Params.put(KEY_USERNAME,EDusername);
                Params.put(KEY_PASSWORD,EDpassword);
                return Params;
            }
        };



    }
}

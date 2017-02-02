package appr.kulaf.com.kulaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {

    private static final String Url = "http://kongosms.com/android/register.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_TELEPHONE = "telephone";
    public static final String KEY_PASSWORD = "password";


    private EditText username;
    private EditText telephone;
    private EditText password;
    public Button signup;
    public TextView login;
    private TextView countryCode;
    private String  number= null;
    Context c = this;
    ArrayAdapter<String> itemsAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText)findViewById(R.id.username);
        telephone = (EditText) findViewById(R.id.mobilenumber);
        password = (EditText) findViewById(R.id.password);
        countryCode = (TextView) findViewById(R.id.CountryTv) ;
        signup = (Button)findViewById(R.id.signup);
        login = (TextView)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

        countryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(c);
                View mview = inflater.inflate(R.layout.country_picker_dialog, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(c);
                dialog.setView(mview);
                final EditText userInputDialogEditText = (EditText) mview.findViewById(R.id.userInputDialog);
                final ListView countryList = (ListView)mview.findViewById(R.id.list) ;
             itemsAdapter =
                        new ArrayAdapter<String>(c, android.R.layout.simple_list_item_1, CountryList.getCountry());
                countryList.setAdapter(itemsAdapter);
                userInputDialogEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        SignUp.this.itemsAdapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                dialog
                        .setCancelable(true);


                final AlertDialog alertDialogAndroid = dialog.create();
                alertDialogAndroid.show();
            countryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String code = CountryList.country[i].toString();

                   countryCode.setText(code);
                    alertDialogAndroid.dismiss();
                }
            });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
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
        number = countryCode.getText().toString()+telephone.getText();
        final String EDphone  = number;
       JSONObject jsonObject = new JSONObject();
        JSONObject header  = new JSONObject();
        try {
            header.put("action","register");
            header.put("user",jsonObject);
            jsonObject.put(KEY_USERNAME,EDusername);
            jsonObject.put(KEY_PASSWORD,EDpassword);
            jsonObject.put(KEY_TELEPHONE,EDphone);
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

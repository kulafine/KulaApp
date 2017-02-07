package appr.kulaf.com.kulaapp;

import android.app.ProgressDialog;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity {


    public static final String KEY_USERNAME = "name";
    public static final String KEY_TELEPHONE = "telephone";
    public static final String KEY_PASSWORD = "password";


    private EditText username;
    private EditText telephone;
    private EditText password;
    public Button signup;
    public TextView login;
    private TextView countryCode;
    private String  number= null;
    ProgressBar progressBar;

    Context c = this;
    ArrayAdapter<String> itemsAdapter;
Constant constant;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = (EditText)findViewById(R.id.username) ;
        countryCode = (TextView)findViewById(R.id.CountryCode) ;
        telephone = (EditText)findViewById(R.id.number);
        password = (EditText)findViewById(R.id.passwordS) ;

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
                    String code = CountryList.code[i].toString();
                    alertDialogAndroid.dismiss();
                }
            });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               registerTheUser();
              //  Toast.makeText(getApplicationContext(),"the button is ok",Toast.LENGTH_LONG).show();
            }
        });



    }
    private void registerTheUser(){

        JSONObject parent  =  new JSONObject();
        JSONObject inner = new JSONObject();
        number = countryCode.getText().toString()+telephone.getText().toString().toString();
        try {
            parent.put("operation","register");
            parent.put("user",inner);
            inner.put(KEY_USERNAME,username.getText().toString().trim());
            inner.put(KEY_PASSWORD,password.getText().toString().trim());
            inner.put(KEY_TELEPHONE,number.trim());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Registering...");
        dialog.show();

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST,Constant.BaseURL, parent, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    dialog.dismiss();
                    Constant.Result = response.getString(Constant.Response);

                }catch (JSONException E){

                }
                Toast.makeText(getApplicationContext(),Constant.Result,Toast.LENGTH_LONG).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"errror"+error,Toast.LENGTH_LONG).show();

            }
        });
        Server.getServer_instance(getApplicationContext()).addRequest(objectRequest);

    }
}

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

public class SignUp extends AppCompatActivity {

    private static final String Url = "http://kongosms.com/android/register.php";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";


    private EditText username;
    private EditText email;
    private EditText password;
    public Button signup;
    public TextView login;
    private TextView textView;
    Context c = this;
    ArrayAdapter<String> itemsAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        username = (EditText)findViewById(R.id.username);
//        email = (EditText) findViewById(R.id.mail);
//        password = (EditText) findViewById(R.id.password);
//
//        signup = (Button)findViewById(R.id.signup);
        login = (TextView)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        textView = (TextView)findViewById(R.id.CountryTv);
        textView.setOnClickListener(new View.OnClickListener() {
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

                    textView.setText(code);
                    alertDialogAndroid.dismiss();
                }
            });
            }
        });
//
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),Login.class);
//              startActivity(intent);
//            }
//        });
//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                registerTheUser();
//            }
//        });
//
//
//
//    }
//    private void registerTheUser(){
//        final String EDusername = username.getText().toString().trim();
//        final  String EDpassword = password.getText().toString().trim();
//        final String EDmail = email.getText().toString().trim();
//       JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put(KEY_USERNAME,EDusername);
//            jsonObject.put(KEY_PASSWORD,EDpassword);
//            jsonObject.put(KEY_EMAIL,EDmail);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,Url, jsonObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                textView.setText(response.toString());
////               try {
////                   String result = response.getString("result").toString();
////                   if (result == "200"){
////                       Intent intent = new Intent(getApplicationContext(),Login.class);
////                       startActivity(intent);
////                   }else if(result == "0") {
////                       Toast.makeText(getApplicationContext(),"the user exist",Toast.LENGTH_LONG).show();
////                   }
////               }catch (JSONException e){
////                   Toast.makeText(getApplicationContext(),"error while parsing ",Toast.LENGTH_LONG).show();
////               }
////                da
////
////
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(),"error while parsing "+error,Toast.LENGTH_LONG).show();
//            }
//       });
//// {
////            @Override
////            protected Map<String, String> getHeaders() throws AuthFailureError {
////
////                Map<String, String> params = new HashMap<String, String>();
////                params.put(KEY_USERNAME, EDusername);
////                params.put(KEY_PASSWORD,EDpassword);
////                params.put(KEY_EMAIL,EDmail);
////                return params;
////            }
////        };
//        Server.getServer_instance(getApplicationContext()).addRequest(jsonObjectRequest);


    }
}

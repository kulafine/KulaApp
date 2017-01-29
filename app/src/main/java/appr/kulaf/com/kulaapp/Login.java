package appr.kulaf.com.kulaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    public EditText username;
    public EditText password;
    public Button login;
    public TextView regiser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.mobilenumber);
        password = (EditText)findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        regiser  =(TextView)findViewById(R.id.registration);
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
                ProgressDialog pDialog = new ProgressDialog(getApplicationContext());
                pDialog.setMessage("Loading...");
                pDialog.setCancelable(true);
                pDialog.show();
            }
        });

    }
}

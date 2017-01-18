package appr.kulaf.com.kulaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    private static final String Url =" ";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_PASSWORD="password";

    public EditText username;
    public EditText email;
    public EditText password;
    public Button signup;
    public Button signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }
}

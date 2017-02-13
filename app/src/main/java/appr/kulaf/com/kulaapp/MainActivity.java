package appr.kulaf.com.kulaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    Button bsign,bfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bsign = (Button)findViewById(R.id.btnsign);
        bfood = (Button)findViewById(R.id.factivity);

        bsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SignUp.class);
                startActivity(i);
            }
        });

        bfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent k = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(k);

            }
        });


    }
}

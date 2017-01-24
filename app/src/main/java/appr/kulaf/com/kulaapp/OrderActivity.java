package appr.kulaf.com.kulaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OrderActivity extends AppCompatActivity {

    private ImageView ims;
    private TextView words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ims = (ImageView)findViewById(R.id.it_img);
        words = (TextView)findViewById(R.id.it_desc);

        Picasso.with(getApplicationContext())
               .load(getIntent().getStringExtra("im"))
               .resize(360,200).into(ims);
        words.setText(getIntent().getStringExtra("desc"));

    }
}

package appr.kulaf.com.kulaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    private ImageView im;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        im = (ImageView)findViewById(R.id.it_img);
        im.setImageResource(getIntent().getIntExtra("im",00));
    }
}

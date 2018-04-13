package farhadarts.birdwatching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class BirdDetailActivity extends AppCompatActivity {

    private Bird bird;
    private EditText latitude, longitude, nameDanish, nameEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_detail);


        Intent intent = getIntent();
        bird = (Bird) intent.getSerializableExtra("BIRD");

        TextView headingView = findViewById(R.id.bird_heading_textview);
        headingView.setText("Bird Id=" + bird.getBirdId());

        latitude = findViewById(R.id.bird_latitude_edittext);
        latitude.setText(bird.getBirdLatitude() + "");

        longitude = findViewById(R.id.bird_longitude1_edittext);
        longitude.setText(bird.getBirdLongitude() + "");

        nameDanish = findViewById(R.id.bird_nameDanish_edittext);
        nameDanish.setText(bird.getBirdNameDanish());

        nameEnglish = findViewById(R.id.bird_nameEnglish_edittext);
        nameEnglish.setText(bird.getBirdNameEnglish());
    }
}


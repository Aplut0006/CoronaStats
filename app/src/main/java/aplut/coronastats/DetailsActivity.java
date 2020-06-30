package aplut.coronastats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private TextView textViewCases,textViewRecovered,textViewDeaths,textViewActive,
             textViewTodayCases,textViewTodayRecovered,textViewTodayDeaths,
                textViewCritical,textViewTests;
    private int positionCountry;
    private ImageView imageView;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textViewActive = findViewById(R.id.tvActive);
        textViewCases = findViewById(R.id.tvCases);
        textViewDeaths = findViewById(R.id.tvDeaths);
        textViewRecovered = findViewById(R.id.tvRecovered);
        textViewTodayCases = findViewById(R.id.tvTodayCases);
        textViewTodayRecovered = findViewById(R.id.tvTodayRecovered);
        textViewTodayDeaths = findViewById(R.id.tvTodayDeaths);
        textViewCritical = findViewById(R.id.tvCritical);
        textViewTests = findViewById(R.id.tvTests);
        imageView = findViewById(R.id.imageViewFlag);
        textView = findViewById(R.id.textCountryName);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Country Stats");

        positionCountry = getIntent().getIntExtra("position",0);


        Picasso.get().load(AffectedCountries.countryModelList.get(positionCountry)
        .getFlag()).into(imageView);
        textView.setText(AffectedCountries.countryModelList.get(positionCountry)
        .getCountry());

        textViewCases.setText(AffectedCountries.countryModelList.get(positionCountry)
                .getCases());
        textViewRecovered.setText(AffectedCountries.countryModelList.get(positionCountry)
                .getRecovered());

        textViewDeaths.setText(AffectedCountries.countryModelList.get(positionCountry)
                .getDeaths());

        textViewActive.setText(AffectedCountries.countryModelList.get(positionCountry)
                .getActive());
        textViewTodayCases.setText(AffectedCountries.countryModelList.get(positionCountry)
                .getTodayCases());
        textViewTodayRecovered.setText(AffectedCountries.countryModelList.get(positionCountry)
                .getTodayRecovered());
        textViewTodayDeaths.setText(AffectedCountries.countryModelList.get(positionCountry)
                .getTodayDeaths());
        textViewCritical.setText(AffectedCountries.countryModelList.get(positionCountry)
                .getCritical());
        textViewTests.setText(AffectedCountries.countryModelList.get(positionCountry)
                .getTests());

    }
}
package aplut.coronastats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView textViewCases, textViewRecovered,textViewDeaths,textViewActive
            ,todayCases,todayRecovered,todayDeaths;
    private ScrollView scrollView;
    private PieChart pieChart;
    private SimpleArcLoader simpleArcLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewActive = findViewById(R.id.tvActive);
        textViewCases = findViewById(R.id.tvCases);
        textViewDeaths = findViewById(R.id.tvDeaths);
        textViewRecovered = findViewById(R.id.tvRecovered);
        todayCases = findViewById(R.id.tvTodayCases);
        todayRecovered = findViewById(R.id.tvTodayRecovered);
        todayDeaths = findViewById(R.id.tvTodayDeaths);
        scrollView = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.piechart);
        simpleArcLoader = findViewById(R.id.arcLoader);
        fetchData();

    }

    private void fetchData() {
        String url = "https://disease.sh/v2/all";
        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            textViewCases.setText(jsonObject.getString("cases"));
                            textViewRecovered.setText(jsonObject.getString("recovered"));
                            textViewDeaths.setText(jsonObject.getString("deaths"));
                            textViewActive.setText(jsonObject.getString("active"));
                            todayCases.setText(jsonObject.getString("todayCases"));
                            todayRecovered.setText(jsonObject.getString("todayRecovered"));
                            todayDeaths.setText(jsonObject.getString("todayDeaths"));

                            pieChart.addPieSlice(new PieModel("cases",
                                    Integer.parseInt(textViewCases.getText().toString()),
                                    Color.parseColor("#FF9800")));
                            pieChart.addPieSlice(new PieModel("recovered",
                                    Integer.parseInt(textViewRecovered.getText().toString()),
                                    Color.parseColor("#8BC34A")));
                            pieChart.addPieSlice(new PieModel("deaths",
                                    Integer.parseInt(textViewDeaths.getText().toString()),
                                    Color.parseColor("#F44336")));
                            pieChart.addPieSlice(new PieModel("active",
                                    Integer.parseInt(textViewActive.getText().toString()),
                                    Color.parseColor("#03A9F4")));
                            pieChart.startAnimation();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.getCache().clear();
        requestQueue.add(request);



    }
    public void trackCountries(View view){
        Intent intent = new Intent(MainActivity.this,AffectedCountries.class);
        startActivity(intent);
    }
}
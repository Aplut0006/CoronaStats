package aplut.coronastats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountries extends AppCompatActivity {

    public static List<CountryModel> countryModelList = new ArrayList<>();
    private SimpleArcLoader simpleArcLoader;
    private CountryAdapter adapter;
    private CountryModel model;
    private EditText editTextSearch;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_affected_countries);
        listView = findViewById(R.id.listView);
        simpleArcLoader = findViewById(R.id.arcLoader);
        editTextSearch = findViewById(R.id.editTextSearch);
        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AffectedCountries.this,DetailsActivity.class)
                        .putExtra("position",position);
                startActivity(intent);
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fetchData();

    }
    private void fetchData() {
        String url = "https://disease.sh/v2/countries";
        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for(int i=0;i<=jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String countryName = jsonObject.getString("country");
                                String cases = jsonObject.getString("cases");
                                String deaths = jsonObject.getString("deaths");
                                String recovered = jsonObject.getString("recovered");
                                String active = jsonObject.getString("active");
                                String todayCases = jsonObject.getString("todayCases");
                                String todayRecovered = jsonObject.getString("todayRecovered");
                                String todayDeaths = jsonObject.getString("todayDeaths");
                                String critical = jsonObject.getString("critical");
                                String tests = jsonObject.getString("tests");

                                JSONObject jsonObject1 = jsonObject.getJSONObject("countryInfo");
                                String countryFlag = jsonObject1.getString("flag");

                               model = new CountryModel(countryName,cases,deaths,recovered,active,countryFlag
                               ,todayCases,todayRecovered,todayDeaths,critical,tests);
                               countryModelList.add(model);
                               adapter = new CountryAdapter(AffectedCountries.this,countryModelList);
                               listView.setAdapter(adapter);

                               simpleArcLoader.stop();
                               simpleArcLoader.setVisibility(View.GONE);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(AffectedCountries.this);
        requestQueue.getCache().clear();
        requestQueue.add(request);



    }
}
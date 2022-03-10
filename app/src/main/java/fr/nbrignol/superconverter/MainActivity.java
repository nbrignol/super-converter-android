package fr.nbrignol.superconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {

    protected float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rate = 0.5f;
        Log.d("CURRENCY", "Starting...");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://geoplugin.net/json.gp?base_currency=USD";


        // Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("CURRENCY", response.toString());

                        try {
                            Double rateDouble = response.getDouble("geoplugin_currencyConverter");
                            rate = 1/ rateDouble.floatValue();

                            TextView label = findViewById(R.id.rateLabel);
                            label.setText(String.format("%.2f", rate));
                        } catch(JSONException e) {
                            Log.e("CURRENCY", "Impossible de récup le rate.");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("CURRENCY", "Error !");
                Log.d("CURRENCY", error.toString());
            }
        });

        // Add the request to the RequestQueue.

        queue.add(request);



        Button convertButton = findViewById(R.id.conversionButton);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView resultLabel = findViewById(R.id.conversionResult);

                EditText input = findViewById(R.id.userInput);
                String stringInput = input.getText().toString();
                float floatInput = Float.parseFloat(stringInput);
                float result = floatInput * rate;

                resultLabel.setText( String.format("%.2f", result) );

            }
        });

    }

}
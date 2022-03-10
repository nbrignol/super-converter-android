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

    protected Converter converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        converter = new GeoPluginConverter();
        converter.init(this);

        Button convertButton = findViewById(R.id.conversionButton);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView resultLabel = findViewById(R.id.conversionResult);

                EditText input = findViewById(R.id.userInput);
                String stringInput = input.getText().toString();
                float floatInput = Float.parseFloat(stringInput);

                float result = converter.convert(floatInput);
                resultLabel.setText( String.format("%.2f", result) );

                TextView rateLabel = findViewById(R.id.rateLabel);
                rateLabel.setText( String.format("%.2f", converter.getRate()) );
            }
        });

    }

}
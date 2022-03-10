package fr.nbrignol.superconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements ConverterListener {

    protected Converter converter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        converter = new GeoPluginConverter();
        converter.setListener(this);
        converter.init(this);

        Button convertButton = findViewById(R.id.conversionButton);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView resultLabel = findViewById(R.id.conversionResult);

                float result = converter.convert( getUserInput() );
                resultLabel.setText( String.format("%.2f", result) );


            }
        });

    }

    protected float getUserInput(){
        EditText input = findViewById(R.id.userInput);
        String stringInput = input.getText().toString();
        float floatInput = Float.parseFloat(stringInput);

        return floatInput;
    }

    @Override
    public void onRateUpdated(float currentRate) {
        TextView rateLabel = findViewById(R.id.rateLabel);
        rateLabel.setText( String.format("%.2f", currentRate) );
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
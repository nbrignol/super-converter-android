package fr.nbrignol.superconverter;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class GeoPluginConverter extends Converter {

    protected String url = "http://geoplugin.net/json.gp?base_currency=USD";
    protected float rate = 0;

    @Override
    public void init(Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("CURRENCY", response.toString());

                        try {
                            Double rateDouble = response.getDouble("geoplugin_currencyConverter");
                            rate = 1/ rateDouble.floatValue();

                            if (listener != null) {
                                listener.onRateUpdated(rate);
                            }


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

        queue.add(request);
    }

    @Override
    public float getRate() {
        return rate;
    }

    @Override
    public float convert(float value) {
        return value * rate;
    }
}

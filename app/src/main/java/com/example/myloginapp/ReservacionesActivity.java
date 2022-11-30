package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.security.ProviderInstaller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ReservacionesActivity extends AppCompatActivity {

    ListView lstReservaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservaciones);
        this.inicializarControles();
        this.cargarReservaciones();
    }

    private void inicializarControles() {
        lstReservaciones = (ListView) findViewById(R.id.lstReservaciones);
    }

    private void cargarListViewTemplate(List<Reservacion> reservaciones) {
        ListViewAdapterReservaciones adapter = new ListViewAdapterReservaciones(this, R.layout.reservaciones_listview_template, reservaciones);
        lstReservaciones.setAdapter(adapter);
    }

    private void cargarReservaciones() {
        List<Reservacion> reservacionesList = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("http://10.0.2.2:8000/reservaciones/%1$s", id);
        System.out.println(url);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                String codSalon = obj.getString("cod_salon");
                                String tiempoInicio = obj.getString("tiempo_inicio");
                                String tiempoFinal = obj.getString("tiempo_final");

                                reservacionesList.add(new Reservacion(codSalon, tiempoInicio, tiempoFinal));
                            }

                            cargarListViewTemplate(reservacionesList);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();
                    }
                });

        queue.add(jsonArrayRequest);
    }
}
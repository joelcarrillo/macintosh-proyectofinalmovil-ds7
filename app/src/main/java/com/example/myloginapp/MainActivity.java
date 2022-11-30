package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView txtCorreo = (TextView) findViewById(R.id.correo);
        TextView txtPassword = (TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = txtCorreo.getText().toString();
                String password = txtPassword.getText().toString();

                if (correo.equals("") || password.equals("")) {
                    Toast.makeText(MainActivity.this, "Rellena todos los campos necesarios", Toast.LENGTH_LONG).show();
                    return;
                }

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                String url = String.format("http://10.0.2.2:8000/login?correo=%1$s&pass=%2$s", correo, password);
                System.out.println(url);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println(response);
                                if (response.has("error")) {
                                    Toast.makeText(MainActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_LONG).show();
                                } else {
                                    Intent i = new Intent(MainActivity.this, ReservacionesActivity.class);
                                    try {
                                        i.putExtra("id", response.getString("id_usuario"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    startActivity(i);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                error.printStackTrace();
                            }
                        });

                queue.add(jsonObjectRequest);
            }
        });


    }
}
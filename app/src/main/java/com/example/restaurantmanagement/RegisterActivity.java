package com.example.restaurantmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private EditText email, password, name, address;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String nameText = name.getText().toString();
                String addressText = address.getText().toString();

                if (emailText.isEmpty()) {
                    email.setError("Email is required");
                    return;
                }
                if (passwordText.isEmpty()) {
                    password.setError("Password is required");
                    return;
                }
                if (passwordText.length() < 6) {
                    password.setError("Password must be at least 6 characters");
                    return;
                }
                if (nameText.isEmpty()) {
                    name.setError("Name is required");
                    return;
                }
                if (addressText.isEmpty()) {
                    address.setError("Address is required");
                    return;
                }

                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                String url = "http://192.168.1.13:8080/api/v1/register";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Enregistrement avec succes")) {
                                    email.setText(null);
                                    name.setText(null);
                                    address.setText(null);
                                    password.setText(null);
                                    Toast.makeText(RegisterActivity.this, "Enregistrement avec succes", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Echec d'enregistrement "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}


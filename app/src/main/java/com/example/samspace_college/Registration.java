package com.example.samspace_college;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.samspace_college.databinding.ActivityRegistrationBinding;

import org.json.JSONObject;

import Validators.EmailValidator;
import Validators.PwordValidator;

public class Registration extends AppCompatActivity {

    String regURL = "https://ayomideandroidtrainingapi-production.up.railway.app/api/v1/admin";
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fname = binding.fname.getText().toString().trim();
                final String lname = binding.lname.getText().toString().trim();
                final String email = binding.email.getText().toString().trim();
                final String pword = binding.pword.getText().toString().trim();

                if (fname.isEmpty()|| lname.isEmpty()||email.isEmpty()||pword.isEmpty()){
                    binding.thanks.setText("All fields are required");
                    binding.thanks.setVisibility(View.VISIBLE);
                }else{
                    Register();
                }
            }
        });

    }
    private void Register(){
        final String fname = binding.fname.getText().toString().trim();
        final String lname = binding.lname.getText().toString().trim();
        final String email = binding.email.getText().toString().trim();
        final String pword = binding.pword.getText().toString().trim();

        if(!EmailValidator.isEmailValid(email)){
            binding.thanks.setText("Invalid email");
            binding.thanks.setVisibility(View.VISIBLE);
        }
        if(!PwordValidator.isPwordValid(pword)){
            binding.thanks.setText("Invalid password. Please check the requirements.");
            binding.thanks.setVisibility(View.VISIBLE);
        }
        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("firstName", fname);
            jsonObject.put("lastName", lname);
            jsonObject.put("email", email);
            jsonObject.put("password", pword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, regURL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                binding.thanks.setText("Registration unsuccessful, try again");
                binding.thanks.setVisibility(View.VISIBLE);

            }
        });
        queue.add(jsonObjectRequest);
    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
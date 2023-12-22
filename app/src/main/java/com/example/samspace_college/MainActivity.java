package com.example.samspace_college;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.samspace_college.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import Validators.EmailValidator;
import Validators.PwordValidator;

public class MainActivity extends AppCompatActivity {

    String loginURL = "https://app-7c3cd652-938a-4fc2-b694-24b8228e1f06.cleverapps.io/api/v1/admin/login";
    ActivityMainBinding binding;
    
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        editor = sharedPreferences.edit();




        binding.signon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.email.getText().toString().trim();
                String pword = binding.pword.getText().toString().trim();

                if(email.isEmpty()||pword.isEmpty()){
                    binding.procid.setText("All fields are required");
                    binding.procid.setVisibility(View.VISIBLE);
                }else{
                    signin();
                }
            }
        });

    }
    public void signin(){

        String email = binding.email.getText().toString().trim();
        String pword = binding.pword.getText().toString().trim();

        if(!EmailValidator.isEmailValid(email)){
            binding.procid.setText("Invalid Email");
            binding.procid.setVisibility(View.VISIBLE);
            return;
        }
        if(!PwordValidator.isPwordValid(pword)){
            binding.procid.setText("Incorrect Password");
            binding.procid.setVisibility(View.VISIBLE);
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", email);
            jsonObject.put("password", pword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, loginURL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String token;
                try {
                    token = jsonObject.getString("token");
                    editor.putString("token", token);
                    editor.commit();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                binding.procid.setText("LOGIN SUCCESSFUL");
                binding.procid.setVisibility(View.VISIBLE);

                startActivity(new Intent(getApplicationContext(), Dashboard.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                binding.procid.setText("LOGIN UNSUCCESSFUL, TRY AGAIN.");
                binding.procid.setVisibility(View.VISIBLE);
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void signup(View view) {
        startActivity(new Intent(getApplicationContext(), Registration.class));
    }
}
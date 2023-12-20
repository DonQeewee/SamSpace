package com.example.samspace_college;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.samspace_college.databinding.ActivityDashboardBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Dashboard extends AppCompatActivity {

    String userDetailsURL = "https://app-7c3cd652-938a-4fc2-b694-24b8228e1f06.cleverapps.io/api/v1/admin/userdetails";

    ActivityDashboardBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        getUserDetails();

    }

    public void getUserDetails() {

        RequestQueue q = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, userDetailsURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String fname, lname, email, pword;
                try {
                    fname = jsonObject.getString("firstName");
                    lname = jsonObject.getString("lastName");
                    email = jsonObject.getString("email");
                    pword = jsonObject.getString("password");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                binding.welcome.setText("Welcome, " + fname + lname);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> details = new HashMap<>();
                details.put("Authorization", "Bearer " + token);
                return details;
            }
        };
        q.add(jsonObjectRequest);

    }

    public void logout(View view) {
        editor.clear();
        editor.commit();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void course(View view) {
        startActivity(new Intent(getApplicationContext(), Courses.class));
    }
}
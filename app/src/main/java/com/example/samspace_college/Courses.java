package com.example.samspace_college;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.samspace_college.databinding.ActivityCoursesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Courses extends AppCompatActivity {
    ActivityCoursesBinding binding;
    final String courseURL = "https://ayomide-api.cleverapps.io/api/v1/course";
    List<String> courses = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCoursesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getCourses();

        binding.courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Courses.this, "This is " + courses.get(position) + " course", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getCourses() {

        RequestQueue q = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, courseURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                String courseName;

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        courseName = item.getString("courseName");
                        courses.add(courseName);
                    }
                    ArrayAdapter<String> allCourses = new ArrayAdapter<>(getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            courses
                    );
                    binding.courseList.setAdapter(allCourses);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("myError", volleyError.toString());
            }
        });
        q.add(jsonArrayRequest);

    }


}
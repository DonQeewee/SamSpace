package com.example.samspace_college;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.samspace_college.databinding.ActivityStudentsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Students extends AppCompatActivity {

    final String studentsURL = "https://app-7c3cd652-938a-4fc2-b694-24b8228e1f06.cleverapps.io/api/v1/student";

    ActivityStudentsBinding binding;
    List<String> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.students.setLayoutManager(new LinearLayoutManager(this));
        binding.students.setAdapter(new MyAdapter(getApplicationContext(), ));

        };

    public void getStudents() {

        RequestQueue q = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, studentsURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                String name, email, grade;

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        name = item.getString("firstName" + "lastname");
                        email = item.getString("email");
                        grade = item.getString("level");
                        students.add(name);
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
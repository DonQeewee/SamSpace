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

    final String studentsURL = "https://ayomide-api.cleverapps.io/api/v1/student";
    final String teachersURL = "https://ayomide-api.cleverapps.io/api/v1/teacher";
    final String adminURL = "https://ayomide-api.cleverapps.io/api/v1/admin";

    ActivityStudentsBinding binding;
    List<String> students = new ArrayList<>();

    List<sd> sd = new ArrayList<>();
    List<Admin> admin = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getStudents();
        getTeachers();
        getAdmins();

    }

    ;

    public void getStudents() {

        RequestQueue q = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, studentsURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                String fname, lname, email, grade, name;

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        fname = item.getString("firstName");
                        lname = item.getString("lastName");
                        email = item.getString("email");
                        grade = item.getString("level");
                        name = fname + " " + lname;

                        sd.add(new sd(name, email, grade));

                    }
                    binding.students.setAdapter(new MyAdapter(getApplicationContext(), sd));


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

    public void getTeachers() {

        RequestQueue q = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, teachersURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                String fname, lname, email, grade, name;

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        fname = item.getString("firstName");
                        lname = item.getString("lastName");
                        email = item.getString("email");
                        grade = item.getString("level");
                        name = fname + " " + lname;

                        sd.add(new sd(name, email, grade));

                    }
                    binding.teachers.setAdapter(new MyAdapter(getApplicationContext(), sd));


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

    public void getAdmins() {

        RequestQueue q = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, adminURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                String fname, lname, role, email, name;

                try {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        fname = item.getString("firstName");
                        lname = item.getString("lastName");
                        role = item.getString("role");
                        email = item.getString("email");
                        name = fname + " " + lname;

                        admin.add(new Admin(name, email, role));

                    }
                    binding.admin.setAdapter(new AdminAdapter(getApplicationContext(), admin));


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
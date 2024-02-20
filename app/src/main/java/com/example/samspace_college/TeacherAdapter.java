package com.example.samspace_college;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<MyViewHolder>{

    Context context;
    List<sd> sd;

    public TeacherAdapter(Context context, List<com.example.samspace_college.sd> sd) {
        this.context = context;
        this.sd = sd;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.sd_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(sd.get(position).getName());
        holder.email.setText(sd.get(position).getEmail());
        holder.grade.setText(sd.get(position).getGrade());

    }

    @Override
    public int getItemCount() {
        return sd.size();
    }
}

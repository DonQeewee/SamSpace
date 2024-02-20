package com.example.samspace_college;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<MyViewHolder>{

    Context context;
    List<Admin> admin;

    public AdminAdapter(Context context, List<Admin> admin) {
        this.context = context;
        this.admin = admin;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.admin_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(admin.get(position).getName());
        holder.email.setText(admin.get(position).getEmail());
        holder.role.setText(admin.get(position).getRole());

    }

    @Override
    public int getItemCount() {
        return admin.size();
    }
}

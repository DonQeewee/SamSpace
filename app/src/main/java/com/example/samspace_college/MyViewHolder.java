package com.example.samspace_college;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView pic;
    TextView name, email, grade;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        pic = itemView.findViewById(R.id.pic);
        name = itemView.findViewById(R.id.name);
        email = itemView.findViewById(R.id.email);
        grade = itemView.findViewById(R.id.grade);
    }
}

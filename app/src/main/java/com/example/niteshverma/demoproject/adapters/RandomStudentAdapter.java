package com.example.niteshverma.demoproject.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.niteshverma.demoproject.DemoApplication;
import com.example.niteshverma.demoproject.R;
import com.example.niteshverma.demoproject.models.Result;

import java.util.ArrayList;
import java.util.List;

public class RandomStudentAdapter extends RecyclerView.Adapter<RandomStudentAdapter.RandomStudentViewHolder>{

    private List<Result> resultList = new ArrayList<>();

    public RandomStudentAdapter() {
    }

    @NonNull
    @Override
    public RandomStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_student,
                parent, false);
        return new RandomStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomStudentViewHolder holder, int i) {
        Result result = resultList.get(i);
        holder.name.setText(String.format("%s %s", result.getName().getFirst(),
                result.getName().getLast()));
        holder.age.setText(String.format("Age: %d", result.getDob().getAge()));
        holder.email.setText(result.getEmail());

        if(result.getPicture() != null && result.getPicture().getLarge() != null){
            Log.e("Adapter", "Index:" + i + " URL: " + result.getPicture().getLarge());
            DemoApplication.get().getComponent().getPicasso().load(result.getPicture().getLarge()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setItems(List<Result> results) {
        resultList = results;
        notifyDataSetChanged();
    }

    public static class RandomStudentViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public ImageView imageView;
        public TextView age;
        public TextView email;

        public RandomStudentViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
            age = itemView.findViewById(R.id.age);
            email = itemView.findViewById(R.id.email);
        }
    }

}

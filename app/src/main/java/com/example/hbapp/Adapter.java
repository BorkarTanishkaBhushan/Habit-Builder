package com.example.hbapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHabitViewHolder> {

    Context context;
    Activity activity;
    List<Habit> habitList;

    public Adapter(Context context, Activity activity, List<Habit> habitList) {
        this.context = context;
        this.activity = activity;
        this.habitList = habitList;
    }

    @NonNull
    @Override
    public MyHabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_habit, parent, false);
        return new MyHabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHabitViewHolder holder, int position) {
        holder.title.setText(habitList.get(position).getTitle());
        holder.description.setText(habitList.get(position).getDescription());
        holder.hour.setText(habitList.get(position).getHour());
        holder.min.setText(habitList.get(position).getMin());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateHabit.class);

                intent.putExtra("title", habitList.get(position).getTitle());
                intent.putExtra("description", habitList.get(position).getDescription());
                intent.putExtra("id", habitList.get(position).getId());
                intent.putExtra("hour", habitList.get(position).getHour());
                intent.putExtra("min", habitList.get(position).getMin());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public class MyHabitViewHolder extends RecyclerView.ViewHolder{
        TextView title, description, hour, min;
        RelativeLayout layout;
        public MyHabitViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.HabitTitle);
            hour = itemView.findViewById(R.id.hourSet);
            min = itemView.findViewById(R.id.minSet);
            description = itemView.findViewById(R.id.habitDesc);
            layout = itemView.findViewById(R.id.habit_card_layout);
        }
    }
}

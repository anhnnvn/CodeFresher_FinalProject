package com.example.myapplication3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.model.CategoryRoom;
import com.example.myapplication3.R;

import java.util.ArrayList;

public class RoomCategoryAdapter extends RecyclerView.Adapter<RoomCategoryAdapter.ViewHolder> {

    private ArrayList<CategoryRoom> categoryRooms;
    private Callback callback;

    public RoomCategoryAdapter(ArrayList<CategoryRoom> categoryRooms, Callback callback) {
        this.categoryRooms = categoryRooms;
        this.callback = callback;
    }

    public void updateData(ArrayList<CategoryRoom> data) {
        this.categoryRooms = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
            view = layoutInflater.inflate(R.layout.room_category_item, parent, false);
        return new RoomCategoryAdapter.ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        View view = holder.getView();
        final CategoryRoom categoryRoom = categoryRooms.get(position);

        ImageView imageView;
        TextView name;

        imageView = view.findViewById(R.id.category_image);
        name = view.findViewById(R.id.category_name);

        name.setText(categoryRoom.getName());
        imageView.setImageResource(categoryRoom.getImage());
    }

    @Override
    public int getItemCount() {
        return categoryRooms.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        ViewHolder(View view) {
            super(view);
            this.view = view;
        }

        View getView() {
            return view;
        }
    }

    public interface Callback {
        void onItemClick(int index, RoomCategoryAdapter roomCategoryAdapter);
    }
}

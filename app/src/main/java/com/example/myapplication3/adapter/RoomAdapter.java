package com.example.myapplication3.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication3.R;
import com.example.myapplication3.model.RoomInformation;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private ArrayList<RoomInformation> roomArrayList;
    private Callback callback;
    private final int type;

    public RoomAdapter(ArrayList<RoomInformation> roomArrayList, Callback callback, int type) {
        this.roomArrayList = roomArrayList;
        this.callback = callback;
        this.type = type;
    }

    public void updateData(ArrayList<RoomInformation> data) {
        this.roomArrayList = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (type == 0) {
            view = layoutInflater.inflate(R.layout.room_item_normal, parent, false);
        } else {
            view = layoutInflater.inflate(R.layout.room_item_big, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        View view = holder.getView();
        final RoomInformation roomInformation = roomArrayList.get(position);

        TextView roomName;
        TextView roomPrice;
        TextView roomAddress;
        ImageView roomImage;
        ImageView favoriteImage;

        roomName = view.findViewById(R.id.room_name);
        roomPrice = view.findViewById(R.id.room_price);
        roomImage = view.findViewById(R.id.room_image);
        roomAddress = view.findViewById(R.id.room_address);
        favoriteImage = view.findViewById(R.id.favorite);

        roomName.setText(roomInformation.getRoomName());
        roomPrice.setText(roomInformation.getPrice());
        roomImage.setImageResource(roomInformation.getIconResId());
        roomAddress.setText(roomInformation.getRoomAddress());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onItemClick(position, roomInformation);
            }
        });
        favoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteImage.setImageResource(R.drawable.heart_fill );
                Log.e("Click Favorite", position + " ");
                callback.onFavoriteClick(position, roomInformation);
            }
        });

    }

    @Override
    public int getItemCount() {
        return roomArrayList.size();
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
        void onItemClick(int index, RoomInformation roomInformation);

        void onFavoriteClick(int index, RoomInformation roomInformation);
    }
}

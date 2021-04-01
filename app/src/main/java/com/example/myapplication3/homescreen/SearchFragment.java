package com.example.myapplication3.homescreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication3.api.HttpRequestMethod;
import com.example.myapplication3.api.HttpRequestTask;
import com.example.myapplication3.adapter.RoomAdapter;
import com.example.myapplication3.detailscreen.DetailActivity;
import com.example.myapplication3.NetWorkUtils;
import com.example.myapplication3.manager.PreferenceManager;
import com.example.myapplication3.R;
import com.example.myapplication3.model.RoomInformation;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements RoomAdapter.Callback{

    public static final String ROW_INDEX = "row_index";

    public SearchFragment() {
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getServerData();
    }

    private RoomAdapter roomAdapterSuggest;
    private RecyclerView recyclerViewSuggest;

    private void initView(View view) {

        recyclerViewSuggest = view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, RecyclerView.VERTICAL);
        recyclerViewSuggest.setLayoutManager(staggeredGridLayoutManager);
        roomAdapterSuggest = new RoomAdapter(new ArrayList<>(), this, 1);
        recyclerViewSuggest.setAdapter(roomAdapterSuggest);
    }

    private void getServerData() {
        String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=b1a6b9d8867fa058c1a2f803e6244b14";
        Log.e("TAG", "connect");

        HttpRequestTask task = new HttpRequestTask(HttpRequestMethod.GET, new HttpRequestTask.Callback() {
            @Override
            public void onSuccess(String result) {
                ArrayList<RoomInformation> arrayList = NetWorkUtils.jsonToRoomList(result);
                roomAdapterSuggest.updateData(arrayList);
                Log.e("TAG", result);
            }

            @Override
            public void onFailed(Exception error) {
                Log.e("TAG", "not connection");
                error.printStackTrace();
            }
        });
        task.execute(baseUrl);

    }


    @Override
    public void onItemClick(int index, RoomInformation roomInformation) {
        long rowID = PreferenceManager.saveRoomInformationToPreference(roomInformation, getActivity());
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(ROW_INDEX, rowID);
        startActivity(intent);
    }

    @Override
    public void onFavoriteClick(int index, RoomInformation roomInformation) {

    }
}
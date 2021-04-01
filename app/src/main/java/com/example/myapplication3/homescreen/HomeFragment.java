package com.example.myapplication3.homescreen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication3.api.HttpRequestMethod;
import com.example.myapplication3.api.HttpRequestTask;
import com.example.myapplication3.adapter.RoomAdapter;
import com.example.myapplication3.manager.PreferenceManager;
import com.example.myapplication3.adapter.RoomCategoryAdapter;
import com.example.myapplication3.detailscreen.DetailActivity;
import com.example.myapplication3.model.CategoryRoom;
import com.example.myapplication3.NetWorkUtils;
import com.example.myapplication3.R;
import com.example.myapplication3.model.RoomInformation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements RoomAdapter.Callback, RoomCategoryAdapter.Callback, LocationListener {

    public static final String ROW_INDEX = "row_index";
    public static final int REQUEST_GET_LOCATION = 2;
    public HomeFragment() {
    }
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getServerData();
        setAction();
        getCurrentLocation();
    }

    private double latitude;
    private double longitude;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private RoomAdapter roomAdapterSuggest;
    private RoomCategoryAdapter roomCategoryAdapter;
    private LocationManager locationManager;

    private RecyclerView recyclerViewSuggest;
    private RecyclerView recyclerViewCategory;

    private TextView currentLocation;
    private TextView viewAllRecommend;
    private TextView locationCurrent;

    private final int ITEM_VIEW_NORMAL = 0;
    private final int ITEM_VIEW_BIG = 1;

    private void setAction() {
        viewAllRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("View all:", "1");
            }
        });
    }

    private void detectLocation() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    private void initView(View view) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        currentLocation = view.findViewById(R.id.current_location);
        viewAllRecommend = view.findViewById(R.id.view_all_suggest);
        recyclerViewSuggest = view.findViewById(R.id.recycler_view);
        recyclerViewCategory = view.findViewById(R.id.room_category_recycler_view);
        locationCurrent = view.findViewById(R.id.current_location);


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager1 = new StaggeredGridLayoutManager(1, RecyclerView.HORIZONTAL);
        recyclerViewSuggest.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewCategory.setLayoutManager(staggeredGridLayoutManager1);
        roomAdapterSuggest = new RoomAdapter(new ArrayList<>(), this, ITEM_VIEW_NORMAL);
        roomCategoryAdapter = new RoomCategoryAdapter(new ArrayList<>(), this);
        recyclerViewSuggest.setAdapter(roomAdapterSuggest);
        recyclerViewCategory.setAdapter(roomCategoryAdapter);


    }

    private void getServerData() {
        String baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?id=524901&appid=b1a6b9d8867fa058c1a2f803e6244b14";
        Log.e("TAG", "connect");

        HttpRequestTask task = new HttpRequestTask(HttpRequestMethod.GET, new HttpRequestTask.Callback() {
            @Override
            public void onSuccess(String result) {
                ArrayList<RoomInformation> arrayList = NetWorkUtils.jsonToRoomList(result);
                roomAdapterSuggest.updateData(arrayList);

                ArrayList<CategoryRoom> categoryRooms = NetWorkUtils.jsonToRoomCategory(result);
                roomCategoryAdapter.updateData(categoryRooms);

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

    private void getCurrentLocation(){
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
            detectLocation();
        }

    }

    private void findAddress() {
        Log.e("HomeFragment:","find address");
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String country = addresses.get(0).getCountryName();
            String state = addresses.get(0).getAdminArea();
            currentLocation.setText(addresses.get(0).getLocality());
            Log.e("HomeFragment:",addresses.get(0).getLocality());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("HomeFragment:","error "+e.toString());

        }

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

    @Override
    public void onItemClick(int index, RoomCategoryAdapter roomCategoryAdapter) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("HomeFragment:","Location changed");

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        findAddress();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //To do something
            detectLocation();
        }
        else {
            Log.e("HomeFragment: ", "error location");
        }


    }
}
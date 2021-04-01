package com.example.myapplication3.detailscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication3.db.RoomContract;
import com.example.myapplication3.db.RoomDBHelper;
import com.example.myapplication3.manager.PreferenceManager;
import com.example.myapplication3.R;
import com.example.myapplication3.model.RoomInformation;
import com.example.myapplication3.model.RoomType;

import static com.example.myapplication3.homescreen.HomeFragment.ROW_INDEX;

public class DetailActivity extends AppCompatActivity  {

    static String ROOM_INFORMATION_ARG = "room_information_arg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView(R.layout.activity_detail);
        initView();
        fillData();
        setAction();
    }

    private RoomInformation roomInformation;
    private static String TAG = "TAG";
    private ImageView callToUse;
    private ImageView back;
    private ImageView roomImage;
    private TextView userNumber;
    private TextView roomName;
    private TextView roomAddress;
    private  TextView roomPrice;
    private TextView roomAcreage;

    private void initView(){
        roomImage = findViewById(R.id.room_image);
        callToUse= findViewById(R.id.call_to_user);
        back = findViewById(R.id.back);
        userNumber= findViewById(R.id.user_number);
        roomPrice= findViewById(R.id.price);
        roomAcreage= findViewById(R.id.room_acreage);
        roomName= findViewById(R.id.room_name);
        roomAddress= findViewById(R.id.room_address);
    }

    private void setAction(){
        callToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = roomInformation.getPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void fillData() {
        roomInformation = PreferenceManager.getRoomInformationFromPreference(this);

        roomImage.setImageResource(roomInformation.getIconResId());
        userNumber.setText(roomInformation.getPhone());
        roomName.setText(roomInformation.getRoomName());
        roomAddress.setText(roomInformation.getRoomAddress());
        roomPrice.setText("$ " + roomInformation.getPrice());
        roomAcreage.setText(roomInformation.getAcreage());
    }

    private void getRoomInformationFromPreference() {
        RoomDBHelper roomDBHelper = new RoomDBHelper(this);
        SQLiteDatabase sqLiteDatabase = roomDBHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                RoomContract.RoomEntry.ROOM_NAME,
                RoomContract.RoomEntry.ROOM_ADDRESS,
                RoomContract.RoomEntry.ROOM_PRICE,
                RoomContract.RoomEntry.ROOM_ACREAGE,

        };

        String selection = BaseColumns._ID + " = ?";
        long id = getIntent().getLongExtra(ROW_INDEX, 1);
        String[] selectionArgs = {"" + id};

        Cursor cursor = sqLiteDatabase.query(
                RoomContract.RoomEntry.TABLE_NAME,
                projection,
                selection, selectionArgs,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            String roomType = cursor.getString(cursor.getColumnIndexOrThrow(RoomContract.RoomEntry.ROOM_NAME));
            Log.e("Room type", roomType.toString());

            String price = cursor.getString(cursor.getColumnIndexOrThrow(RoomContract.RoomEntry.ROOM_PRICE));
            String acreage = cursor.getString(cursor.getColumnIndexOrThrow(RoomContract.RoomEntry.ROOM_ACREAGE));
            roomInformation = new RoomInformation(
                    RoomType.valueOf(roomType),
                    acreage,
                    price
            );
        }
        cursor.close();

//        Log.e("TAG", cursor.toString());
    }


}
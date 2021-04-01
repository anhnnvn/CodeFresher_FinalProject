 package com.example.myapplication3.homescreen;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.myapplication3.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private Button add;
    private Button add1;
    private Button add2;
    private Spinner spinner;
    String n;
    String n1;
    String n2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        getServerData();

        setAction();

    }

    private void setAction() {
        n = spinner.getSelectedItem().toString();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (n){
                    case "+": {
                        String x = String.valueOf(Integer.parseInt( editText1.getText().toString()) +
                                Integer.parseInt( editText2.getText().toString()) );
                        editText3.setText(x);
                        break;
                    }case "-": {
                        String x = String.valueOf(Integer.parseInt( editText1.getText().toString()) -
                                Integer.parseInt( editText2.getText().toString()) );
                        editText3.setText(x);
                        break;
                    }case "x": {
                        String x = String.valueOf(Integer.parseInt( editText1.getText().toString()) *
                                Integer.parseInt( editText2.getText().toString()) );
                        editText3.setText(x);
                        break;
                    }
                    case "/": {
                        String x = String.valueOf(Integer.parseInt( editText1.getText().toString()) /
                                Integer.parseInt( editText2.getText().toString()) );
                        editText3.setText(x);
                        break;
                    }
                }
            }
        });

        add1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                
                TimePicker timePicker = new TimePicker(getActivity());
                n1 = String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute());
                editText1.setText(n1);
            }
        });
        add2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                DatePicker datePicker = new DatePicker(getActivity());
                n2 = String.valueOf(datePicker.getMonth()) + ":" + String.valueOf(datePicker.getDayOfMonth())
                + ":" + String.valueOf(datePicker.getYear());
                editText2.setText(n2);
            }
        });

    }

    private void getServerData() {

    }

    private void initView(View view) {
        editText1 = view.findViewById(R.id.n1);
        editText2 = view.findViewById(R.id.n2);
        editText3 = view.findViewById(R.id.n3);

        add = view.findViewById(R.id.button);
        add1 = view.findViewById(R.id.button1);
        add2 = view.findViewById(R.id.button2);

        spinner = view.findViewById(R.id.spinner);
        String[] list = getResources().getStringArray(R.array.country);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}
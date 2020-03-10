package com.example.drinkmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowMachineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_machine);
        Bundle bundle = getIntent().getExtras();
        ArrayList<String> VendingList = bundle.getStringArrayList("SortArray");
        ListView listView = findViewById(R.id.listview);
        ArrayAdapter<String> items = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, VendingList);
        listView.setAdapter(items);
    }
}

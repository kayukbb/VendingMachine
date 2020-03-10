package com.example.drinkmachine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference Machine;
    int maxid;
    Spinner sort;
    String SortText;
    ArrayList<String> List =  new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sort = findViewById(R.id.sorting);
        String[] drinks = {"Oolong tea", "greentea","cola", "Lemon Tea", "tea"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, drinks);
        sort.setAdapter(adapter);

        Machine = FirebaseDatabase.getInstance().getReference().child("Machine");
        Button search;
        Button LogButton = (Button) findViewById(R.id.LogButton);
        LogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(startIntent);
            }
        });


        Button Reg_Button = (Button) findViewById(R.id.Reg_Button);
        Reg_Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), RegActivity.class);
                startActivity(startIntent);
            }
        });

        search = (Button) findViewById(R.id.go);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Machine.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List.clear();
                        SortText = sort.getSelectedItem().toString();
                        if(dataSnapshot.exists())
                            maxid=((int)dataSnapshot.getChildrenCount());
                        for(int i = 1; i <= maxid; i++) {
                            if(dataSnapshot.child(String.valueOf(i)).child(SortText).exists()){
                                List.add(String.valueOf(i));
                            }
                        }
                        Intent intent = new Intent(MainActivity.this, ShowMachineActivity.class);
                        intent.putExtra("SortArray", List);
                        startActivity(intent);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

            }
        });
        Button all = (Button) findViewById(R.id.All);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Machine.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List.clear();
                        SortText = sort.getSelectedItem().toString();
                        if(dataSnapshot.exists())
                            maxid=((int)dataSnapshot.getChildrenCount());
                        for(int i = 1; i <= maxid; i++) {
                                List.add(String.valueOf(i));
                        }
                        Intent intent = new Intent(MainActivity.this, ShowMachineActivity.class);
                        intent.putExtra("SortArray", List);
                        startActivity(intent);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });


    }
}

package com.example.drinkmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText UserName, Password;
    Button Login;
    DatabaseReference Log;
    long maxid;
    String Username;
    String password;
    TextView a, b;
    ArrayList<String> AcList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserName = (EditText)findViewById(R.id.UserName);
        Password = (EditText)findViewById(R.id.Pw);
        Login = (Button)findViewById(R.id.Login);
        a = (TextView)findViewById(R.id.a);
        b = (TextView)findViewById(R.id.b);
        Log = FirebaseDatabase.getInstance().getReference().child("Register");
        Log.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        final String[] Account = new String[(int)maxid];
        final String[] PassW = new String[(int)maxid];
        for(int i = 0; i< (int)maxid; i++) {
            int j = i+1;

            Log = FirebaseDatabase.getInstance().getReference().child("Register").child(String.valueOf(j));

            Log.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Username = dataSnapshot.child("userName").getValue().toString();
                    password = dataSnapshot.child("password").getValue().toString();
                    AcList.add(Username);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


    }
}

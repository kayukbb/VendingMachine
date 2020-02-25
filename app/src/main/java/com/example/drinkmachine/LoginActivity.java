package com.example.drinkmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    ArrayList<String> AcList = new ArrayList<>();
    ArrayList<String> PwList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserName = (EditText) findViewById(R.id.UserName);
        Password = (EditText) findViewById(R.id.Pw);
        Login = (Button) findViewById(R.id.Login);
        Log = FirebaseDatabase.getInstance().getReference().child("Register");
        Log.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
                for (int i = 1; i <= (int) maxid; i++) {
                    Username = dataSnapshot.child(String.valueOf(i)).child("userName").getValue().toString();
                    password = dataSnapshot.child(String.valueOf(i)).child("password").getValue().toString();
                    AcList.add(Username);
                    PwList.add(password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ac_Input = UserName.getText().toString();
                String Pw_Input = Password.getText().toString();
                int count = 0;
                for (int i = 0; i < AcList.size(); i++) {
                    if ((Ac_Input.equals(AcList.get(i))) && Pw_Input.equals(PwList.get(i))) {
                        finish();
                        break;
                    }
                    count ++;
                }
                if(count >= AcList.size())
                    Toast.makeText(LoginActivity.this, "Please enter correct User name and Password", Toast.LENGTH_LONG).show();
            }
        });
    }
}

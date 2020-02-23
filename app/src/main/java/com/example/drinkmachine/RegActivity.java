package com.example.drinkmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegActivity extends AppCompatActivity {
EditText UserName, Pw, Pw2;
Button Registration;
DatabaseReference Reg;
Register register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        UserName = (EditText)findViewById(R.id.UserName);
        Pw = (EditText)findViewById(R.id.Pw);
        Pw2 = (EditText)findViewById(R.id.Pw2);
        Registration = (Button)findViewById(R.id.Registration);
        register = new Register();
        Reg = FirebaseDatabase.getInstance().getReference().child("Register");
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               register.setUserName(UserName.getText().toString());
               register.setPassword(Pw.getText().toString());
               Reg.child("Register1").setValue(register);
               Toast.makeText(RegActivity.this, "sign up sueccessfully",Toast.LENGTH_LONG).show();
            }
        });

    }
}

package com.example.drinkmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegActivity extends AppCompatActivity {
    TextView a;
    EditText UserName, Pw, Pw2;
    Button Registration;
    DatabaseReference Reg;
    Register register;
    DatabaseReference UserInfo;
    String Username;
    ArrayList<String> AcList = new ArrayList<String>();
    long maxid;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"+"(?=.*[0-9])"+"(?=.*[a-z])"+"(?=.*[A-Z])"+"(?=\\S+$)"+".{5,14}"+"$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        UserName = (EditText)findViewById(R.id.UserName);
        Pw = (EditText)findViewById(R.id.Pw);
        Pw2 = (EditText)findViewById(R.id.Pw2);
        Registration = (Button)findViewById(R.id.Login);
        register = new Register();
        Reg = FirebaseDatabase.getInstance().getReference().child("Register");
        Reg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
                for(int i = 1; i <= (int)maxid; i++) {
                    Username = dataSnapshot.child(String.valueOf(i)).child("userName").getValue().toString();
                    AcList.add(Username);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameT = UserName.getText().toString();
                String pwT = Pw.getText().toString();
                String pwT2 =Pw2.getText().toString();
                for(int i = 0; i <AcList.size(); i++){
                    if(nameT.equals(AcList.get(i))){
                        UserName.setError("The user name has been used");
                        UserName.requestFocus();
                    }
                }

                if(nameT.isEmpty() && pwT.isEmpty() && pwT2.isEmpty()){
                    Toast.makeText(RegActivity.this,"Please Enter the Fields Clearly !!!",Toast.LENGTH_LONG).show();
                }
                else if (nameT.isEmpty()){
                    UserName.setError("Please enter User Name!!");
                    UserName.requestFocus();
                }
                else if(pwT.isEmpty()) {
                    Pw.setError("Please enter password!");
                    Pw.requestFocus();
                }
                else if(pwT2.isEmpty()) {
                    Pw2.setError("Please enter password again!");
                    Pw2.requestFocus();
                }
                else if (nameT.length()<3 || nameT.length()>12){
                    UserName.setError("The Username is limited between 4-12");
                    UserName.requestFocus();
                }
                else if(!PASSWORD_PATTERN.matcher(pwT).matches()){
                        Pw.setError("This password too weak,\nAt least 1 Digit, 1 Lower and Upper case letter!");
                        Pw.requestFocus();
                    }

                else if (!(pwT.equals(pwT2))){
                    Toast.makeText(RegActivity.this,"Password are not same",Toast.LENGTH_LONG).show();
                }
                else {
                    register.setUserName(UserName.getText().toString());
                    register.setPassword(Pw.getText().toString());
                    Reg.child(String.valueOf(maxid + 1)).setValue(register);
                    Toast.makeText(RegActivity.this,"Sign up successful",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}

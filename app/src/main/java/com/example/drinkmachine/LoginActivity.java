package com.example.drinkmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
EditText UserName, Password;
Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserName = (EditText)findViewById(R.id.UserName);
        Password = (EditText)findViewById(R.id.Pw);
        Login = (Button)findViewById(R.id.Registration);
    }
}

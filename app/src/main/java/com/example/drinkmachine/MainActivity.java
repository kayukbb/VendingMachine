package com.example.drinkmachine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button LogButton = (Button) findViewById(R.id.LogButton);
        LogButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent startIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(startIntent);

                /* this is my test cod */
            }

        });
    }
}

package com.example.mad_demo19;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

/*        TextView textView = findViewById(R.id.welcomeText);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(OverviewActivity.this, R.string.helloMessage, Toast.LENGTH_LONG).show();
            }

        });
        */

        ViewGroup listView = findViewById(R.id.listView);
        for(int i=0; i<listView.getChildCount(); i++){
            TextView currentTextView = (TextView)( listView).getChildAt(i);
            currentTextView.setOnClickListener((view) -> {
                //   Toast.makeText(OverviewActivity.this, "Selected: " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(R.id.overviewLayout), "Selected: " + ((TextView) view).getText(), Snackbar.LENGTH_INDEFINITE ).show();
            });
        }
    }
}

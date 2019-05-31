package com.example.mad_demo19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.mad_demo19.model.ToDo;

import java.io.Serializable;

public class DetailviewActivity extends AppCompatActivity {

    private FloatingActionButton saveButton;
    private EditText itemName;
    private EditText itemDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);

        saveButton = findViewById(R.id.fab);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });
        itemName = findViewById(R.id.itemName);
        itemDescription = findViewById(R.id.itemDescription);

    }
    private void saveItem(){
        //Intent --> Datenbehälter um Daten zwischen Activities zu transportieren
        Intent returnIntent = new Intent();
        ToDo item = new ToDo(itemName.getText().toString());
        item.setDescription(itemDescription.getText().toString());

        //Objekt wird in serialisierte Form gebracht
        returnIntent.putExtra("item", (Serializable) item);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}

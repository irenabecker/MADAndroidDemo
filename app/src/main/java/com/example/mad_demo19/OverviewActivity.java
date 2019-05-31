package com.example.mad_demo19;

import android.content.Intent;
/*import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
*/
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mad_demo19.model.impl.room.ToDoDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_demo19.model.ToDo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OverviewActivity extends AppCompatActivity {

    //Bedienelemente als Instanzatribute deklarieren
    private ViewGroup listView;
    private FloatingActionButton createButton;
    private ArrayAdapter<ToDo> listViewAdapter;

    private ToDoDatabase db;

    private List<ToDo> items = new ArrayList<>();//(Arrays.asList(new ToDo("lorem"), new ToDo("ipsum"), new ToDo("dolor")));

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
        listView = findViewById(R.id.listView);
       /* for(int i=0; i<listView.getChildCount(); i++){
            TextView currentTextView = (TextView)( listView).getChildAt(i);
            currentTextView.setOnClickListener((view) -> {
                //   Toast.makeText(OverviewActivity.this, "Selected: " + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                Snackbar.make(findViewById(R.id.overviewLayout), "Selected: " + ((TextView) view).getText(), Snackbar.LENGTH_INDEFINITE ).show();
            });
        } */
        createButton = findViewById(R.id.fab);
        createButton.setOnClickListener((view) -> {
            createNewItem();
        });
      //  items.forEach(item -> addNewItemToList(item));
      /*  for (String item :  items){
            addNewItemToList(item);
        }*/

      listViewAdapter = new ArrayAdapter<ToDo>(this, R.layout.activity_overview_listitem_simple, items){
          //Im Inflator View aufbauen
          @NonNull
          @Override
          public View getView(int position, @Nullable View convertView ,@NonNull ViewGroup parent){
              View itemView = getLayoutInflater().inflate(R.layout.activity_overview_listitems, null);
              TextView itemNameView = itemView.findViewById(R.id.itemName);
              ToDo currentItem = getItem(position);
              itemNameView.setText(currentItem.getName());
              return itemView;
          }
      };
        ((ListView)listView).setAdapter(listViewAdapter);
        ((ListView) listView).setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long idDbElement) {
                ToDo clickedItem = listViewAdapter.getItem(position);
                Snackbar.make(findViewById(R.id.overviewLayout), "Selected: " + clickedItem.getName(), Snackbar.LENGTH_INDEFINITE).show();
            }
        });

        db = Room.databaseBuilder(getApplicationContext(), ToDoDatabase.class, "todo-db").build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ToDo> items = db.getDao().readAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (ToDo item : items){
                            addNewItemToList(item);
                        }
                    }
                });
            }
        }).start();
    }

    private void createNewItem(){
      //  Snackbar.make(findViewById(R.id.overviewLayout), "Create New item!", Snackbar.LENGTH_SHORT).show();
        Intent detailviewIntent = new Intent(this, DetailviewActivity.class);
        startActivityForResult(detailviewIntent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK){
            ToDo item = (ToDo)data.getSerializableExtra("item");
          //  Snackbar.make(findViewById(R.id.overviewLayout), "Received: " + itemName, Snackbar.LENGTH_LONG).show();
            createItemAndUpdateList(item);
        }
    }

    private void createItemAndUpdateList(ToDo item){
        //neuer Hintergrundthread
        new Thread(new Runnable() {
            @Override
            public void run() {
                long id = db.getDao().create(item);
                item.setId(id);
                //geht auf MainThread --> addNewItemToList muss im MainThread aufgerufen werden
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        addNewItemToList(item);
                    }
                });
            }
        }).start();
    }

    private void addNewItemToList(ToDo toDo){
        listViewAdapter.add(toDo);
      /*  View itemView = getLayoutInflater().inflate(R.layout.activity_overview_listitem_simple, null);
        ((TextView)itemView).setText(itemName);
        itemView.setOnClickListener((view)->  {
            Snackbar.make(findViewById(R.id.overviewLayout), "Selected: " + ((TextView) view).getText(), Snackbar.LENGTH_INDEFINITE ).show();
        });
        listView.addView(itemView);
        */
      /* Lambda Äquivalent zu:
        itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(findViewById(R.id.overviewLayout), "Selected: " + ((TextView) view).getText(), Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        */
    }
}

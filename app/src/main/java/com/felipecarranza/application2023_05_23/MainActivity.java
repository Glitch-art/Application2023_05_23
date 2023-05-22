package com.felipecarranza.application2023_05_23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.felipecarranza.application2023_05_23.adapters.ListEntriesAdapter;
import com.felipecarranza.application2023_05_23.db.DbEntries;
import com.felipecarranza.application2023_05_23.db.DbHelper;
import com.felipecarranza.application2023_05_23.entities.Entries;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView list_entries;
    ArrayList<Entries> array_entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_entries = findViewById(R.id.list_entries);
        list_entries.setLayoutManager(new LinearLayoutManager(this));

        DbEntries dbEntries = new DbEntries(MainActivity.this);

        array_entries = new ArrayList<>();

        ListEntriesAdapter listEntriesAdapter = new ListEntriesAdapter(dbEntries.indexEntries());
        list_entries.setAdapter(listEntriesAdapter);
    }

    // Mostrar el menú de opciones
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new:
                navigateToNewActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void navigateToNewActivity() {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }
}
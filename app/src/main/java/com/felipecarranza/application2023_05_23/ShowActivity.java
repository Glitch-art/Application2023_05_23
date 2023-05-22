package com.felipecarranza.application2023_05_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.felipecarranza.application2023_05_23.db.DbEntries;
import com.felipecarranza.application2023_05_23.entities.Entries;

public class ShowActivity extends AppCompatActivity {

    EditText txt_title, txt_content;
    Button btn_save;

    boolean edit_result = false;

    Entries entry;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        txt_title = findViewById(R.id.txt_title);
        txt_content = findViewById(R.id.txt_content);
        btn_save = findViewById(R.id.btn_save);

        // Obtener el id de la entrada
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id = 0;
            } else {
                id = extras.getInt("id");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("id");
        }

        // Obtener la entrada de la base de datos
        DbEntries dbEntries = new DbEntries(ShowActivity.this);
        entry = dbEntries.showEntry(id);

        // Mostrar los datos de la entrada en los campos de texto
        if (entry != null) {
            txt_title.setText(entry.getTitle());
            txt_content.setText(entry.getContent());
        }

        btn_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!txt_title.getText().toString().isEmpty() && !txt_content.getText().toString().isEmpty()) {
                    edit_result = dbEntries.editEntry(id, txt_title.getText().toString(), txt_content.getText().toString());

                    if (edit_result) {
                        Toast.makeText(ShowActivity.this, "Entrada editada correctamente", Toast.LENGTH_SHORT).show();
                        navigateToMainActivity();
                    } else {
                        Toast.makeText(ShowActivity.this, "Error al editar la entrada", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ShowActivity.this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
package com.felipecarranza.application2023_05_23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.felipecarranza.application2023_05_23.db.DbEntries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewActivity extends AppCompatActivity {

    EditText txt_title, txt_content;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        txt_title = findViewById(R.id.txt_title);
        txt_content = findViewById(R.id.txt_content);
        btn_save = findViewById(R.id.btn_save);
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        btn_save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbEntries dbEntries = new DbEntries(NewActivity.this);
                long id = dbEntries.insertEntry(txt_title.getText().toString(), txt_content.getText().toString(), now, now);

                if (id > 0) {
                    Toast.makeText(NewActivity.this, "Entrada guardada", Toast.LENGTH_SHORT).show();
                    navigateToMainActivity();
                } else {
                    Toast.makeText(NewActivity.this, "Error al guardar la entrada", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
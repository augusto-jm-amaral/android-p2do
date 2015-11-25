package com.example.augusto.projeto2do;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final String username = getIntent().getStringExtra("USERNAME");

        Button btnCategorias = (Button) findViewById(R.id.btnCategorias);
        Button btnTarefas = (Button) findViewById(R.id.btnTarefas);

        btnCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Categorias.class);
                intent.putExtra("USERNAME",username);
                Menu.this.startActivity(intent);
            }
        });

        btnTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

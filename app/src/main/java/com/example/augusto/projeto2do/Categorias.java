package com.example.augusto.projeto2do;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.augusto.projeto2do.adapter.CategoriasAdapter;
import com.example.augusto.projeto2do.modelo.Categoria;
import com.example.augusto.projeto2do.modelo.CategoriaDao;

public class Categorias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        CategoriaDao categoriaDao = new CategoriaDao(this);

        final String username = getIntent().getStringExtra("USERNAME");

        ListView listView = (ListView) findViewById(R.id.lvCategorias);
        listView.setAdapter(new CategoriasAdapter(this,categoriaDao.selecionarTodas()));

        Button btnNovaCategoria = (Button) findViewById(R.id.btnNovaCategoria);
        btnNovaCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categorias.this, NovaCategoria.class);
                Categorias.this.startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Categoria item = (Categoria) parent.getAdapter().getItem(position);
                Intent intent = new Intent(Categorias.this, NovaCategoria.class);
                intent.putExtra("IDCATEGORIA", ""+item.getId());
                intent.putExtra("NOMECATEGORIA", item.getNome());
                Categorias.this.startActivity(intent);
            }
        });

    }
}

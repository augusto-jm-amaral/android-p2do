package com.example.augusto.projeto2do;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.augusto.projeto2do.modelo.Categoria;
import com.example.augusto.projeto2do.modelo.CategoriaDao;

public class NovaCategoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_categoria);

        final CategoriaDao categoriaDao = new CategoriaDao(this);
        final String idCategoriaString = getIntent().getStringExtra("IDCATEGORIA");
        String nomeCategoria = getIntent().getStringExtra("NOMECATEGORIA");
        Categoria categoriaExistente = null;
        boolean isUpdate = false;

        Button btnSalvar = (Button) findViewById(R.id.btnSalvarCategoria);
        Button btnExcluir = (Button) findViewById(R.id.btnExcluirCategoria);
        EditText etNomeCategoria = (EditText) findViewById(R.id.etNomeCategoria);

        if(idCategoriaString!=null){
            etNomeCategoria.setText(nomeCategoria);
            isUpdate = true;
        }

        final boolean finalIsUpdate = isUpdate;
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etNomeCategoria = (EditText) findViewById(R.id.etNomeCategoria);
                String nomeCategoria = etNomeCategoria.getText().toString();

                Categoria categoria = new Categoria();
                categoria.setNome(nomeCategoria);
                if(finalIsUpdate){
                    categoria.setId(Integer.parseInt(idCategoriaString));
                    categoriaDao.atualizar(categoria);
                    Toast toast = Toast.makeText(NovaCategoria.this, "Categoria atualizada com sucesso!", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    long inserir = categoriaDao.inserir(categoria);

                    if(inserir>0){
                        Toast toast = Toast.makeText(NovaCategoria.this, "Categoria inserida com sucesso!", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(NovaCategoria.this, "Falha ao inserir a categoria!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

                NovaCategoria.this.finish();
            }
        });
    }
}

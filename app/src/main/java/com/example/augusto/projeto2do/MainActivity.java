package com.example.augusto.projeto2do;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.augusto.projeto2do.modelo.UsuarioDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final UsuarioDao usuarioDao = new UsuarioDao(this);

        Button btnAcessar = (Button) findViewById(R.id.btnAcessar);
        Button btnNovoUsuario = (Button) findViewById(R.id.btnNovoUsuario);
        Button btnAtualizarUsuario = (Button) findViewById(R.id.btnAtualizarUsuario);

        final EditText editUserName = (EditText) findViewById(R.id.userName);
        final EditText editpassword = (EditText) findViewById(R.id.password);

        //Acessar
        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editUserName.getText().toString();
                String password = editpassword.getText().toString();

                if(username!=null && password!=null){
                    if(usuarioDao.validar(username, password)){
                        Intent intent = new Intent(MainActivity.this, Menu.class);
                        intent.putExtra("USERNAME",username);
                        MainActivity.this.startActivity(intent);
                    }
                }

            }
        });

        //Atualizar usuario
        btnAtualizarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editUserName.getText().toString();
                String password = editpassword.getText().toString();

                if(username!=null && password!=null){

                    if(usuarioDao.validar(username, password)){
                        Intent intent = new Intent(MainActivity.this, NovoUsuario.class);
                        intent.putExtra("USERNAME",username);
                        MainActivity.this.startActivity(intent);
                    }
                }

            }
        });

        //Novo usuario
        btnNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NovoUsuario.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

}

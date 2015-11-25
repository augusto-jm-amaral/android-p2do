package com.example.augusto.projeto2do;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.augusto.projeto2do.modelo.Usuario;
import com.example.augusto.projeto2do.modelo.UsuarioDao;

public class NovoUsuario extends AppCompatActivity {

    UsuarioDao usuarioDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        usuarioDao = new UsuarioDao(this);
        boolean isUsuarioUpdate = false;

        final EditText editNome = (EditText) findViewById(R.id.editNome);
        final RadioGroup radioSexo = (RadioGroup) findViewById(R.id.radioGroup);
        final EditText editUserName = (EditText) findViewById(R.id.editUserName);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);

        String username = getIntent().getStringExtra("USERNAME");

        if(username!=null){
            Usuario usuario = usuarioDao.buscaUsuario(username);
            if(usuario!=null){
                isUsuarioUpdate = true;
                editNome.setText(usuario.getNome());
                editPassword.setText(usuario.getPassword());
                editUserName.setText(usuario.getUsername());
                Log.i("DEBUG",usuario.getSexo());

            }
        }

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
        final boolean finalIsUsuarioUpdate = isUsuarioUpdate;
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuario = new Usuario();
                usuario.setNome(editNome.getText().toString());

                int sexo = radioSexo.getCheckedRadioButtonId();
                if(sexo == 1){
                    usuario.setSexo("M");
                }else{
                    usuario.setSexo("F");
                }

                usuario.setUsername(editUserName.getText().toString());
                usuario.setPassword(editPassword.getText().toString());


                long rowid = -1;

                if (finalIsUsuarioUpdate){
                    usuarioDao.atualizar(usuario);
                    rowid = 1;
                }else {
                    rowid = usuarioDao.inserir(usuario);
                }

                Context context = getApplicationContext();
                CharSequence text = "Usuario inserido com sucesso!";
                int duration = Toast.LENGTH_SHORT;


                if(rowid<0){
                    text = "Falha ao inserir o usuario!";
                }

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                NovoUsuario.this.finish();

            }
        });
    }


}

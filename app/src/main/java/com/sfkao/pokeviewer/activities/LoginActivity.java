package com.sfkao.pokeviewer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.utils.Login;

public class LoginActivity extends AppCompatActivity {

    EditText nombreDeUsuario, contrasenya;
    Button olvidadaContrasenya, iniciarSesion, registrarse,sinIniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        nombreDeUsuario = findViewById(R.id.usernameTextInput);
        contrasenya = findViewById(R.id.passwordTextInput);
        iniciarSesion = findViewById(R.id.iniciarSesionButton);
        sinIniciar = findViewById(R.id.inivitadoButton);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombreDeUsuario.getText().toString().equals("")){
                    Toast.makeText(view.getContext(),R.string.se_requiere_nombre_de_usuario,Toast.LENGTH_LONG).show();
                    return;
                }
                Login.setUsuario(new Login.User(nombreDeUsuario.getText().toString()));
                irAMain();
            }
        });

        sinIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.logout();
                irAMain();
            }
        });

    }

    private void irAMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
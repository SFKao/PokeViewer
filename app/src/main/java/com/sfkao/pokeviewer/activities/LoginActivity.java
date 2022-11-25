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

        Login.autoLogin(this);

        nombreDeUsuario = findViewById(R.id.usernameTextInput);
        contrasenya = findViewById(R.id.passwordTextInput);
        iniciarSesion = findViewById(R.id.iniciarSesionButton);
        sinIniciar = findViewById(R.id.inivitadoButton);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombreDeUsuario.getText().toString().equals("")){ //Hay que poner tambien la contraseña, pero como el login no funciona aun no.
                    Toast.makeText(view.getContext(),R.string.se_requiere_nombre_de_usuario,Toast.LENGTH_LONG).show();
                    return;
                }
                if(Login.tryLogin(nombreDeUsuario.getText().toString(), contrasenya.getText().toString(), LoginActivity.this ))
                    irAMain();
                else
                    Toast.makeText(view.getContext(),R.string.usuario_o_contra_incorrectos,Toast.LENGTH_LONG).show();
            }
        });

        sinIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.logout(LoginActivity.this);
                irAMain();
            }
        });

    }

    private void irAMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
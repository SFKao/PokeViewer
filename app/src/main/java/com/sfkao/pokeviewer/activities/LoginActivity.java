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

/**
 * Activity que se lanza para loguearse.
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * Contiene los siguientes campos y botones
     */
    EditText nombreDeUsuario, contrasenya;
    Button olvidadaContrasenya, iniciarSesion, registrarse,sinIniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        //Login.autoLogin(this);        //Necesario si se decide que esta sea la activity que se lanza con la app

        //Recojo los campos, faltan por recoger algunos que un no funcionan
        nombreDeUsuario = findViewById(R.id.usernameTextInput);
        contrasenya = findViewById(R.id.passwordTextInput);
        iniciarSesion = findViewById(R.id.iniciarSesionButton);
        sinIniciar = findViewById(R.id.inivitadoButton);
        registrarse = findViewById(R.id.registrarseButton);

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irARegister();
            }
        });

        //Si se selecciona iniciar sesion
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Miro que los campos no esten vacios
                if(nombreDeUsuario.getText().toString().equals("")){ //Hay que poner tambien la contraseña, pero como el login no funciona aun no.
                    //Si esta vacia muestro un Toast
                    Toast.makeText(view.getContext(),R.string.se_requiere_nombre_de_usuario,Toast.LENGTH_LONG).show();
                    return;
                }
                //Si se loguea, ve al main, si no, muestra que algun campo es incorrecto
                try {
                    if(Login.tryLogin(nombreDeUsuario.getText().toString(), contrasenya.getText().toString(), LoginActivity.this ))
                        irAMain();
                    else
                        Toast.makeText(view.getContext(),R.string.usuario_o_contra_incorrectos,Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        sinIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Logout coloca como cuenta de invitado
                Login.logout(LoginActivity.this);
                irAMain();
            }
        });

    }

    private void irAMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void irARegister(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }


}
package com.sfkao.pokeviewer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.utils.Login;


public class RegisterActivity extends AppCompatActivity {

    EditText usuario;
    EditText email;
    EditText pass;
    EditText repeatPass;
    Button registrar;
    ImageButton salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usuario = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        pass = findViewById(R.id.editTextPass);
        repeatPass = findViewById(R.id.editTextRepeatPass);

        registrar = findViewById(R.id.registerButton);
        salir = findViewById(R.id.exitRegisterImageButton);

    }

    @Override
    protected void onStart() {
        super.onStart();

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irALogin();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usuario.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,R.string.usuario_obligatorio,Toast.LENGTH_LONG).show();
                    return;
                }
                //TODO: Mirar que el correo sea un correo
                if(email.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,R.string.email_obligatorio,Toast.LENGTH_LONG).show();
                    return;
                }
                if(pass.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,R.string.pass_obligatorio,Toast.LENGTH_LONG).show();
                    return;
                }
                if(repeatPass.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this,R.string.repeatPass_obligatorio,Toast.LENGTH_LONG).show();
                    return;
                }
                if(!pass.getText().toString().equals(repeatPass.getText().toString())){
                    Toast.makeText(RegisterActivity.this,R.string.contrase_a_no_concuerdan,Toast.LENGTH_LONG).show();
                    return;
                }
                try{
                    if(Login.tryRegister(usuario.getText().toString(),pass.getText().toString(),email.getText().toString(),RegisterActivity.this)){
                        irAMain();
                    }
                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_LONG);
                }
            }
        });

    }

    private void irAMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void irALogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
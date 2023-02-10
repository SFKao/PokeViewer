package com.sfkao.pokeviewer.modelo.pojo_pokeapi_login;

import com.sfkao.pokeviewer.modelo.pojo_pokeapi_usuario.AmigoApi;

public class LoginResponse {

    private AmigoApi usuario;
    private int code;
    private String response;

    public LoginResponse(AmigoApi usuario, int code, String response) {
        this.usuario = usuario;
        this.code = code;
        this.response = response;
    }

    public AmigoApi getUsuario() {
        return usuario;
    }

    public void setUsuario(AmigoApi usuario) {
        this.usuario = usuario;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "LoginResponde{" +
                "usuario=" + usuario +
                ", code=" + code +
                ", response='" + response + '\'' +
                '}';
    }
}

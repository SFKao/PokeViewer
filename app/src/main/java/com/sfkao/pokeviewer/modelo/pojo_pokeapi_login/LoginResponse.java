package com.sfkao.pokeviewer.modelo.pojo_pokeapi_login;

public class LoginResponse {

    private UsuarioApi usuario;
    private int code;
    private String response;

    public LoginResponse(UsuarioApi usuario, int code, String response) {
        this.usuario = usuario;
        this.code = code;
        this.response = response;
    }

    public UsuarioApi getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioApi usuario) {
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

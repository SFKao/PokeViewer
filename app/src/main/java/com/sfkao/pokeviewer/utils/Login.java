package com.sfkao.pokeviewer.utils;

public class Login {

    private static User usuario = autoLogin();

    public static User getUsuario() {
        return usuario;
    }

    public static void setUsuario(User usuario) {
        Login.usuario = usuario;
    }

    public static String getUsername(){
        return usuario.username;
    }

    public static boolean isInvited(){
        return usuario.invitado;
    }

    public static User autoLogin(){
        usuario = logout(); //TODO: cambiar para que pueda loguearme
        return usuario;
    }

    public static User logout(){
        usuario = new User("Invitado","","");
        return usuario;
    }

    public static class User{
        private String username;
        private String mail;
        private String api_key;
        private boolean invitado;

        public User(String username, String mail, String api_key) {
            this.username = username;
            this.mail = mail;
            this.api_key = api_key;
            invitado = true;
        }

        public User(String username) {
            this.username = username;
            invitado = false;
        }

        public User() {
            invitado = true;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getApi_key() {
            return api_key;
        }

        public void setApi_key(String api_key) {
            this.api_key = api_key;
        }

        public boolean isInvitado() {
            return invitado;
        }

        public void setInvitado(boolean invitado) {
            this.invitado = invitado;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", mail='" + mail + '\'' +
                    ", api_key='" + api_key + '\'' +
                    ", invitado=" + invitado +
                    '}';
        }
    }

}

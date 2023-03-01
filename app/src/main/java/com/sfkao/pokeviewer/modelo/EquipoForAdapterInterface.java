package com.sfkao.pokeviewer.modelo;

public interface EquipoForAdapterInterface {

    String getName();
    String getUser();
    String getApiId();
    boolean isPokemon(int pok);
    String getPokImg(int pok);
    String getPokSImg(int pok);
    String getPokName(int pok);
    int getPokId(int pok);
    int getLikes();
    int getFavs();
    boolean getDadoLike();
    boolean getDadoFav();
    void setDadoLike(boolean b);
    void setDadoFavoritos(boolean b);

    void setName(String s);
    void setUser(String s);
    void setApiID(String s);
    void setPokemon(int pos, int id, String img, String imgS, String name);
    void setLikes(int l);
    void setFavs(int f);

}

package com.sfkao.pokeviewer.modelo;

public interface EquipoForAdapterInterface {

    public String getName();
    public String getUser();
    public String getApiId();
    public boolean isPokemon(int pok);
    public String getPokImg(int pok);
    public String getPokSImg(int pok);
    public String getPokName(int pok);
    public int getPokId(int pok);
    public int getLikes();
    public int getFavs();
    public boolean getDadoLike();
    public boolean getDadoFav();
    public void setDadoLike(boolean b);
    public void setDadoFavoritos(boolean b);

    public void setName(String s);
    public void setUser(String s);
    public void setApiID(String s);
    public void setPokemon(int pos, int id, String img, String imgS, String name);
    public void setLikes(int l);
    public void setFavs(int f);

}

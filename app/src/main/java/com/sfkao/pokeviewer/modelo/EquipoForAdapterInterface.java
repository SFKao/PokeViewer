package com.sfkao.pokeviewer.modelo;

public interface EquipoForAdapterInterface {

    public String getName();
    public String getUser();
    public String getId();
    public boolean isPokemon(int pok);
    public String getPokImg(int pok);
    public String getPokSImg(int pok);
    public String getPokName(int pok);
    public int getPokId(int pok);
    public int getLikes();
    public int getFavs();
    public boolean getDadoLike();
    public boolean getDadoFav();

}

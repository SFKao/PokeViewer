package com.sfkao.pokeviewer.modelo;

import com.sfkao.pokeviewer.apis.ApiConexion;
import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;

import java.util.Arrays;
import java.util.Objects;

/**
 * Clase que almacena informacion de un equipo pokemon
 */
public class Equipo implements EquipoForAdapterInterface{

    private String nombre;
    private String autor;
    private String identificador;
    private Pokemon[] pokemons;
    private int likes;
    private int favoritos;

    public Equipo() {
        pokemons = new Pokemon[6];
    }

    public Equipo(String nombre, String autor, String identificador, Pokemon[] pokemons, int likes, int favoritos) {
        this.nombre = nombre;
        this.autor = autor;
        this.identificador = identificador;
        this.pokemons = pokemons;
        this.likes = likes;
        this.favoritos = favoritos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Pokemon getPokemon(int pos){
        return pokemons[pos];
    }

    public void setPokemon(Pokemon p, int pos){
        pokemons[pos] = p;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public void setPokemons(Pokemon[] pokemons) {
        this.pokemons = pokemons;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(int favoritos) {
        this.favoritos = favoritos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return likes == equipo.likes && favoritos == equipo.favoritos && Objects.equals(nombre, equipo.nombre) && Objects.equals(autor, equipo.autor) && Objects.equals(identificador, equipo.identificador) && Arrays.equals(pokemons, equipo.pokemons);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nombre, autor, identificador, likes, favoritos);
        result = 31 * result + Arrays.hashCode(pokemons);
        return result;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", identificador='" + identificador + '\'' +
                ", pokemons=" + Arrays.toString(pokemons) +
                ", likes=" + likes +
                ", favoritos=" + favoritos +
                '}';
    }

    @Override
    public String getName() {
        return nombre;
    }

    @Override
    public String getUser() {
        return autor;
    }

    @Override
    public String getApiId() {
        return identificador;
    }

    @Override
    public String getPokImg(int pok) {
        return pokemons[pok].getSprites().getFrontDefault();
    }

    @Override
    public String getPokSImg(int pok) {
        return pokemons[pok].getSprites().getFrontShiny();
    }

    @Override
    public String getPokName(int pok) {
        return pokemons[pok].getName();
    }

    @Override
    public int getPokId(int pok) {
        return pokemons[pok].getId();
    }

    @Override
    public int getFavs() {
        return favoritos;
    }

    @Override
    public boolean getDadoLike() {
        return false;
    }

    @Override
    public boolean getDadoFav() {
        return false;
    }

    @Override
    public boolean isPokemon(int pok) {
        return pokemons[pok] != null;
    }

    @Override
    public void setName(String s) {
        nombre = s;
    }

    @Override
    public void setUser(String s) {
        autor = s;
    }

    @Override
    public void setApiID(String s) {
        identificador = s;
    }

    @Override
    public void setPokemon(int pos, int id, String img, String imgS, String name) {
        pokemons[pos] = ApiConexion.getInstance().getPokemon(id);
    }

    @Override
    public void setFavs(int f) {
        favoritos = f;
    }
}

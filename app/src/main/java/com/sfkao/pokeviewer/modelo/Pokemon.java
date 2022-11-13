package com.sfkao.pokeviewer.modelo;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class Pokemon {

    private String nombre;
    private int id;
    private String urlFoto;
    private String[] tipos;

    private HashSet<String> debilidades;
    private HashSet<String> resistencias;
    private HashSet<String> dobleDebilidades;
    private HashSet<String> dobleResistencias;
    private HashSet<String> inmunidades;

    private int ps, atk, def, sAtk, sDef, spe;
    private String habilidad1, habilidad2, habilidadOculta;

    public Pokemon() {
        tipos = new String[2];
    }

    public Pokemon(String nombre) {
        this();
        this.nombre = nombre;
    }

    public Pokemon(int id) {
        this();
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String[] getTipos() {
        return tipos;
    }

    public void setTipos(String[] tipos) {
        this.tipos = tipos;
    }

    public HashSet<String> getDebilidades() {
        return debilidades;
    }

    public void setDebilidades(HashSet<String> debilidades) {
        this.debilidades = debilidades;
    }

    public HashSet<String> getResistencias() {
        return resistencias;
    }

    public void setResistencias(HashSet<String> resistencias) {
        this.resistencias = resistencias;
    }

    public HashSet<String> getDobleDebilidades() {
        return dobleDebilidades;
    }

    public void setDobleDebilidades(HashSet<String> dobleDebilidades) {
        this.dobleDebilidades = dobleDebilidades;
    }

    public HashSet<String> getDobleResistencias() {
        return dobleResistencias;
    }

    public void setDobleResistencias(HashSet<String> dobleResistencias) {
        this.dobleResistencias = dobleResistencias;
    }

    public HashSet<String> getInmunidades() {
        return inmunidades;
    }

    public void setInmunidades(HashSet<String> inmunidades) {
        this.inmunidades = inmunidades;
    }

    public void setTipo1(String tipo1){
        tipos[0] = tipo1;
    }

    public void setTipo2(String tipo2){
        tipos[1] = tipo2;
    }

    public String getTipo1(){
        return tipos[0];
    }

    public String getTipo2(){
        return tipos[1];
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getsAtk() {
        return sAtk;
    }

    public void setsAtk(int sAtk) {
        this.sAtk = sAtk;
    }

    public int getsDef() {
        return sDef;
    }

    public void setsDef(int sDef) {
        this.sDef = sDef;
    }

    public int getSpe() {
        return spe;
    }

    public void setSpe(int spe) {
        this.spe = spe;
    }

    public String getHabilidad1() {
        return habilidad1;
    }

    public void setHabilidad1(String habilidad1) {
        this.habilidad1 = habilidad1;
    }

    public String getHabilidad2() {
        return habilidad2;
    }

    public void setHabilidad2(String habilidad2) {
        this.habilidad2 = habilidad2;
    }

    public String getHabilidadOculta() {
        return habilidadOculta;
    }

    public void setHabilidadOculta(String habilidadOculta) {
        this.habilidadOculta = habilidadOculta;
    }

    @NonNull
    @Override
    public String toString() {
        return "Pokemon{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", urlFoto='" + urlFoto + '\'' +
                ", tipos=" + Arrays.toString(tipos) +
                ", debilidades=" + debilidades +
                ", resistencias=" + resistencias +
                ", dobleDebilidades=" + dobleDebilidades +
                ", dobleResistencias=" + dobleResistencias +
                ", inmunidades=" + inmunidades +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return id == pokemon.id && Objects.equals(nombre, pokemon.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, id);
    }
}

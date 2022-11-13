package com.sfkao.pokeviewer.apis;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.modelo.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;

public class ApiConexion {

    private MainActivity mainActivity;

    public ApiConexion(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void buscarPokemonYMostrar(String nombre){
        final Pokemon[] pokemon = {new Pokemon()};

        String endpoint = ApiUtils.endpointPokemon + nombre;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JsonObject jsonObject = new Gson().fromJson(response.toString(), JsonObject.class);
                        JsonObject sprites = jsonObject.getAsJsonObject("sprites");
                        if (sprites.isJsonNull()) {
                            pokemon[0] = null;
                            return;
                        }

                        pokemon[0].setUrlFoto(sprites.get("front_default").getAsString());
                        pokemon[0].setId(jsonObject.get("id").getAsInt());
                        pokemon[0].setNombre(jsonObject.get("name").getAsString());


                        //JSONArray tipos = new Gson().fromJson(jsonObject.get("types").toString(),JSONArray.class);
                        JsonArray tipos = jsonObject.getAsJsonArray("types");

                        JsonObject tipo1 = tipos.get(0).getAsJsonObject();

                        JsonObject tipo2 = null;



                        if(tipos.size()==2){
                            tipo2 = tipos.get(1).getAsJsonObject();
                        }


                        pokemon[0].setTipo1(tipo1.getAsJsonObject("type").get("name").getAsString());
                        if(tipo2!=null){
                            pokemon[0].setTipo2(tipo2.getAsJsonObject("type").get("name").getAsString());
                        }
                        JsonArray stats = jsonObject.getAsJsonArray("stats");
                        pokemon[0].setPs((stats.get(0).getAsJsonObject().get("base_stat").getAsInt()));
                        pokemon[0].setAtk((stats.get(1).getAsJsonObject().get("base_stat").getAsInt()));
                        pokemon[0].setDef((stats.get(2).getAsJsonObject().get("base_stat").getAsInt()));
                        pokemon[0].setsAtk((stats.get(3).getAsJsonObject().get("base_stat").getAsInt()));
                        pokemon[0].setsDef((stats.get(4).getAsJsonObject().get("base_stat").getAsInt()));
                        pokemon[0].setSpe((stats.get(5).getAsJsonObject().get("base_stat").getAsInt()));

                        JsonArray habilidades = jsonObject.getAsJsonArray("abilities");
                        for(int i = 0; i < habilidades.size(); i++){
                            if(habilidades.get(i).getAsJsonObject().get("is_hidden").getAsBoolean())
                                pokemon[0].setHabilidadOculta(habilidades.get(i).getAsJsonObject().get("ability").getAsJsonObject().get("name").getAsString());
                            else{
                                if(pokemon[0].getHabilidad1()==null)
                                    pokemon[0].setHabilidad1(habilidades.get(i).getAsJsonObject().get("ability").getAsJsonObject().get("name").getAsString());
                                else
                                    pokemon[0].setHabilidad2(habilidades.get(i).getAsJsonObject().get("ability").getAsJsonObject().get("name").getAsString());
                            }
                        }

                        //mainActivity.imprimirPokemon(pokemon[0]);
                        buscarDebilidades(pokemon[0]);

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(mainActivity.getCacheDir(),1024*1024*20),new BasicNetwork(new HurlStack()));

        requestQueue.start();

        requestQueue.add(jsonObjectRequest);
    }

    public void buscarPokemonYMostrar(int id){
        buscarPokemonYMostrar(String.valueOf(id));
    }

    public void buscarDebilidades(Pokemon p){

        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(mainActivity.getCacheDir(),1024*1024*20),new BasicNetwork(new HurlStack()));

        String endpoint = ApiUtils.endpointTipos + p.getTipo1();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        HashSet<String> debilidades = new HashSet<>();
                        HashSet<String> halfed = new HashSet<>();
                        HashSet<String> inmune = new HashSet<>();
                        try {
                            JSONObject damage_relations = response.getJSONObject("damage_relations");
                            JSONArray debilidadArray = damage_relations.getJSONArray("double_damage_from");
                            for (int i = 0; i < debilidadArray.length(); i++)
                                debilidades.add(debilidadArray.getJSONObject(i).getString("name"));

                            JSONArray halfedArray = damage_relations.getJSONArray("half_damage_from");
                            for( int i = 0; i < halfedArray.length();i++)
                                halfed.add(halfedArray.getJSONObject(i).getString("name"));

                            JSONArray inmuneArray = damage_relations.getJSONArray("no_damage_from");
                            for( int i = 0; i < inmuneArray.length();i++)
                                inmune.add(inmuneArray.getJSONObject(i).getString("name"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        p.setDebilidades(debilidades);
                        p.setResistencias(halfed);
                        p.setInmunidades(inmune);
                        if(p.getTipo2()==null)
                            mainActivity.imprimirPokemon(p);
                        else
                            debilidadesSegundoTipo(p);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });


        requestQueue.start();
        requestQueue.add(jsonObjectRequest);

    }

    private void debilidadesSegundoTipo(Pokemon p){
        JsonObjectRequest jsonObjectRequest2 = null;
        String endpoint2 = ApiUtils.endpointTipos + p.getTipo2();
        jsonObjectRequest2 = new JsonObjectRequest
                (Request.Method.GET, endpoint2, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        HashSet<String> debilidades = p.getDebilidades();
                        HashSet<String> halfed = p.getResistencias();
                        HashSet<String> inmune = p.getInmunidades();
                        HashSet<String> dobleDebilidades = new HashSet<>();
                        HashSet<String> dobleHalfed = new HashSet<>();
                        try {
                            JSONObject damage_relations = response.getJSONObject("damage_relations");

                            JSONArray inmuneArray = damage_relations.getJSONArray("no_damage_from");
                            for( int i = 0; i < inmuneArray.length();i++) {
                                String nombre = inmuneArray.getJSONObject(i).getString("name");
                                inmune.add(nombre);
                                debilidades.remove(nombre);
                                halfed.remove(nombre);
                            }

                            JSONArray debilidadArray = damage_relations.getJSONArray("double_damage_from");
                            for (int i = 0; i < debilidadArray.length(); i++) {
                                String nombre = debilidadArray.getJSONObject(i).getString("name");
                                if(debilidades.contains(nombre)) {
                                    debilidades.remove(nombre);
                                    dobleDebilidades.add(nombre);
                                }else if(halfed.contains(nombre)){
                                    halfed.remove(nombre);
                                }else if(!inmune.contains(nombre)){
                                    debilidades.add(nombre);
                                }
                            }

                            JSONArray halfedArray = damage_relations.getJSONArray("half_damage_from");
                            for( int i = 0; i < halfedArray.length();i++) {
                                String nombre = halfedArray.getJSONObject(i).getString("name");
                                if(halfed.contains(nombre)) {
                                    halfed.remove(nombre);
                                    dobleHalfed.add(nombre);
                                }else if(debilidades.contains(nombre)){
                                    halfed.remove(nombre);
                                }else if(!inmune.contains(nombre)){
                                    halfed.add(nombre);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        p.setDebilidades(debilidades);
                        p.setDobleDebilidades(dobleDebilidades);
                        p.setInmunidades(inmune);
                        p.setResistencias(halfed);
                        p.setDobleResistencias(dobleHalfed);



                        mainActivity.imprimirPokemon(p);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(mainActivity.getCacheDir(),1024*1024*20),new BasicNetwork(new HurlStack()));
        requestQueue.start();
        requestQueue.add(jsonObjectRequest2);

    }


}

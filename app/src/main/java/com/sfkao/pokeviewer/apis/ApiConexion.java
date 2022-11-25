package com.sfkao.pokeviewer.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sfkao.pokeviewer.modelo.pojo_pokemon.Pokemon;
import com.sfkao.pokeviewer.modelo.pojo_tipos.Tipo;

import java.io.IOException;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton para la conexion a la API
 */
public class ApiConexion {

    private static final ApiConexion api = new ApiConexion();

    public static ApiConexion getInstance(){
        return api;
    }

    //Obtener pokemon a partir del nombre
    public Pokemon getPokemon(String nombre){

        //Uso gson para insertar los datos en mi POJO
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        ApiUtils service = retrofit.create(ApiUtils.class);

        Call<Pokemon> callSync = service.getPokemon(nombre.toLowerCase(Locale.ROOT));
        //Hago la llamada
        try{
            retrofit2.Response<Pokemon> response = callSync.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Obtener el tipo a partir de su nombre
    public Tipo getTipo(String nombre){

        //Uso gson para insertar los datos en mi POJO
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        ApiUtils service = retrofit.create(ApiUtils.class);

        Call<Tipo> callSync = service.getTipo(nombre);
        //Hago la llamada
        try{
            retrofit2.Response<Tipo> response = callSync.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /*
    //Ruinas de un pasado lejano. Un fosil de codigo.
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


     */


}

package com.sfkao.pokeviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button botonBuscar;
    ImageView imagePokemon;
    EditText textoPokemon;

    TextView textError;

    ImageView imageTipoIzquierda, imageTipoMedio, imageTipoDerecha;

    HashMap<String, Drawable> diccionarioNombreAID = new HashMap<String, Drawable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonBuscar = (Button) findViewById(R.id.buttonSearchPokemon);
        imagePokemon = (ImageView) findViewById(R.id.imagePokemon);
        textoPokemon = (EditText) findViewById(R.id.textfieldPokemonNameOrNumber);

        imageTipoIzquierda = (ImageView) findViewById(R.id.imageTypeLeft);
        imageTipoMedio = (ImageView) findViewById(R.id.imageTypeMiddle);
        imageTipoDerecha = (ImageView) findViewById(R.id.imageTypeRight);

        textError = (TextView) findViewById(R.id.textError);


        diccionarioNombreAID = new HashMap<>();
        diccionarioNombreAID.put("normal",ResourcesCompat.getDrawable(getResources(),R.drawable.normal,null));
        diccionarioNombreAID.put("bug",ResourcesCompat.getDrawable(getResources(),R.drawable.bug,null));
        diccionarioNombreAID.put("dark",ResourcesCompat.getDrawable(getResources(),R.drawable.dark,null));
        diccionarioNombreAID.put("dragon",ResourcesCompat.getDrawable(getResources(),R.drawable.dragon,null));
        diccionarioNombreAID.put("electric",ResourcesCompat.getDrawable(getResources(),R.drawable.electric,null));
        diccionarioNombreAID.put("fairy",ResourcesCompat.getDrawable(getResources(),R.drawable.fairy,null));
        diccionarioNombreAID.put("fight",ResourcesCompat.getDrawable(getResources(),R.drawable.fight,null));
        diccionarioNombreAID.put("fire",ResourcesCompat.getDrawable(getResources(),R.drawable.fire,null));
        diccionarioNombreAID.put("flying",ResourcesCompat.getDrawable(getResources(),R.drawable.flying,null));
        diccionarioNombreAID.put("ghost",ResourcesCompat.getDrawable(getResources(),R.drawable.ghost,null));
        diccionarioNombreAID.put("grass",ResourcesCompat.getDrawable(getResources(),R.drawable.grass,null));
        diccionarioNombreAID.put("ground",ResourcesCompat.getDrawable(getResources(),R.drawable.ground,null));
        diccionarioNombreAID.put("ice",ResourcesCompat.getDrawable(getResources(),R.drawable.ice,null));
        diccionarioNombreAID.put("poison",ResourcesCompat.getDrawable(getResources(),R.drawable.poison,null));
        diccionarioNombreAID.put("psychic",ResourcesCompat.getDrawable(getResources(),R.drawable.psychic,null));
        diccionarioNombreAID.put("rock",ResourcesCompat.getDrawable(getResources(),R.drawable.rock,null));
        diccionarioNombreAID.put("steel",ResourcesCompat.getDrawable(getResources(),R.drawable.steel,null));
        diccionarioNombreAID.put("water",ResourcesCompat.getDrawable(getResources(),R.drawable.water,null));
    }

    @Override
    protected void onStart() {
        super.onStart();
        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    buscar();
                }
        });

        textoPokemon.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                buscar();
                return false;
            }
        });
    }

    private void buscar(){
        String endpointBase = "https://pokeapi.co/api/v2/pokemon/";
        String charset = "UTF-8";
        String entrada = String.valueOf(textoPokemon.getText());


        String endpoint = endpointBase + entrada;

        BasicNetwork basicNetwork = new BasicNetwork(new HurlStack());
        JsonObject jsonObject = null;
        String json = null;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JsonElement je = JsonParser.parseString(response.toString());

                        JsonObject jsonObject = new Gson().fromJson(response.toString(), JsonObject.class);
                        JsonObject sprites = new Gson().fromJson(jsonObject.get("sprites").toString(), JsonObject.class);
                        if (sprites.isJsonNull()) {
                            textError.setText(R.string.pokemonNotFound);
                            return;
                        }
                        Picasso.get().load(sprites.get("front_default").getAsString()).into(imagePokemon);

                        //JSONArray tipos = new Gson().fromJson(jsonObject.get("types").toString(),JSONArray.class);
                        JSONArray tipos = null;
                        try {
                            tipos = new JSONArray(jsonObject.get("types").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JSONObject tipo1 = null;
                        JSONObject tipo2 = null;

                        try {
                            tipo1 = tipos.getJSONObject(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(tipos.length()==2){
                            try {
                                tipo2 = tipos.getJSONObject(1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        try{
                            if(tipo2==null){
                                imageTipoMedio.setImageDrawable(diccionarioNombreAID.get(tipo1.getJSONObject("type").getString("name")));
                                imageTipoMedio.setVisibility(View.VISIBLE);
                                imageTipoIzquierda.setVisibility(View.INVISIBLE);
                                imageTipoDerecha.setVisibility(View.INVISIBLE);
                            }else{
                                imageTipoIzquierda.setImageDrawable(diccionarioNombreAID.get(tipo1.getJSONObject("type").getString("name")));
                                imageTipoDerecha.setImageDrawable(diccionarioNombreAID.get(tipo2.getJSONObject("type").getString("name")));
                                imageTipoMedio.setVisibility(View.INVISIBLE);
                                imageTipoIzquierda.setVisibility(View.VISIBLE);
                                imageTipoDerecha.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        textError.setText("");


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textError.setText(getResources().getText(R.string.pokemonNotFound));

                    }
                });

        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(getCacheDir(),1024*1024*20),new BasicNetwork(new HurlStack()));

        requestQueue.start();

        requestQueue.add(jsonObjectRequest);

    }
}
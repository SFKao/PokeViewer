package com.sfkao.pokeviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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

public class MainActivity extends AppCompatActivity {

    Button botonBuscar;
    ImageView imagePokemon;
    EditText textoPokemon;

    ImageView imageTipoIzquierda, imageTipoMedio, imageTipoDerecha;

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
                            textoPokemon.setText(R.string.pokemonNotFound);
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

                        if(tipo1!=null){
                            try {
                                if(tipo1.getJSONObject("type").getString("name").equals("normal")){
                                    imageTipoMedio.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.normal,null));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textoPokemon.setText(getResources().getText(R.string.pokemonNotFound));

                    }
                });

        RequestQueue requestQueue = new RequestQueue(new DiskBasedCache(getCacheDir(),1024*1024*20),new BasicNetwork(new HurlStack()));

        requestQueue.start();

        requestQueue.add(jsonObjectRequest);

    }
}
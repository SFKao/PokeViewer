package com.sfkao.pokeviewer.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;

import com.sfkao.pokeviewer.R;
import com.sfkao.pokeviewer.activities.MainActivity;
import com.sfkao.pokeviewer.apis.PokeviewerConexion;
import com.sfkao.pokeviewer.modelo.pojo_pokeapi_usuario.AmigoApi;
import com.sfkao.pokeviewer.utils.Login;
import com.squareup.picasso.Picasso;

public class BuscarAmigoDialog extends DialogFragment {

    private View view;
    private MainActivity context;

    private ImageView friendStatus;
    private TextView friendUsername;
    private TextView friendLikes;
    private ImageView friendLikesImg;
    private TextView friendFavs;
    private ImageView friendPok1;
    private ImageView friendPok2;
    private ImageView friendPok3;
    private EditText nombreUsuarioInput;
    private ImageButton buscarUsuario;
    private Button enviarSolicitudButton;
    private Button salirButton;

    private AmigoApi a;

    public BuscarAmigoDialog() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        this.context = (MainActivity) context;
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_buscar_amigo,null);

        friendStatus = (ImageView) view.findViewById(R.id.friend_status);
        friendUsername = (TextView) view.findViewById(R.id.friend_username);
        friendLikes = (TextView) view.findViewById(R.id.friend_likes);
        friendLikesImg = (ImageView) view.findViewById(R.id.friend_likes_img);
        friendFavs = (TextView) view.findViewById(R.id.friend_favs);
        friendPok1 = (ImageView) view.findViewById(R.id.friend_pok1);
        friendPok2 = (ImageView) view.findViewById(R.id.friend_pok2);
        friendPok3 = (ImageView) view.findViewById(R.id.friend_pok3);
        nombreUsuarioInput = (EditText) view.findViewById(R.id.nombreUsuarioText);
        buscarUsuario = (ImageButton) view.findViewById(R.id.buscar_usuario);
        enviarSolicitudButton = (Button) view.findViewById(R.id.enviar_solicitud_button);
        salirButton = (Button) view.findViewById(R.id.salir_button);

        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        buscarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = nombreUsuarioInput.getText().toString();
                if(username.equals("")){
                    Toast.makeText(context, R.string.se_requiere_nombre_de_usuario, Toast.LENGTH_SHORT).show();
                    return;
                }
                a = PokeviewerConexion.getInstance().buscarUsuario(Login.getUsuario().getApi_key(),username);
                if(a!=null){
                    friendUsername.setText(a.getUsername());
                    friendLikes.setText(String.valueOf(a.getLikes()));
                    friendFavs.setText(String.valueOf(a.getFavoritos()));
                    if(a.getPk1()!=null)
                        Picasso.get().load(a.getPk1().img).into(friendPok1);
                    else
                        friendPok1.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.pokeball,null));
                    if(a.getPk2()!=null)
                        Picasso.get().load(a.getPk2().img).into(friendPok2);
                    else
                        friendPok2.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.pokeball,null));
                    if(a.getPk3()!=null)
                        Picasso.get().load(a.getPk3().img).into(friendPok3);
                    else
                        friendPok3.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.pokeball,null));
                    if(a.getEstadoAmistad()==null)
                        a.setEstadoAmistad("desconocido");
                    switch (a.getEstadoAmistad()){
                        case "pendiente":
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_baseline_email_24,null));
                            break;
                        case "aceptada":
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.equipos,null));
                            break;
                        case "recibida":
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_baseline_mark_email_unread_24,null));
                            break;
                        case "desconocido":
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.lupa,null));
                            break;
                        default:
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bug,null));
                    }
                }
            }
        });

        enviarSolicitudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Login.isInvited()){
                    Toast.makeText(context, R.string.necesitas_estar_logueado, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(a == null){
                    Toast.makeText(context, R.string.se_requiere_nombre_de_usuario, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(a.getEstadoAmistad().equals("aceptada")){
                    Toast.makeText(context, R.string.el_usuario_ya_es_amigo, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(a.getEstadoAmistad().equals("pendiente")){
                    Toast.makeText(context, R.string.el_usuario_ya_tiene_la_peticion_de_amistad, Toast.LENGTH_SHORT).show();
                    return;
                }

                String newStatus = PokeviewerConexion.getInstance().enviarPeticion(Login.getUsuario().getApi_key(),a.getUsername());
                a.setEstadoAmistad(newStatus);
                if(newStatus!=null){
                    switch (a.getEstadoAmistad()){
                        case "pendiente":
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_baseline_email_24,null));
                            break;
                        case "aceptada":
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.equipos,null));
                            break;
                        case "recibida":
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.ic_baseline_mark_email_unread_24,null));
                            break;
                        case "desconocido":
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.lupa,null));
                            break;
                        default:
                            friendStatus.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bug,null));
                    }
                    Toast.makeText(context, R.string.la_solicitud_se_envio_correctamente, Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(context, R.string.hubo_un_error,Toast.LENGTH_SHORT).show();
            }
        });



        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(view);
        return alertDialogBuilder.create();
    }

}
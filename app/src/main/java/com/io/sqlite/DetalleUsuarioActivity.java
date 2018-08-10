package com.io.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.io.sqlite.entidades.usuario;

public class DetalleUsuarioActivity extends AppCompatActivity {

    TextView campoId,campoNombre,campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        campoId=findViewById(R.id.campoId);
        campoNombre=findViewById(R.id.campoNombre);
        campoTelefono=findViewById(R.id.campoTelefono);

        //recibimos el parámetro del bundle
        Bundle objetoEnviado=getIntent().getExtras();
        usuario user=null;

        if(objetoEnviado!=null){
            user= (usuario) objetoEnviado.getSerializable("usuario");
            campoId.setText(user.getId().toString());
            campoNombre.setText(user.getNombre().toString());
            campoTelefono.setText(user.getTelefono().toString());
        }

    }
}

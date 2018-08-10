package com.io.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.io.sqlite.entidades.usuario;
import com.io.sqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultarListaListView extends AppCompatActivity {

    ListView listViewPersonas;

    //esto lo ponemos aquí para que tenga un ámbito global
    ArrayList <String> listaInformacion; //un array que contiene la información que se va a presnetar
    ArrayList <usuario> listaUsuarios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_list_view);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listViewPersonas=findViewById(R.id.listViewPersonas);

        //llamamos al método que se encarga de consultar la lista de personas
        consultarListaPersonas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion="id: "+listaUsuarios.get(pos).getId()+"\n";
                informacion+="Nombre: "+listaUsuarios.get(pos).getNombre()+"\n";;
                informacion+="Telefono: "+listaUsuarios.get(pos).getTelefono();

                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();

                //para pasar datos a otra actividad
                usuario user=listaUsuarios.get(pos);

                Intent intent=new Intent(ConsultarListaListView.this,DetalleUsuarioActivity.class);

                Bundle bundle = new Bundle(); //creamos un objeto Bundle que se llama bundle que sea igual a un nuevo Bundle
                bundle.putSerializable("usuario",user); //serializable porque es un objeto en () ponemos la clave y el valor

                intent.putExtras(bundle);
                startActivity(intent);


            }
        });





    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        usuario usuario=null;

        //instanciamos la lista de usuarios
        listaUsuarios=new ArrayList<usuario>();
        //selecte * from tb_usuarios
        Cursor cursor=db.rawQuery("SELECT * from "+ Utilidades.TABLA_USUARIO+" ORDER BY "+Utilidades.CAMPO_ID,null);

        //la sentencia anterior se ejecuta y nos devuelve los registros
        while (cursor.moveToNext()){
            usuario=new usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();

        for(int i=0; i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+" - "+listaUsuarios.get(i).getNombre());

        }
    }
}

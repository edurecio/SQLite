package com.io.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.io.sqlite.utilidades.Utilidades;

public class registro_usuarios extends AppCompatActivity {

    EditText campoId, campoNombre, campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        campoId=findViewById(R.id.campoID);
        campoNombre=findViewById(R.id.campoNombre);
        campoTelefono=findViewById(R.id.campoTelefono);
    }

    public void onClick(View view) {
        registrarUsuarios();
        //registrarUsuariosSql();
    }

    private void registrarUsuariosSql() {
        //Definimos la BD y la abrimos
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        //insert into usuario (id,nombre,telefono) values (123, 'Yomismo', '65483')
        /*String insert="INSERT INTO "+Utilidades.TABLA_USUARIO + " ("+Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+")"
                +" VALUES ("+campoId.getText().toString()+", '"+campoNombre.getText().toString()+", '"+campoTelefono.getText().toString()+"')";*/
        String insert ="INSERT INTO "+Utilidades.TABLA_USUARIO+" ("+Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+") VALUES ( "
                +campoId.getText().toString()+",'"+campoNombre.getText().toString()+"','"+campoTelefono.getText().toString()+"')";

        db.execSQL(insert);

        db.close();
    }

    private void registrarUsuarios() {

        //abrimos la conexión para iniciar el registro
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        //agregamos con el put
        values.put(Utilidades.CAMPO_ID,campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        //lo insertamos en la BD
        Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id Registro; "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();

        //Lo mismo pero usando SQL INSERT INTO

        //limpiamos los campos
        campoId.setText("");
        campoNombre.setText("");
        campoTelefono.setText("");
        campoId.requestFocus();
    }
}

package com.io.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.io.sqlite.utilidades.Utilidades;

public class ConsultarUsuarios extends AppCompatActivity {

    EditText campoId,campoNombre,campoTelefono;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);

        conn= new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        campoId=findViewById(R.id.documentoId);
        campoNombre=findViewById(R.id.campoNombreConsulta);
        campoTelefono=findViewById(R.id.campoTelefonoConsulta);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnConsultar:
                consultar();

                break;

            case R.id.btnActualizarUsuario:
                actualizarUsuario();

                break;

            case R.id.btnEliminar:
                eliminar();

                break;
        }
    }

    private void eliminar() {
        //conectamos con la base de datos para escribir/borrar
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se eliminó el usuario",Toast.LENGTH_SHORT).show();
        db.close();
        limpiar();
        campoId.setText("");


    }

    private void actualizarUsuario() {
        //conectamos con la base de datos para escribir
        SQLiteDatabase db=conn.getWritableDatabase();

        String[] parametros={campoId.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);

        Toast.makeText(getApplicationContext(),"Se actualizó el usuario",Toast.LENGTH_SHORT).show();
        db.close();
        limpiar();
    }

    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        //definimos parámetros de consulta
        String[] parametros={campoId.getText().toString()};
        String [] campos ={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};

        try {

            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            //pasamos a los campos la información retornada
            campoNombre.setText(cursor.getString(0));
            campoTelefono.setText(cursor.getString(1));
            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe", Toast.LENGTH_LONG).show();
            limpiar();

        }
    }

    private void limpiar() {
        campoNombre.setText("");
        campoTelefono.setText("");
    }
}

package com.io.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.io.sqlite.entidades.usuario;
import com.io.sqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaComboActivity extends AppCompatActivity {

    Spinner comboPersonas;
    TextView txtNombre, txtDocumento, txtTelefono;
    ArrayList<String> listaPersonas; //con el que se va a llenar el combo
    ArrayList<usuario> personasList;  //con la identidad Usuario

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);

        conn= new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios", null,1);

        comboPersonas=findViewById(R.id.comboPersonas);

        txtDocumento=findViewById(R.id.txtDocumento);
        txtNombre=findViewById(R.id.txtNombre);
        txtTelefono=findViewById(R.id.txtTelefono);

        //ArrayList<String> listaPersonas = null;
        //ArrayList<usuario> personasList;

        consultarListaPersonas();

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                //cogemos los datos del Array personasList
                if (pos!=0) {
                    txtDocumento.setText(personasList.get(pos-1).getId().toString());
                    txtNombre.setText(personasList.get(pos-1).getNombre());
                    txtTelefono.setText(personasList.get(pos-1).getTelefono());
                }else{
                    txtDocumento.setText("");
                    txtNombre.setText("");
                    txtTelefono.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        //ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.combo_dias,android.R.layout.simple_spinner_item);
        //ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,personasList);
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
        //ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        //****    ArrayAdapter<CharSequence> adaptador=new ArrayAdapter (this,R.array.simple_spinner_item,listaPersonas);

        assert listaPersonas != null;
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,listaPersonas);

        comboPersonas.setAdapter(adaptador); //se ncarga de llamar a la BD y obtener la lista de personas con las que trabajamos



    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        usuario persona=null;
        personasList =new ArrayList<usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona=new usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            Log.i("Tel",persona.getTelefono());

            personasList.add(persona);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for(int i=0;i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId()+" - "+personasList.get(i).getNombre());
        }

    }

}

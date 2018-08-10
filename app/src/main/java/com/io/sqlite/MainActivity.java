package com.io.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
    }

    public void onClick(View view) {
        Intent miIntent=null;

        switch (view.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(MainActivity.this, registro_usuarios.class);
                break;

            case R.id.btnConsultar:
                miIntent=new Intent(MainActivity.this, ConsultarUsuarios.class);
                break;

            case R.id.btnConsultaSpinner:
                miIntent=new Intent(MainActivity.this, ConsultaComboActivity.class);
                break;

            case R.id.btnConsultaListView:
                miIntent=new Intent(MainActivity.this, ConsultarListaListView.class);
                break;

            case R.id.btnRegistroMascotas:
                miIntent=new Intent(MainActivity.this, RegistroMascotas.class);

                break;

            case R.id.btnConsultarListaMascotas:

                break;

        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
}

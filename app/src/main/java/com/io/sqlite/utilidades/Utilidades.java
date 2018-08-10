package com.io.sqlite.utilidades;

public class Utilidades {

    //ponemos constantes de los nombres de los campos
    public static String TABLA_USUARIO="Usuario";
    public static String CAMPO_ID="id";
    public static String CAMPO_NOMBRE="nombre";
    public static String CAMPO_TELEFONO="telefono";

    //COSTANTES PARA LA TABLA MASCOTA
    public static String TABLA_MASCOTA="mascota";
    public static String CAMPO_ID_MASCOTA="id_mascota";
    public static String CAMPO_NOMBRE_MASCOTA="id_nombre_mascota";
    public static String CAMPO_RAZA_MASCOTA="id_raza_mascota";
    public static String CAMPO_ID_DUENO="id_dueno";


    //public static final String CREAR_TABLA_USUARIO="CREATE TABLE usuarios (id INTEGER, nombre TEXT, telefono TEXT)";
    public static String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_TELEFONO+" TEXT)";
    public static String CREAR_TABLA_MASCOTA="CREATE TABLE "+
            TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_MASCOTA+" TEXT, "+CAMPO_RAZA_MASCOTA+" TEXT,"+CAMPO_ID_DUENO+" INTEGER)";
}

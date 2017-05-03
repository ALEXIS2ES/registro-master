package pe.edu.upeu.registro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upeu.registro.bean.Cars;
import pe.edu.upeu.registro.util.Commons;

/**
 * Created by lenovo on 2/05/2017.
 */

public class CarsDAO extends SQLiteOpenHelper {
    private static final int VERSION=1;

    public CarsDAO(Context context) {
        super(context, Commons.DATABASE, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {


        String ddl="CREATE TABLE Cars(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " codProducto  TEXT," +
                " desProducto INTEGER," +
                " cantidad INTEGER," +
                " precio INTEGER," +
                " total INTEGER," +
                ");";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String ddl ="DROP TABLE IF EXISTS Car";
        db.execSQL(ddl);
        this.onCreate(db);
    }

    public void deleteCar(Cars id){
        getWritableDatabase().delete("Person",null,null);
    }



    public void saveCars(Cars cars){
        ContentValues values=new ContentValues();
        values.put("desProducto",cars.getDesProducto());
        values.put("cantidad",cars.getCantidad());
        values.put("precio",cars.getPrecio());
        values.put("total",cars.getTotal());

        getWritableDatabase().insert("Car",null,values);
    }

    public List<Cars> findCarAll() {
        List<Cars> listCars = new ArrayList<Cars>();

        String columns[]={"id","codProducto ","desProducto","cantidad ","precio","total"};
        String where=null;
        Cursor cursor = getReadableDatabase().query("Cars",columns,where,null,null,null,null);
        Cars cars=null;
        while (cursor.moveToNext()){
            cars = new Cars();
            cars.setId(cursor.getInt(1));
            cars.setCodProducto(cursor.getInt(2));
            cars.setDesProducto(cursor.getString(2));
            cars.setCantidad(cursor.getInt(3));
            cars.setPrecio(cursor.getInt(4));
            cars.setTotal(cursor.getInt(5));

            listCars.add(cars);
        }
        return listCars;
    }



    public void updateCars(Cars cars){
        ContentValues values = new ContentValues();

        values.put("codProducto", cars.getCodProducto());
        values.put("desProducto", cars.getDesProducto());
        values.put("cantidad", cars.getCantidad());
        values.put("precio", cars.getPrecio());
        values.put("total", cars.getTotal());

        getWritableDatabase().update("Cars",values,"id="+cars.getId(),null);
    }


    public void deleteCars(Cars id){
        getWritableDatabase().delete("Cars",null,null);
    }

    public Cars findCarById(String id){
        String column[]={"id","codProducto","desProducto","cantidad","precio","total"};
        String where = "id = "+id;

        Cars cars=null;
        Cursor cursor = getReadableDatabase().query("Cars",column,where,null,null,null,null);
        if(cursor.moveToFirst()){
            cars=new Cars();
            cars.setId(cursor.getInt(0));
            cars.setCodProducto(cursor.getInt(1));
            cars.setDesProducto(cursor.getString(2));
            cars.setCantidad(cursor.getInt(3));
            cars.setPrecio(cursor.getInt(4));
            cars.setTotal(cursor.getInt(5));

        }
        cursor.close();
        return cars;
    }
}

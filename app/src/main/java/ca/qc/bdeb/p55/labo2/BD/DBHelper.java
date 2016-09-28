package ca.qc.bdeb.p55.labo2.BD;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ca.qc.bdeb.p55.labo2.Client.Client;

/**
 * Created by WuTchanKi on 2016-09-23.
 */
public class DBHelper extends SQLiteOpenHelper {
    private  static final String DB_NAME = "boris.db";
    public static final int DBVERSION = 1;
    private Context context;
    private static DBHelper instance = null;

    private static final String TABLE_NOM = "nomtable";
    private static final String TABLE_CLIENT = "clients";

    private  static final String CLIENT_ID = "_id";
    private static final String CLIENT_PRENOM = "prenom";
    private static final String CLIENT_NOM = "nom";
    private static final String CLIENT_MAIL = "mail";
    private static final String CLIENT_CITY = "city";
    private static final String CLIENT_ADRESS = "adress";
    private static final String CLIENT_AGE = "age";
    private static final String CLIENT_GENDER = "gender";

    public static DBHelper getInstance (Context context){
        if(instance == null){
            instance = new DBHelper(context.getApplicationContext());
        }
        return  instance;
    }
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private DBHelper (Context context){
        super(context, DB_NAME, null, DBVERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlClient = "CREATE TABLE " + TABLE_CLIENT + "("+ CLIENT_ID + " INTEGER PRIMARY" +
                " KEY," + CLIENT_NOM + " TEXT,"+ CLIENT_PRENOM +" TEXT,"+ CLIENT_ADRESS+" TEXT,"
                + CLIENT_CITY+ " TEXT," + CLIENT_MAIL+ " TEXT," + CLIENT_AGE+ " INTEGER,"+
                CLIENT_GENDER+ " TEXT)";

        db.execSQL(sqlClient);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void ajouterClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CLIENT_NOM, client.getLastName());
        values.put(CLIENT_PRENOM, client.getFirstName());
        values.put(CLIENT_ADRESS, client.getAdress());
        values.put(CLIENT_CITY, client.getCity());
        values.put(CLIENT_MAIL, client.getMail());
        values.put(CLIENT_AGE, client.getAge());
        values.put(CLIENT_GENDER, client.getGender());

        long id = db.insert(TABLE_CLIENT, null, values);
        client.setIdClient(id);
        db.close();
    }
    public ArrayList<Client> recupererListeClient(){
        ArrayList<Client> clients = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLIENT, null,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }

        if(cursor.getCount() > 0){

            do{

                Client client = new Client();

              client.setLastName(cursor.getString(0))  ;
                client.setFirstName(cursor.getString(1));
                client.setAdress(cursor.getString(2))  ;
                client.setCity(cursor.getString(3));
                client.setMail(cursor.getString(4));
                client.setAge(cursor.getInt(5));
                client.setGender(cursor.getString(6));

                clients.add(client);




            }while(cursor.moveToNext());
        }
        return clients;
    }

    public Client getClient(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLIENT, new String [] { CLIENT_ID, CLIENT_NOM, CLIENT_PRENOM,
        CLIENT_ADRESS, CLIENT_CITY, CLIENT_MAIL, CLIENT_AGE, CLIENT_GENDER}, CLIENT_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Client client = new Client(cursor.getString(0), cursor.getString(1), cursor.getString(2)
        , cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6));
        cursor.close();
        db.close();
        return client;
    }

    public boolean updateClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CLIENT_NOM, client.getLastName());
        values.put(CLIENT_PRENOM, client.getFirstName());
        values.put(CLIENT_ADRESS, client.getAdress());
        values.put(CLIENT_CITY, client.getCity());
        values.put(CLIENT_MAIL, client.getMail());
        values.put(CLIENT_AGE, client.getAge());
        values.put(CLIENT_GENDER, client.getGender());

        int nbMAJ = db.update(TABLE_CLIENT, values, CLIENT_ID + " = ?",
                new String[] {String.valueOf(client.getIdClient())});

        db.close();

        return  (nbMAJ >0);
    }

    public void deleteClient(Client client){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CLIENT, CLIENT_ID + " = ?",
                new String [] { String.valueOf(client.getIdClient())});

        db.close();
    }
}
